package com.wensby.application.userinterface;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LayerDifferenceCalculator {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayer prev;
  private final TerminalLayer next;

  private List<PositionedTerminalCharacter> prevCharacters;
  private Map<InterfaceLocation, PositionedTerminalCharacter> prevCharactersByLocation;
  private List<PositionedTerminalCharacter> nextCharacters;
  private Map<InterfaceLocation, PositionedTerminalCharacter> nextCharactersByLocation;

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
      prevCharactersByLocation = getCharacterByLocation(prevCharacters);
      nextCharacters = next.getPositionedCharacters();
      nextCharactersByLocation = getCharacterByLocation(nextCharacters);
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
    return Stream.of(prevCharactersByLocation, nextCharactersByLocation)
        .map(Map::keySet)
        .flatMap(Set::stream)
        .filter(location -> !unchangedLocations.contains(location))
        .filter(this::isPrecedingNextCharacterShort)
        .collect(toSet());

  }

  private boolean isPrecedingNextCharacterShort(InterfaceLocation location) {
    var precedingLocation = location.plus(InterfaceLocation.at(-1, 0));
    var precedingNextCharacter = nextCharactersByLocation.getOrDefault(precedingLocation, null);
    return precedingNextCharacter == null || precedingNextCharacter.getCharacter().getRenderLength() <= 1;
  }

  private PositionedTerminalCharacter getDifference(InterfaceLocation location) {
    if (prevCharactersByLocation.containsKey(location)) {
      if (nextCharactersByLocation.containsKey(location)) {
        return nextCharactersByLocation.get(location);
      }
      else {
        if (prevCharactersByLocation.get(location).getCharacter().getRenderLength() > 1 && !isSucceedingNextCharacterPresent(location)) {
          return new PositionedTerminalCharacter(location, characterFactory.createCharacter("  "));
        }
        else {
          return new PositionedTerminalCharacter(location, characterFactory.createCharacter(' '));
        }
      }
    }
    else {
      return nextCharactersByLocation.get(location);
    }
  }

  private boolean isSucceedingNextCharacterPresent(InterfaceLocation location) {
    return findNextCharacter(location.plus(InterfaceLocation.at(1, 0))).isPresent();
  }

  private Optional<PositionedTerminalCharacter> findNextCharacter(InterfaceLocation location) {
    return Optional.ofNullable(nextCharactersByLocation.getOrDefault(location, null));
  }

  private Map<InterfaceLocation, PositionedTerminalCharacter> getCharacterByLocation(List<PositionedTerminalCharacter> characters) {
    return characters.stream()
        .collect(toMap(PositionedTerminalCharacter::getLocation, c -> c));
  }
}
