package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LayerDifferenceCalculator {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayer prev;
  private final TerminalLayer next;

  private List<PositionedTerminalCharacter> prevCharacters;
  private Map<InterfaceLocation, PositionedTerminalCharacter> prevCharactersByLocaction;
  private List<PositionedTerminalCharacter> nextCharacters;
  private Map<InterfaceLocation, PositionedTerminalCharacter> nextCharactersByLocaction;

  LayerDifferenceCalculator(TerminalCharacterFactory characterFactory, TerminalLayer prev, TerminalLayer next) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.prev = prev; // can be null
    this.next = Objects.requireNonNull(next);
  }

  /**
   * @return a list at positioned characters, sorted by column and row.
   */
  public List<PositionedTerminalCharacter> getDifference() {
    if (prev == null) {
      return next.getPositionedCharacters();
    }
    else {
      prevCharacters = prev.getPositionedCharacters();
      prevCharactersByLocaction = getCharacterByLocation(prevCharacters);
      nextCharacters = next.getPositionedCharacters();
      nextCharactersByLocaction = getCharacterByLocation(nextCharacters);
      return getChangedLocations().stream()
          .map(this::getDifference)
          .collect(toList());
    }
  }

  private Set<InterfaceLocation> getChangedLocations() {
    var unchangedLocations = nextCharacters.stream()
        .filter(prevCharacters::contains)
        .map(PositionedTerminalCharacter::getLocation)
        .collect(toList());
    return Stream.of(prevCharactersByLocaction, nextCharactersByLocaction)
        .map(Map::keySet)
        .flatMap(Set::stream)
        .filter(location -> !unchangedLocations.contains(location))
        .collect(toSet());

  }

  private PositionedTerminalCharacter getDifference(InterfaceLocation location) {
    if (prevCharactersByLocaction.containsKey(location)) {
      if (nextCharactersByLocaction.containsKey(location)) {
        return nextCharactersByLocaction.get(location);
      }
      else {
        if (prevCharactersByLocaction.get(location).getCharacter().getRenderLength() > 1) {
          return new PositionedTerminalCharacter(location, characterFactory.createCharacter("  "));
        }
        else {
          return new PositionedTerminalCharacter(location, characterFactory.createCharacter(' '));
        }
      }
    }
    else {
      return nextCharactersByLocaction.get(location);
    }
  }

  private Map<InterfaceLocation, PositionedTerminalCharacter> getCharacterByLocation(List<PositionedTerminalCharacter> characters) {
    return characters.stream()
        .collect(toMap(PositionedTerminalCharacter::getLocation, c -> c));
  }
}
