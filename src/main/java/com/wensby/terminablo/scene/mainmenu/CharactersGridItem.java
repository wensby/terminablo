package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.userinterface.reactive.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CharactersGridItem extends ReactiveComponent {

  private final PlayerCharacter character;
  private final boolean selected;
  private final TerminalCharacterFactory characterFactory;

  public CharactersGridItem(PlayerCharacter character, boolean selected, TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.character = Objects.requireNonNull(character);
    this.selected = selected;
  }

  @Override
  public Component render() {
    return new Grid(Map.of(
        "visual", createVisual(),
        "details", createCharacterDetails()),
        "visual details", List.of(1, 3), List.of(1)
    );
  }

  private Component createVisual() {
    return new Graphic(character.getLevelEntity().getCharacter().orElse(characterFactory.createCharacter(' ')));
  }

  private Container createCharacterDetails() {
    return new Container(
        List.of(
            new Text(characterFactory, character.getName(), selected),
            new Text(characterFactory, "Level " + character.getLevel() + " " + character.getCharacterClass().getName(), selected),
            new Text(characterFactory, "EXPANSION CHARACTER", selected)
            )
    );
  }
}
