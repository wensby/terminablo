package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Grid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;
import java.util.Map;

public class MainMenuPage extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onSinglePlayerClicked;
  private final Runnable onExitTerminabloClicked;

  public MainMenuPage(TerminalCharacterFactory characterFactory, Runnable onSinglePlayerClicked, Runnable onExitTerminabloClicked) {
    this.characterFactory = characterFactory;
    this.onSinglePlayerClicked = onSinglePlayerClicked;
    this.onExitTerminabloClicked = onExitTerminabloClicked;
  }

  @Override
  public Component render() {
    var childLayout =
        "_ _ _\n" +
        "_ menu _";
    var columnLayout = List.of(1, 1, 1);
    var rowLayout = List.of(1, 1);
    var mainMenu = new MainMenu(characterFactory, onSinglePlayerClicked, onExitTerminabloClicked);
    return new Grid(
        Map.of("menu", mainMenu),
        childLayout, columnLayout, rowLayout
    );
  }
}
