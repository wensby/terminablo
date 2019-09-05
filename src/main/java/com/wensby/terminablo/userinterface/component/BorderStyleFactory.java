package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;

import java.util.Objects;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;

public class BorderStyleFactory {

  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  public BorderStyleFactory(TerminalLayerFactory layerFactory, TerminalCharacterFactory characterFactory) {
    this.layerFactory = Objects.requireNonNull(layerFactory);
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  public BorderStyle createButtonBorderStyle() {
    var topLeftLayer = layerFactory.createBlankLayer(InterfaceSize.of(1, 1));
    topLeftLayer.put(characterFactory.createCharacter('╭'), atOrigin());
    var topRightLayer = layerFactory.createBlankLayer(InterfaceSize.of(1, 1));
    topRightLayer.put(characterFactory.createCharacter('╮'), atOrigin());
    var bottomLeftLayer = layerFactory.createBlankLayer(InterfaceSize.of(1, 1));
    bottomLeftLayer.put(characterFactory.createCharacter('╰'), atOrigin());
    var bottomRightLayer = layerFactory.createBlankLayer(InterfaceSize.of(1, 1));
    bottomRightLayer.put(characterFactory.createCharacter('╯'), atOrigin());
    BorderCorner topLeft = availableSize -> topLeftLayer;
    BorderCorner topRight = availableSize -> topRightLayer;
    BorderCorner bottomLeft = availableSize -> bottomLeftLayer;
    BorderCorner bottomRight = availableSize -> bottomRightLayer;
    BorderStraight topStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(l, 1));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('─'), at(i, 0));
      }
      return layer;
    };
    BorderStraight bottomStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(l, 1));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('─'), at(i, 0));
      }
      return layer;
    };
    BorderStraight leftStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(1, l));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('│'), at(0, i));
      }
      return layer;
    };
    BorderStraight rightStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(1, l));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('│'), at(0, i));
      }
      return layer;
    };
    return new BorderStyle(topLeft, topRight, bottomLeft, bottomRight, topStraight, bottomStraight, leftStraight, rightStraight);
  }
}
