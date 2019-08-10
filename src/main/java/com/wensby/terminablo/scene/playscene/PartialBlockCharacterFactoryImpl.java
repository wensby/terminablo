package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.util.UnitInterval;

import java.util.List;

public class PartialBlockCharacterFactoryImpl implements PartialBlockCharacterFactory {

  private static final List<Character> CHARACTERS = List
      .of(' ', '▁', '▂', '▃', '▄', '▅', '▆', '▇', '█');

  @Override
  public char getPartialBlockCharacter(UnitInterval interval) {
    requireNonNull(interval);
    return CHARACTERS.get(interval.toIntRoundedDown(CHARACTERS.size()));
  }
}
