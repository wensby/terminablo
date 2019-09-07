package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Grid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CharacterSelectionPage extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;

  public CharacterSelectionPage(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  @Override
  public Component render() {
    return new Grid(Map.of("character", new CharacterSelection(characterFactory)),
        "character _", List.of(2, 1), List.of(1));
  }
}
