package com.wensby.terminablo;

import com.wensby.application.TerminalApplicationBuilder;
import com.wensby.application.TerminalApplication;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.LinuxTerminalMainMenuView;
import com.wensby.terminablo.scene.mainmenu.MainMenuControllerImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuModelImpl;
import com.wensby.terminablo.scene.playscene.LevelSceneFactory;
import com.wensby.terminablo.scene.playscene.LevelSceneFactoryImpl;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.TerminalLayerFactoryImpl;
import com.wensby.userinterface.linux.*;

public class TerminabloFactory {

  public static TerminalApplication createTerminablo(LinuxTerminal linuxTerminal, LinuxTerminalRenderCommandFactory commandFactory) {
    var characterFactory = new TerminalCharacterFactoryImpl();
    var layerFactory = new TerminalLayerFactoryImpl(characterFactory);
    var sceneStack = new SceneStackImpl();
    var levelSceneFactory = new LevelSceneFactoryImpl(characterFactory, layerFactory, sceneStack);
    var scene = createMainMenuScene(characterFactory, sceneStack, layerFactory, levelSceneFactory);
    sceneStack.push(scene);
    var terminabloUpdater = new TerminabloUpdater(sceneStack);
    var frameRenderer = new TerminabloApplicationRenderer(sceneStack);
    return new TerminalApplicationBuilder()
        .withCommandFactory(commandFactory)
        .withCharacterFactory(characterFactory)
        .withLayerFactory(layerFactory)
        .withLinuxTerminal(linuxTerminal)
        .withUpdater(terminabloUpdater)
        .withRenderer(frameRenderer)
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
