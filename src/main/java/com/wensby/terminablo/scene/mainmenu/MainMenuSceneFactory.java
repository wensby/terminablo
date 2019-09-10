package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.userinterface.UserInterface;
import com.wensby.terminablo.userinterface.reactive.ComponentFactory;

public class MainMenuSceneFactory {

  private final SceneStack sceneStack;
  private final TerminalCharacterFactory characterFactory;

  public MainMenuSceneFactory(SceneStack sceneStack, TerminalCharacterFactory characterFactory) {
    this.sceneStack = sceneStack;
    this.characterFactory = characterFactory;
  }

  public Scene createMainMenuScene()  {
    Runnable onExitTerminabloClicked = sceneStack::pop;
    var componentFactory = new ComponentFactory();
    var mainMenuPage = new MainMenuPageSwitch(componentFactory, characterFactory, onExitTerminabloClicked);
    var mainMenuUserInterface = new UserInterface(mainMenuPage);
    var model = new MainMenuModel(mainMenuUserInterface);
    var controller = new MainMenuController(model);
    var view = new MainMenuView(model);
    return new Scene(controller, view);
  }
}
