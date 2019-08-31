package com.wensby.terminablo;

import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelLocation;

import java.util.Optional;

public class TerminalLevelRenderer {

  private final LevelEntityRenderer entityRenderer;

  public TerminalLevelRenderer(LevelEntityRenderer entityRenderer) {
    this.entityRenderer = entityRenderer;
  }

  public void render(Level level, LevelLocation location, TerminalLayerPainter painter) {
    var interfaceCenter = InterfaceLocation.at(painter.getAvailableSize().getWidth() / 2, painter.getAvailableSize().getHeight() / 2);
    var topLeftInterfacePosition = topLeftInterfacePosition(interfaceCenter);
    var topLeftLevelLocation = topLeftLevelLocation(topLeftInterfacePosition, location, interfaceCenter);

    for (int y = 0; y < painter.getAvailableSize().getHeight(); y++) {
      for (int x = 0; x < painter.getAvailableSize().getWidth() / 2; x++) {
        var layerPosition = topLeftInterfacePosition.plus(InterfaceLocation.at(x * 2, y));
        var levelLocation = LevelLocation.of(topLeftLevelLocation.getX() + x, topLeftLevelLocation.getY() + y);
        level.entities(levelLocation).stream()
            .map(entityRenderer::getTerminalCharacter)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(character -> painter.put(character, layerPosition));
      }
    }
  }

  private InterfaceLocation topLeftInterfacePosition(InterfaceLocation center) {
    return InterfaceLocation.at(center.getColumn() % 2, 0);
  }

  private LevelLocation topLeftLevelLocation(InterfaceLocation topLeftInterfacePosition,
      LevelLocation levelCenter, final InterfaceLocation interfaceCenter) {
    final int left = (interfaceCenter.getColumn() - topLeftInterfacePosition.getColumn()) / 2;
    return LevelLocation.of(levelCenter.getX() - left, levelCenter.getY() - interfaceCenter.getRow());
  }
}
