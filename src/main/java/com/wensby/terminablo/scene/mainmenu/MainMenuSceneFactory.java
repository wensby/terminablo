package com.wensby.terminablo.scene.mainmenu;

import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.playscene.PlaySceneFactory;
import com.wensby.terminablo.userinterface.component.InterfaceComponentFactory;

import java.util.List;

public class MainMenuSceneFactory {

  private final SceneStack sceneStack;
  private final PlaySceneFactory playSceneFactory;
  private final InterfaceComponentFactory componentFactory;

  public MainMenuSceneFactory(SceneStack sceneStack, PlaySceneFactory playSceneFactory, InterfaceComponentFactory componentFactory) {
    this.sceneStack = sceneStack;
    this.playSceneFactory = playSceneFactory;
    this.componentFactory = componentFactory;
  }

  public Scene createMainMenuScene()  {
    var items = List.of("SINGLE PLAYER", "BATTLE.NET", "OTHER MULTIPLAYER", "CREDITS", "CINEMATICS", "EXIT TERMINABLO");
    var model = new MainMenuModel(items);
    var controller = new MainMenuController(sceneStack, model, playSceneFactory);
    var view = new MainMenuView(model, componentFactory);
    return new Scene(controller, view);
  }
}
