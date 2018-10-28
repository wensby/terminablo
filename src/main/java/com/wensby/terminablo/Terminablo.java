package com.wensby.terminablo;

import static java.lang.System.in;
import static java.lang.System.out;

import com.wensby.BenchmarkController;
import com.wensby.BenchmarkRenderer;
import com.wensby.BenchmarkRendererImpl;
import com.wensby.TerminalLayerWriter;
import com.wensby.TerminalLayerWriterImpl;
import com.wensby.UpdaterImpl;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.levelscene.LevelSceneFactory;
import com.wensby.terminablo.scene.levelscene.LevelSceneFactoryImpl;
import com.wensby.terminablo.scene.mainmenu.LinuxTerminalMainMenuView;
import com.wensby.terminablo.scene.mainmenu.MainMenuControllerImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuModelImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuScene;
import com.wensby.terminablo.userinterface.terminal.BashCommandExecutor;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminal;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalRenderCommandFactory;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalUserInterfaceFactory;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
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
    var userInterfaceFactory = new LinuxTerminalUserInterfaceFactory(linuxTerminal, commandFactory);
    var userInterface = userInterfaceFactory.createUserInterface();
    var sceneStack = new SceneStackImpl();
    var canvas = (LinuxTerminalVisualCanvas) userInterface.getCanvas();
    var layerFactory = new TerminalLayerFactoryImpl(characterFactory);
    var levelSceneFactory = new LevelSceneFactoryImpl(characterFactory, layerFactory, canvas, sceneStack);
    MainMenuScene scene = createMainMenuScene(characterFactory, sceneStack, layerFactory,
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
          .build()
          .run();
    } catch (Throwable e) {
      Logger.getLogger(Terminablo.class).fatal("Terminablo crashed.", e);
    } finally {
      userInterface.release();
    }
  }

  private static MainMenuScene createMainMenuScene(
      TerminalCharacterFactory characterFactory,
      SceneStack sceneStack,
      TerminalLayerFactory layerFactory,
      LevelSceneFactory levelSceneFactory
  ) {
    var model = new MainMenuModelImpl();
    var view = new LinuxTerminalMainMenuView(model, layerFactory, characterFactory);
    var controller = new MainMenuControllerImpl(model, sceneStack, levelSceneFactory);
    return new MainMenuScene(controller, view);
  }

}
