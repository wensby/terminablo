package com.wensby.terminablo;

import static java.lang.System.in;
import static java.lang.System.out;

import com.wensby.SceneTicker;
import com.wensby.terminablo.scene.LevelSceneInterfaceRenderer;
import com.wensby.terminablo.scene.OrbTerminalRepresentationFactory;
import com.wensby.terminablo.scene.levelscene.LevelScene;
import com.wensby.terminablo.scene.levelscene.LevelSceneController;
import com.wensby.terminablo.scene.levelscene.LevelSceneModel;
import com.wensby.terminablo.scene.levelscene.LevelSceneTerminalView;
import com.wensby.terminablo.userinterface.terminal.BashCommandExecutor;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminal;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalRenderCommandFactory;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalUserInterfaceFactory;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;
import com.wensby.terminablo.world.AgentBuilder;
import com.wensby.terminablo.world.level.LevelEntityFactory;
import com.wensby.terminablo.world.level.LevelFactory;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactoryImpl;

public class Terminablo {

  public static void main(String[] args) {
    var bashCommandExecutor = new BashCommandExecutor();
    var linuxTerminal = new LinuxTerminal(in, out, bashCommandExecutor);
    var characterFactory = new LinuxTerminalCharacterFactory();
    var commandFactory = new LinuxTerminalRenderCommandFactory(characterFactory);
    var userInterfaceFactory = new LinuxTerminalUserInterfaceFactory(linuxTerminal, commandFactory);
    var userInterface = userInterfaceFactory.createUserInterface();
    var canvas = (LinuxTerminalVisualCanvas) userInterface.getCanvas();
    var hero = new AgentBuilder().build();
    var levelEntityFactory = new LevelEntityFactory();
    var levelFactory = new LevelFactory(levelEntityFactory);
    var level = levelFactory.createFactoryFromString("###\n# #\n###");
    var levelSceneModel = new LevelSceneModel(hero, level);
    level.putEntity(LevelLocation.of(1, 1), hero.getLevelEntity());
    var orbTerminalRepresentationFactory = new OrbTerminalRepresentationFactory(characterFactory);
    var terminalLayerFactory = new TerminalLayerFactoryImpl();
    var levelSceneInterfaceRenderer = new LevelSceneInterfaceRenderer(
        orbTerminalRepresentationFactory, terminalLayerFactory, characterFactory);
    var levelEntityRenderer = new LevelEntityRenderer(characterFactory);
    var levelRenderer = new TerminalLevelRenderer(terminalLayerFactory, levelEntityRenderer);
    var levelSceneView = new LevelSceneTerminalView(canvas, levelSceneInterfaceRenderer,
        levelRenderer, levelSceneModel);
    var levelSceneController = new LevelSceneController(levelSceneModel);
    var scene = new LevelScene(levelSceneController, levelSceneView);
    var gameTicker = new SceneTicker(scene);
    try {
      new GameLooperBuilder()
          .withTickable(gameTicker)
          .withUserInterface(userInterface)
          .build()
          .run();
    } finally {
      userInterface.release();
    }
  }

}
