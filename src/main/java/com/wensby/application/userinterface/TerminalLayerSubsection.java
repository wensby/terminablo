package com.wensby.application.userinterface;

import java.util.List;
import java.util.Objects;

public class TerminalLayerSubsection implements TerminalLayer {

  private final TerminalLayer layer;
  private final TerminalLayerSection section;

  public TerminalLayerSubsection(TerminalLayer layer, TerminalLayerSection section) {
    this.layer = Objects.requireNonNull(layer);
    this.section = Objects.requireNonNull(section);
  }

  @Override
  public InterfaceSize getSize() {
    return section.getSize();
  }

  @Override
  public boolean put(TerminalCharacter character, InterfaceLocation location) {
    if (section.containsRelativeLocation(location)) {
      if (!isLongCharacterOnLastColumn(character, location)) {
        return layer.put(character, section.getAbsoluteLocation(location));
      }
    }
    return false;
  }

  @Override
  public TerminalCharacter getCharacter(InterfaceLocation position) {
    return layer.getCharacter(section.getAbsoluteLocation(position));
  }

  @Override
  public List<PositionedTerminalCharacter> getPositionedCharacters() {
    return null;
  }

  @Override
  public void put(TerminalLayer layer, InterfaceLocation location) {
    layer.getPositionedCharacters().forEach(character -> put(character, location));
  }

  private void put(PositionedTerminalCharacter character, InterfaceLocation location) {
    this.put(character.getCharacter(), character.getLocation().plus(location));
  }

  private boolean isLongCharacterOnLastColumn(TerminalCharacter character, InterfaceLocation location) {
    return character.getRenderLength() > 1 && location.getColumn() == section.getSize().getWidth() - 1;
  }

  @Override
  public TerminalLayerSubsection getSubsection(InterfaceLocation topLeft, InterfaceSize size) {
    return new TerminalLayerSubsection(this, this.section.createSection(topLeft, size));
  }
}
