package com.wensby.terminablo;

import com.wensby.application.TerminalApplication;
import com.wensby.application.TerminalApplicationContext;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.UserInput;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuView;
import com.wensby.terminablo.scene.mainmenu.MainMenuController;
import com.wensby.terminablo.scene.mainmenu.MainMenuModel;
import com.wensby.terminablo.scene.playscene.PlaySceneFactory;
import com.wensby.terminablo.scene.playscene.PlaySceneFactoryImpl;
import com.wensby.terminablo.scene.testscene.TestSceneView;
import com.wensby.terminablo.userinterface.component.InterfaceComponentFactory;

import java.time.Duration;
import java.util.List;

public class TerminabloFactory {

  public TerminalApplication createTerminablo(TerminalApplicationContext context) {
    var characterFactory = context.getCharacterFactory();
    var sceneStack = new SceneStackImpl();
    var levelSceneFactory = new PlaySceneFactoryImpl(characterFactory, sceneStack);
    var scene = createMainMenuScene(characterFactory, sceneStack, levelSceneFactory);
    var testScene = new Scene((Duration elapsedTime, UserInput input) -> {}, new TestSceneView(characterFactory));
    sceneStack.push(scene);
    var terminabloUpdater = new TerminabloUpdater(sceneStack);
    var terminabloRenderer = new TerminabloApplicationRenderer(sceneStack);
    return context.getApplicationBuilder()
        .withUpdater(terminabloUpdater)
        .withRenderer(terminabloRenderer)
        .withTargetTicksPerSecond(30)
        .build();
  }

  private static Scene createMainMenuScene(
      TerminalCharacterFactory characterFactory,
      SceneStack sceneStack,
      PlaySceneFactory playSceneFactory
  ) {
    var items = List.of("SINGLE PLAYER", "BATTLE.NET", "OTHER MULTIPLAYER", "CREDITS", "CINEMATICS", "EXIT TERMINABLO");
    var model = new MainMenuModel(items);
    var componentFactory = new InterfaceComponentFactory(characterFactory);
    var view = new MainMenuView(model, componentFactory);
    var controller = new MainMenuController(sceneStack, model, playSceneFactory);
    return new Scene(controller, view);
  }
}
