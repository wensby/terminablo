package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Grid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.userinterface.reactive.Text;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameSelection extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;

  public GameSelection(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  @Override
  public Component render() {
    return new Grid(Map.of(
        "name", new Text(characterFactory, "text", false),
        "games", new GamesGrid(characterFactory),
        "controls", new Text(characterFactory, "controls", false),
        "exit", new Text(characterFactory, "exit", false)
    ), "name\ngames\ncontrols\nexit", List.of(1), List.of(1, 5, 1, 1));
  }
}
