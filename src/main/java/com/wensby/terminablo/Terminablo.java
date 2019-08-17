package com.wensby.terminablo;

import static java.lang.System.in;
import static java.lang.System.out;

import com.wensby.BenchmarkController;
import com.wensby.UpdaterImpl;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.playscene.LevelSceneFactory;
import com.wensby.terminablo.scene.playscene.LevelSceneFactoryImpl;
import com.wensby.terminablo.scene.mainmenu.LinuxTerminalMainMenuView;
import com.wensby.terminablo.scene.mainmenu.MainMenuControllerImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuModelImpl;
import com.wensby.userinterface.BashCommandExecutor;
import com.wensby.userinterface.CharacterDifferenceFactory;
import com.wensby.userinterface.linux.LinuxTerminal;
import com.wensby.userinterface.linux.LinuxTerminalRenderCommandFactory;
import com.wensby.userinterface.linux.LinuxTerminalUserInterfaceFactory;
import com.wensby.userinterface.linux.LinuxTerminalVisualCanvas;
import com.wensby.userinterface.linux.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.TerminalLayerFactoryImpl;
import com.wensby.util.BenchmarkView;
import org.apache.log4j.Logger;

public class Terminablo {

  public static void main(String[] args) {
    var bashCommandExecutor = new BashCommandExecutor();
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var linuxTerminal = new LinuxTerminal(in, out, bashCommandExecutor, commandFactory);
    var characterFactory = new LinuxTerminalCharacterFactory();
    var characterDifferenceFactory = new CharacterDifferenceFactory(characterFactory);
    var userInterfaceFactory = new LinuxTerminalUserInterfaceFactory(linuxTerminal, commandFactory, characterDifferenceFactory);
    var userInterface = userInterfaceFactory.createUserInterface();
    var sceneStack = new SceneStackImpl();
    var canvas = (LinuxTerminalVisualCanvas) userInterface.getCanvas();
    var layerFactory = new TerminalLayerFactoryImpl(characterFactory);
    var levelSceneFactory = new LevelSceneFactoryImpl(characterFactory, layerFactory, canvas, sceneStack);
    Scene scene = createMainMenuScene(characterFactory, sceneStack, layerFactory,
        levelSceneFactory);
    sceneStack.push(scene);
    BenchmarkModel benchmarkModel = new BenchmarkModelImpl();
    BenchmarkController benchmarkController = new BenchmarkControllerImpl(benchmarkModel);
    var sceneStackTicker = new UpdaterImpl(sceneStack, benchmarkController);
    BenchmarkView view = new BenchmarkViewImpl(layerFactory, characterFactory, benchmarkModel);
    var renderer = new RendererImpl(canvas, sceneStack, benchmarkModel, view);
    try {
      new GameLooperBuilder()
          .withTickable(sceneStackTicker)
          .withUserInterface(userInterface)
          .withRenderer(renderer)
          .withBenchmarkModel(benchmarkModel)
          .withTargetTicksPerSecond(15)
          .build()
          .run();
    } catch (Throwable e) {
      Logger.getLogger(Terminablo.class).fatal("Terminablo crashed.", e);
    } finally {
      userInterface.release();
    }
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
