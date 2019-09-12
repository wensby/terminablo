package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.GameRepository;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.userinterface.UserInterface;
import com.wensby.terminablo.userinterface.reactive.ComponentFactory;


import static java.util.Objects.requireNonNull;

public class MainMenuSceneFactory {

  private final SceneStack sceneStack;
  private final TerminalCharacterFactory characterFactory;
  private final GameRepository gameRepository;

  public MainMenuSceneFactory(SceneStack sceneStack, TerminalCharacterFactory characterFactory, GameRepository gameRepository) {
    this.sceneStack = sceneStack;
    this.characterFactory = characterFactory;
    this.gameRepository = requireNonNull(gameRepository);
  }

  public Scene createMainMenuScene() {
    Runnable onExitTerminabloClicked = sceneStack::pop;
    var componentFactory = new ComponentFactory();
    var mainMenuPage = new MainMenuPageSwitch(componentFactory, characterFactory, onExitTerminabloClicked, gameRepository);
    var mainMenuUserInterface = new UserInterface(mainMenuPage);
    var model = new MainMenuModel(mainMenuUserInterface);
    var controller = new MainMenuController(model);
    var view = new MainMenuView(model);
    return new Scene(controller, view);
  }
}
