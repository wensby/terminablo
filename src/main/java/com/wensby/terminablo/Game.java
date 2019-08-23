package com.wensby.terminablo;

import com.wensby.terminablo.player.PlayerCharacter;

import java.util.Objects;

public class Game {

  private final PlayerCharacter character;

  public Game(PlayerCharacter character) {
    this.character = Objects.requireNonNull(character);
  }
}
