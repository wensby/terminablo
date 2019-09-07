package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.scene.playscene.PlaySceneFactory;
import com.wensby.terminablo.userinterface.UserInterface;

public class MainMenuSceneFactory {

  private final SceneStack sceneStack;
  private final PlaySceneFactory playSceneFactory;
  private final TerminalCharacterFactory characterFactory;

  public MainMenuSceneFactory(SceneStack sceneStack, PlaySceneFactory playSceneFactory, TerminalCharacterFactory characterFactory) {
    this.sceneStack = sceneStack;
    this.playSceneFactory = playSceneFactory;
    this.characterFactory = characterFactory;
  }

  public Scene createMainMenuScene()  {
    Runnable onSinglePlayerClicked = () -> sceneStack.push(playSceneFactory.createPlayScene());
    Runnable onExitTerminabloClicked = sceneStack::pop;
    var mainMenuPage = new MainMenuPage(characterFactory, onSinglePlayerClicked, onExitTerminabloClicked);
    var mainMenuUserInterface = new UserInterface(mainMenuPage);
    var model = new MainMenuModel(mainMenuUserInterface);
    var controller = new MainMenuController(model);
    var view = new MainMenuView(model);
    return new Scene(controller, view);
  }
}
