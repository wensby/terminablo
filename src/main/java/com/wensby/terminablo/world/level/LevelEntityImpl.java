package com.wensby.terminablo.world.level;

public class LevelEntityImpl implements LevelEntity {

  private final CharSequence charSeq;

  public LevelEntityImpl(CharSequence charSeq) {
    this.charSeq = charSeq;
  }

  @Override
  public CharSequence getCharacter() {
    return charSeq;
  }
}
