package com.wensby.application.userinterface;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class SparseLayer implements TerminalLayer {

  private final InterfaceSize size;
  private final Map<Integer, Map<Integer, PositionedTerminalCharacter>> columnCharactersByRow;

  public SparseLayer(InterfaceSize size) {
    this.size = size;
    columnCharactersByRow = new HashMap<>();
  }

  @Override
  public TerminalCharacter[][] getCharacters() {
    var characters = new TerminalCharacter[size.getHeight()][size.getWidth()];
    columnCharactersByRow.values().forEach(row -> putRow(row, characters));
    return characters;
  }

  private void putRow(Map<Integer, PositionedTerminalCharacter> row, TerminalCharacter[][] characters) {
    row.values().forEach(character -> putCharacter(character, characters));
  }

  private void putCharacter(PositionedTerminalCharacter character, TerminalCharacter[][] characters) {
    var location = character.getLocation();
    characters[location.getRow()][location.getColumn()] = character.getCharacter();
  }

  @Override
  public List<PositionedTerminalCharacter> getPositionedCharacters() {
    return columnCharactersByRow.values().stream()
        .map(Map::values)
        .flatMap(Collection::stream)
        .collect(toList());
  }

  @Override
  public InterfaceSize getSize() {
    return size;
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation location) {
    layer.getPositionedCharacters()
        .forEach(character -> put(character, location));
  }

  private void put(PositionedTerminalCharacter character, InterfaceLocation location) {
    put(character.getCharacter(), character.getLocation().plus(location));
  }

  @Override
  public boolean put(TerminalCharacter character, InterfaceLocation location) {
    var row = location.getRow();
    var column = location.getColumn();
    int renderLength = character.getRenderLength();
    if (row >= 0 && column >= 0 && row < size.getHeight() && (column + renderLength - 1) < size.getWidth()) {
      columnCharactersByRow.computeIfAbsent(row, integer -> new HashMap<>())
          .put(column, new PositionedTerminalCharacter(location, character));
      clearOverwrittenCharacters(location, character);
      return true;
    }
    return false;
  }

  private void clearOverwrittenCharacters(InterfaceLocation location, TerminalCharacter character) {
    clearOverwrittenPrecedingCharacter(location);
    clearOverwrittenSucceedingCharacters(location, character.getRenderLength() - 1);
  }

  private void clearOverwrittenPrecedingCharacter(InterfaceLocation location) {
    findPrecedingCharacter(location)
        .filter(character -> character.getCharacter().getRenderLength() > 1)
        .ifPresent(this::remove);
  }

  private void remove(PositionedTerminalCharacter character) {
    var location = character.getLocation();
    var row = columnCharactersByRow.getOrDefault(location.getRow(), null);
    if (row != null) {
      row.remove(location.getColumn());
    }
  }

  private void clearOverwrittenSucceedingCharacters(InterfaceLocation location, int positions) {
    var row = columnCharactersByRow.getOrDefault(location.getRow(), null);
    if (row != null) {
      for (int i = 1; i <= positions; i++) {
        row.remove(location.getColumn() + i);
      }
    }
  }

  private Optional<PositionedTerminalCharacter> findPrecedingCharacter(InterfaceLocation location) {
    return Optional.ofNullable(columnCharactersByRow.get(location.getRow()).getOrDefault(location.getColumn() - 1, null));
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation position) {
    if (columnCharactersByRow.containsKey(position.getRow())) {
      var row = columnCharactersByRow.get(position.getRow());
      if (row.containsKey(position.getColumn())) {
        return row.get(position.getColumn()).getCharacter();
      }
    }
    return null;
  }
}
