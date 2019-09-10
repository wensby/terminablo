package com.wensby.application.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;

public class TwoDimensionalCharacterArrayLayer implements TerminalLayer {

  private final TerminalCharacter[][] characters;

  public TwoDimensionalCharacterArrayLayer(InterfaceSize size) {
    this.characters = new TerminalCharacter[size.getHeight()][size.getWidth()];
  }

  public TerminalCharacter[][] getCharacters() {
    return characters;
  }

  @Override
  public List<PositionedTerminalCharacter> getPositionedCharacters() {
    var positionedCharacters = new ArrayList<PositionedTerminalCharacter>();
    for (int row = 0; row < characters.length; row++) {
      for (int column = 0; column < characters[0].length; column++) {
        var character = characters[row][column];
        if (character != null) {
          positionedCharacters.add(new PositionedTerminalCharacter(at(column, row), character));
        }
      }
    }
    return positionedCharacters;
  }

  @Override
  public InterfaceSize size() {
    return InterfaceSize.of(characters.length > 0 ? characters[0].length : 0, characters.length);
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation location) {
    var size = layer.size();
    for (int row = 0; row < size.getHeight(); row++) {
      for (int column = 0; column < size.getWidth(); column++) {
        var originLocation = at(column, row);
        var character = layer.getCharacter(originLocation);
        if (character != null) {
          var destinationPosition = originLocation.plus(location);
          if (!put(character, destinationPosition)) {
            break;
          }
        }
      }
    }
  }

  @Override
  public boolean put(TerminalCharacter character, InterfaceLocation location) {
    var row = location.getRow();
    var column = location.getColumn();
    int renderLength = character.getRenderLength();
    if (row < characters.length && (column + renderLength - 1) < characters[row].length) {
      characters[row][column] = character;
      clearOverwrittenCharacters(location, character);
      return true;
    }
    else {
      return false;
    }
  }

  private void clearOverwrittenCharacters(InterfaceLocation location, TerminalCharacter character) {
    clearOverwrittenPrecedingCharacter(location);
    clearOverwrittenSucceedingCharacters(location, character.getRenderLength() - 1);
  }

  private void clearOverwrittenSucceedingCharacters(InterfaceLocation location, int positions) {
    int row = location.getRow();
    int column = location.getColumn();
    for (int i = 1; i <= positions; i++) {
      characters[row][column + i] = null;
    }
  }

  private void clearOverwrittenPrecedingCharacter(InterfaceLocation location) {
    if (isLocationOverlappedByPrecedingCharacter(location)) {
      characters[location.getRow()][location.getColumn() -1] = null;
    }
  }

  private boolean isLocationOverlappedByPrecedingCharacter(InterfaceLocation location) {
    return findPrecedingCharacter(location)
        .map(TerminalCharacter::getRenderLength)
        .map(length -> length > 1)
        .orElse(false);
  }

  private Optional<TerminalCharacter> findPrecedingCharacter(InterfaceLocation location) {
    if (location.getColumn() > 0) {
      return Optional.ofNullable(characters[location.getRow()][location.getColumn() - 1]);
    }
    else {
      return Optional.empty();
    }
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation interfacePosition) {
    return characters[interfacePosition.getRow()][interfacePosition.getColumn()];
  }

  @Override
  public TerminalLayer getSubsection(InterfaceLocation topLeft, InterfaceSize size) {
    return ((TerminalLayer) new TerminalLayerSubsection(this, new TerminalLayerSection(atOrigin(), size()))).getSubsection(topLeft, size);
  }
}
