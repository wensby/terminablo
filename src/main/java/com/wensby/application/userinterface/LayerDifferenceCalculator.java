package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class LayerDifferenceCalculator {

  private final TerminalCharacterFactory characterFactory;

  public LayerDifferenceCalculator(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  /**
   * @return a list at positioned characters, sorted by column and row.
   */
  public List<PositionedTerminalCharacter> getDifference(TerminalLayer prev, TerminalLayer next) {
    if (prev == null) {
      return next.getPositionedCharacters();
    }
    else {
      var prevCharacters = prev.getPositionedCharacters();
      var prevCharactersByLoc = getCharacterByLocationMap(prevCharacters);
      var nextCharacters = next.getPositionedCharacters();
      var nextCharactersByLoc = getCharacterByLocationMap(nextCharacters);
      return getChangedLocations(prevCharactersByLoc, nextCharactersByLoc, nextCharacters, prevCharacters).parallelStream()
          .map(a -> getDifferenceCharacter(prevCharactersByLoc, nextCharactersByLoc, a))
          .collect(toList());
    }
  }

  private PositionedTerminalCharacter getDifferenceCharacter(Map<InterfaceLocation, PositionedTerminalCharacter> prevCharactersByLoc, Map<InterfaceLocation, PositionedTerminalCharacter> nextCharactersByLoc, InterfaceLocation location) {
    if (prevCharactersByLoc.containsKey(location)) {
      if (nextCharactersByLoc.containsKey(location)) {
        return nextCharactersByLoc.get(location);
      }
      else {
        if (prevCharactersByLoc.get(location).getCharacter().getRenderLength() > 1) {
          return new PositionedTerminalCharacter(location, characterFactory.createCharacter("  "));
        }
        else {
          return new PositionedTerminalCharacter(location, characterFactory.createCharacter(' '));
        }
      }
    }
    else {
      return nextCharactersByLoc.get(location);
    }
  }

  private HashSet<InterfaceLocation> getChangedLocations(Map<InterfaceLocation, PositionedTerminalCharacter> prevCharactersByLoc, Map<InterfaceLocation, PositionedTerminalCharacter> nextCharactersByLoc, List<PositionedTerminalCharacter> nextCharacters, List<PositionedTerminalCharacter> prevCharacters) {
    var changedLocations = new HashSet<InterfaceLocation>();
    changedLocations.addAll(prevCharactersByLoc.keySet());
    changedLocations.addAll(nextCharactersByLoc.keySet());
    var unchangedLocations = nextCharacters.parallelStream()
        .filter(prevCharacters::contains)
        .map(PositionedTerminalCharacter::getLocation)
        .collect(toList());
    changedLocations.removeAll(unchangedLocations);
    return changedLocations;
  }

  private Map<InterfaceLocation, PositionedTerminalCharacter> getCharacterByLocationMap(List<PositionedTerminalCharacter> positionedCharacters) {
    return positionedCharacters.parallelStream().collect(toMap(PositionedTerminalCharacter::getLocation, c -> c));
  }

}
