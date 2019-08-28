package com.wensby.terminablo;

import com.wensby.application.TerminalApplication;
import com.wensby.application.TerminalApplicationContext;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.LinuxTerminalMainMenuView;
import com.wensby.terminablo.scene.mainmenu.MainMenuControllerImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuModelImpl;
import com.wensby.terminablo.scene.playscene.PlaySceneFactory;
import com.wensby.terminablo.scene.playscene.PlaySceneFactoryImpl;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerFactory;

public class TerminabloFactory {

  public TerminalApplication createTerminablo(TerminalApplicationContext context) {
    var characterFactory = context.getCharacterFactory();
    var layerFactory = context.getLayerFactory();
    var sceneStack = new SceneStackImpl();
    var levelSceneFactory = new PlaySceneFactoryImpl(characterFactory, layerFactory, sceneStack);
    var scene = createMainMenuScene(characterFactory, sceneStack, layerFactory, levelSceneFactory);
    sceneStack.push(scene);
    var terminabloUpdater = new TerminabloUpdater(sceneStack);
    var terminabloRenderer = new TerminabloApplicationRenderer(sceneStack);
    return context.getApplicationBuilder()
        .withUpdater(terminabloUpdater)
        .withRenderer(terminabloRenderer)
        .withTargetTicksPerSecond(10)
        .build();
  }

  private static Scene createMainMenuScene(
      TerminalCharacterFactory characterFactory,
      SceneStack sceneStack,
      TerminalLayerFactory layerFactory,
      PlaySceneFactory playSceneFactory
  ) {
    var model = new MainMenuModelImpl();
    var view = new LinuxTerminalMainMenuView(model, layerFactory, characterFactory);
    var controller = new MainMenuControllerImpl(model, sceneStack, playSceneFactory);
    return new Scene(controller, view);
  }
}
