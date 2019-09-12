package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.Game;
import com.wensby.terminablo.userinterface.DecoratedText;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.FlexibleGrid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.userinterface.reactive.Text;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class GameSelection extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final List<Game> games;

  public GameSelection(TerminalCharacterFactory characterFactory, List<Game> games) {
    this.characterFactory = requireNonNull(characterFactory);
    this.games = requireNonNull(games);
  }

  @Override
  public Component render() {
    return new FlexibleGrid(Map.of(
        "name", new Text(characterFactory, DecoratedText.blank().append("text"), false),
        "games", new GamesGrid(characterFactory, games),
        "controls", new Text(characterFactory, DecoratedText.blank().append("controls"), false),
        "exit", new Text(characterFactory, DecoratedText.blank().append("exit"), false)
    ), "name\ngames\ncontrols\nexit", List.of(1), List.of(1, 5, 1, 1));
  }
}
