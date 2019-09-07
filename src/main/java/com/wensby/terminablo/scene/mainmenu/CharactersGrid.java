package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.world.level.LevelEntityImpl;

import java.util.List;

public class CharactersGrid extends ReactiveComponent {

  private final List<PlayerCharacter> characters;
  private final TerminalCharacterFactory characterFactory;

  public CharactersGrid(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
    characters = List.of(new PlayerCharacter("Dj", null, new LevelEntityImpl(characterFactory.createCharacter('A'))));
    setState("selectedCharacterIndex", 0);
  }

  @Override
  public Component render() {
    return new CharactersGridItem(characters.get(0), false, characterFactory);
  }
}
