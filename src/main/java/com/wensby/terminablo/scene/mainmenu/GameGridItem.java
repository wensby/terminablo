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

import static java.util.Objects.requireNonNull;

public class GameGridItem extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final PlayerCharacter character;
  private final boolean selected;
  private final TerminalCharacterFactory characterFactory;

  public GameGridItem(ComponentFactory componentFactory, PlayerCharacter character, boolean selected, TerminalCharacterFactory characterFactory) {
    this.componentFactory = requireNonNull(componentFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.character = requireNonNull(character);
    this.selected = selected;
  }

  @Override
  public Component render() {
    return componentFactory.aFlexibleGrid()
        .withChild("visual", createVisual())
        .withChild("details", createCharacterDetails())
        .withColumnRatios(List.of(1, 3))
        .addRow(List.of("visual", "details"), 1)
        .build();
  }

  private Component createVisual() {

    return new Graphic(characterFactory.createCharacter(' '));
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
