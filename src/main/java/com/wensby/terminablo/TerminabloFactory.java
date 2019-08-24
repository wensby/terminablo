package com.wensby.terminablo;

import com.wensby.UpdaterImpl;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.LinuxTerminalMainMenuView;
import com.wensby.terminablo.scene.mainmenu.MainMenuControllerImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuModelImpl;
import com.wensby.terminablo.scene.playscene.LevelSceneFactory;
import com.wensby.terminablo.scene.playscene.LevelSceneFactoryImpl;
import com.wensby.userinterface.LayerDifferenceCalculator;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.TerminalLayerFactoryImpl;
import com.wensby.userinterface.linux.*;

public class TerminabloFactory {

  public static GameLooper createTerminabloGameLooper(LinuxTerminal linuxTerminal, LinuxTerminalRenderCommandFactory commandFactory) {
    var characterFactory = new TerminalCharacterFactoryImpl();
    var layerDifferenceCalculator = new LayerDifferenceCalculator(characterFactory);
    var layerFactory = new TerminalLayerFactoryImpl(characterFactory);
    var frameFactory = new LinuxTerminalFrameFactory(linuxTerminal, layerFactory);
    var terminalCanvas = new TerminalCanvasImpl(frameFactory, linuxTerminal.getOutputStream(), commandFactory, layerDifferenceCalculator);
    var terminalKeyboard = new LinuxTerminalKeyboard(linuxTerminal.getInputStream());
    var userInterface = new TerminalUserInterface(terminalKeyboard, terminalCanvas);
    var sceneStack = new SceneStackImpl();
    var canvas = userInterface.getCanvas();
    var levelSceneFactory = new LevelSceneFactoryImpl(characterFactory, layerFactory, sceneStack);
    var scene = createMainMenuScene(characterFactory, sceneStack, layerFactory, levelSceneFactory);
    sceneStack.push(scene);
    var benchmarkModel = new BenchmarkModelImpl();
    var benchmarkController = new BenchmarkControllerImpl(benchmarkModel);
    var sceneStackTicker = new UpdaterImpl(sceneStack, benchmarkController);
    var view = new BenchmarkViewImpl(layerFactory, characterFactory, benchmarkModel);
    var renderer = new RendererImpl(canvas, sceneStack, benchmarkModel, view);
    return new GameLooperBuilder()
        .withUpdater(sceneStackTicker)
        .withTerminalUserInterface(userInterface)
        .withRenderer(renderer)
        .withBenchmarkModel(benchmarkModel)
        .withTargetTicksPerSecond(15)
        .build();
  }

  private static Scene createMainMenuScene(
      TerminalCharacterFactory characterFactory,
      SceneStack sceneStack,
      TerminalLayerFactory layerFactory,
      LevelSceneFactory levelSceneFactory
  ) {
    var model = new MainMenuModelImpl();
    var view = new LinuxTerminalMainMenuView(model, layerFactory, characterFactory);
    var controller = new MainMenuControllerImpl(model, sceneStack, levelSceneFactory);
    return new Scene(controller, view);
  }
}
