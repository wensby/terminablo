package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.Game;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.FlexibleGrid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class GameSelectionPage extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExit;
  private final List<Game> games;

  public GameSelectionPage(TerminalCharacterFactory characterFactory, Runnable onExit, List<Game> games) {
    this.characterFactory = requireNonNull(characterFactory);
    this.onExit = requireNonNull(onExit);
    this.games = requireNonNull(games);
  }

  @Override
  public Component render() {
    return new FlexibleGrid(Map.of("character", new GameSelection(characterFactory, games)),
        "character _", List.of(2, 1), List.of(1));
  }

  @Override
  public void sendKeys(List<Key> keys) {
    if (keys.contains(Key.ESCAPE)) {
      onExit.run();
    }
    else {
      super.sendKeys(keys);
    }
  }
}
