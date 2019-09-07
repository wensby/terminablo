package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.userinterface.reactive.Text;

import java.util.Objects;

public class CharacterSelection extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;

  public CharacterSelection(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  @Override
  public Component render() {
    return new Text(characterFactory, "characterSelection", false);
  }
}
