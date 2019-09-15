package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.Game;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ComponentFactory;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class GameSelectionPage extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExit;
  private final List<Game> games;

  public GameSelectionPage(ComponentFactory componentFactory, TerminalCharacterFactory characterFactory, Runnable onExit, List<Game> games) {
    this.componentFactory = requireNonNull(componentFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.onExit = requireNonNull(onExit);
    this.games = requireNonNull(games);
  }

  @Override
  public Component render() {
    return componentFactory.aFlexibleGrid()
        .withChild("character", new GameSelection(componentFactory, characterFactory, games))
        .withColumnRatios(List.of(2, 1))
        .addRow(List.of("character", "_"), 1)
        .build();
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
