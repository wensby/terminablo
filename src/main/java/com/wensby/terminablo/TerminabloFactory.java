package com.wensby.terminablo;

import com.wensby.application.TerminalApplication;
import com.wensby.application.TerminalApplicationContext;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuSceneFactory;
import com.wensby.terminablo.scene.playscene.PlaySceneFactoryImpl;
import com.wensby.terminablo.userinterface.component.BorderStyleFactory;
import com.wensby.terminablo.userinterface.component.InterfaceComponentFactory;

public class TerminabloFactory {

  public TerminalApplication createTerminablo(TerminalApplicationContext context) {
    var characterFactory = context.getCharacterFactory();
    var sceneStack = new SceneStackImpl();
    var levelSceneFactory = new PlaySceneFactoryImpl(characterFactory, sceneStack);
    var borderStyleFactory = new BorderStyleFactory(context.getLayerFactory(), characterFactory);
    var componentFactory = new InterfaceComponentFactory(characterFactory, context.getLayerFactory(), borderStyleFactory);
    var mainMenuSceneFactory = new MainMenuSceneFactory(sceneStack, levelSceneFactory, characterFactory);
    var scene = mainMenuSceneFactory.createMainMenuScene();
    var testScene = new Scene((elapsedTime, input) -> {}, layer -> {});
    sceneStack.push(scene);
    var terminabloUpdater = new TerminabloUpdater(sceneStack);
    var terminabloRenderer = new TerminabloApplicationRenderer(sceneStack);
    return context.getApplicationBuilder()
        .withUpdater(terminabloUpdater)
        .withRenderer(terminabloRenderer)
        .withTargetTicksPerSecond(30)
        .build();
  }
}
