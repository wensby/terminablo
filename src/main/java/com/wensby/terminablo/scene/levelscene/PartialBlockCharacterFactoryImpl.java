package com.wensby.terminablo.scene.levelscene;

import static java.util.Objects.requireNonNull;

import com.wensby.util.UnitInterval;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PartialBlockCharacterFactoryImpl implements PartialBlockCharacterFactory {

  private static final List<Character> CHARACTERS = List
      .of(' ', '▁', '▂', '▃', '▄', '▅', '▆', '▇', '█');

  @Override
  public char getPartialBlockCharacter(UnitInterval interval) {
    requireNonNull(interval);
    return CHARACTERS.get(interval.toIntRoundedDown(CHARACTERS.size()));
  }
}
