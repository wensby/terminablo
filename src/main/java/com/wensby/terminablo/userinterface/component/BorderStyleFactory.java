package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.*;

import java.util.Objects;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;
import static java.awt.Color.*;

public class BorderStyleFactory {

  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  public BorderStyleFactory(TerminalLayerFactory layerFactory, TerminalCharacterFactory characterFactory) {
    this.layerFactory = Objects.requireNonNull(layerFactory);
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  public BorderStyle createButtonBorderStyle() {
    var noneYellow = new CharacterDecoration(null, YELLOW, false);
    var yellowBlack = new CharacterDecoration(YELLOW, BLACK, false);
    var topLeftLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    topLeftLayer.put(characterFactory.createCharacter('╔', yellowBlack), atOrigin());
    topLeftLayer.put(characterFactory.createCharacter('◤', noneYellow), at(1, 0));
    var topRightLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    topRightLayer.put(characterFactory.createCharacter('╗', yellowBlack), at(1, 0));
    topRightLayer.put(characterFactory.createCharacter('◥', noneYellow), atOrigin());
    var bottomLeftLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    bottomLeftLayer.put(characterFactory.createCharacter('╚', yellowBlack), atOrigin());
    bottomLeftLayer.put(characterFactory.createCharacter('◣', noneYellow), at(1, 0));
    var bottomRightLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    bottomRightLayer.put(characterFactory.createCharacter('◢', noneYellow), atOrigin());
    bottomRightLayer.put(characterFactory.createCharacter('╝', yellowBlack), at(1, 0));
    BorderCorner topLeft = availableSize -> topLeftLayer;
    BorderCorner topRight = availableSize -> topRightLayer;
    BorderCorner bottomLeft = availableSize -> bottomLeftLayer;
    BorderCorner bottomRight = availableSize -> bottomRightLayer;
    BorderStraight topStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(l, 1));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('─', noneYellow), at(i, 0));
      }
      return layer;
    };
    BorderStraight bottomStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(l, 1));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('─', noneYellow), at(i, 0));

      }
      return layer;
    };
    BorderStraight straight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(1, l));
      for (int i = 0; i < l; i++) {
        if (i == l / 2) {
          layer.put(characterFactory.createCharacter('●', new CharacterDecoration(YELLOW, BLUE, false)), at(0, i));
        }
        else {
          layer.put(characterFactory.createCharacter('║', yellowBlack), at(0, i));
        }
      }
      return layer;
    };
    return new BorderStyle(topLeft, topRight, bottomLeft, bottomRight, topStraight, bottomStraight, straight, straight);
  }

  public BorderStyle createSelectedButtonBorderStyle() {
    var noneYellow = new CharacterDecoration(null, YELLOW, false);
    var yellowBlack = new CharacterDecoration(YELLOW, BLACK, false);
    var topLeftLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    topLeftLayer.put(characterFactory.createCharacter('╔', yellowBlack), atOrigin());
    topLeftLayer.put(characterFactory.createCharacter('◤', noneYellow), at(1, 0));
    var topRightLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    topRightLayer.put(characterFactory.createCharacter('╗', yellowBlack), at(1, 0));
    topRightLayer.put(characterFactory.createCharacter('◥', noneYellow), atOrigin());
    var bottomLeftLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    bottomLeftLayer.put(characterFactory.createCharacter('╚', yellowBlack), atOrigin());
    bottomLeftLayer.put(characterFactory.createCharacter('◣', noneYellow), at(1, 0));
    var bottomRightLayer = layerFactory.createBlankLayer(InterfaceSize.of(2, 1));
    bottomRightLayer.put(characterFactory.createCharacter('◢', noneYellow), atOrigin());
    bottomRightLayer.put(characterFactory.createCharacter('╝', yellowBlack), at(1, 0));
    BorderCorner topLeft = availableSize -> topLeftLayer;
    BorderCorner topRight = availableSize -> topRightLayer;
    BorderCorner bottomLeft = availableSize -> bottomLeftLayer;
    BorderCorner bottomRight = availableSize -> bottomRightLayer;
    BorderStraight topStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(l, 1));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('━', noneYellow), at(i, 0));
      }
      return layer;
    };
    BorderStraight bottomStraight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(l, 1));
      for (int i = 0; i < l; i++) {
        layer.put(characterFactory.createCharacter('━', noneYellow), at(i, 0));
      }
      return layer;
    };
    BorderStraight straight = l -> {
      var layer = layerFactory.createBlankLayer(InterfaceSize.of(1, l));
      for (int i = 0; i < l; i++) {
        if (i == l / 2) {
          layer.put(characterFactory.createCharacter('●', new CharacterDecoration(YELLOW, BLUE, false)), at(0, i));
        }
        else {
          layer.put(characterFactory.createCharacter('║', yellowBlack), at(0, i));
        }
      }
      return layer;
    };
    return new BorderStyle(topLeft, topRight, bottomLeft, bottomRight, topStraight, bottomStraight, straight, straight);
  }
}
