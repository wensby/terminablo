package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.Game;
import com.wensby.terminablo.userinterface.DecoratedText;
import com.wensby.terminablo.userinterface.reactive.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class GameSelection extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final TerminalCharacterFactory characterFactory;
  private final List<Game> games;

  public GameSelection(ComponentFactory componentFactory, TerminalCharacterFactory characterFactory, List<Game> games) {
    this.componentFactory = requireNonNull(componentFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.games = requireNonNull(games);
  }

  @Override
  public Component render() {
    return componentFactory.aFlexibleGrid()
        .withChild("name", new Text(characterFactory, DecoratedText.blank().append("text"), false))
        .withChild("games", new GamesGrid(componentFactory, characterFactory, games))
        .withChild("controls", new Text(characterFactory, DecoratedText.blank().append("controls"), false))
        .withChild("exit", new Text(characterFactory, DecoratedText.blank().append("exit"), false))
        .withColumnRatios(List.of(1))
        .addRow(List.of("name"), 1)
        .addRow(List.of("games"), 5)
        .addRow(List.of("controls"), 1)
        .addRow(List.of("exit"), 1)
        .build();
  }
}
