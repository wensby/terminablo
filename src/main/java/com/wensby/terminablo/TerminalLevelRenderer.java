package com.wensby.terminablo;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;

public class TerminalLevelRenderer {

  private final TerminalLayerFactory layerFactory;
  private final LevelEntityRenderer entityRenderer;

  public TerminalLevelRenderer(TerminalLayerFactory layerFactory,
      LevelEntityRenderer entityRenderer) {
    this.layerFactory = layerFactory;
    this.entityRenderer = entityRenderer;
  }

  public TerminalLayer render(Level level, LevelLocation location, InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(size);
    var interfaceCenter = InterfaceLocation.of(size.getWidth() / 2, size.getHeight() / 2);
    var topLeftInterfacePosition = topLeftInterfacePosition(interfaceCenter);
    var topLeftLevelLocation = topLeftLevelLocation(topLeftInterfacePosition, location, interfaceCenter);

    for (int y = 0; y < size.getHeight(); y++) {
      for (int x = 0; x < size.getWidth() / 2; x++) {
        var layerPosition = topLeftInterfacePosition.plus(InterfaceLocation.of(x * 2, y));
        var levelLocation = LevelLocation.of(topLeftLevelLocation.getX() + x, topLeftLevelLocation.getY() + y);
        level.entities(levelLocation).stream()
            .map(entityRenderer::getTerminalCharacter)
            .forEach(character -> layer.put(layerPosition, character));
      }
    }
    return layer;
  }

  private InterfaceLocation topLeftInterfacePosition(InterfaceLocation center) {
    return InterfaceLocation.of(center.getColumn() % 2, 0);
  }

  private LevelLocation topLeftLevelLocation(InterfaceLocation topLeftInterfacePosition,
      LevelLocation levelCenter, final InterfaceLocation interfaceCenter) {
    final int left = (interfaceCenter.getColumn() - topLeftInterfacePosition.getColumn()) / 2;
    return LevelLocation.of(levelCenter.getX() - left, levelCenter.getY() - interfaceCenter.getRow());
  }
}
