package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.FlexibleGrid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameSelectionPage extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExit;

  public GameSelectionPage(TerminalCharacterFactory characterFactory, Runnable onExit) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.onExit = Objects.requireNonNull(onExit);
  }

  @Override
  public Component render() {
    return new FlexibleGrid(Map.of("character", new GameSelection(characterFactory)),
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
