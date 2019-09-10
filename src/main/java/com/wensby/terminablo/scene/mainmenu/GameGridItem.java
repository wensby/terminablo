package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.CharacterDecoration;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.userinterface.DecoratedText;
import com.wensby.terminablo.userinterface.reactive.*;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Container;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameGridItem extends ReactiveComponent {

  private final PlayerCharacter character;
  private final boolean selected;
  private final TerminalCharacterFactory characterFactory;

  public GameGridItem(PlayerCharacter character, boolean selected, TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.character = Objects.requireNonNull(character);
    this.selected = selected;
  }

  @Override
  public Component render() {
    return new FlexibleGrid(Map.of(
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
            new Text(characterFactory, DecoratedText.blank().append(character.getName(), new CharacterDecoration(null, null, selected)), selected),
            new Text(characterFactory, DecoratedText.blank().append("Level " + character.getLevel() + " " + character.getCharacterClass().getName(), new CharacterDecoration(null, null, selected)), selected),
            new Text(characterFactory, DecoratedText.blank().append("EXPANSION CHARACTER", new CharacterDecoration(null, Color.GREEN, selected)), selected)
            )
    );
  }
}
