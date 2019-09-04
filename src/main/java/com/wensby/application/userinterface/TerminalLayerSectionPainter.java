package com.wensby.application.userinterface;

import java.util.Objects;

public class TerminalLayerSectionPainter implements TerminalLayerPainter {

  private final TerminalLayer layer;
  private final TerminalLayerSection section;

  public TerminalLayerSectionPainter(TerminalLayer layer, TerminalLayerSection section) {
    this.layer = Objects.requireNonNull(layer);
    this.section = Objects.requireNonNull(section);
  }

  @Override
  public InterfaceSize getAvailableSize() {
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
  public TerminalLayerPainter createSubsectionPainter(InterfaceLocation topLeft, InterfaceSize size) {
    return new TerminalLayerSectionPainter(layer, this.section.createSubsection(topLeft, size));
  }
}
