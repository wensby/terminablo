package com.wensby.terminablo.scene.playscene;

import com.wensby.Renderer;
import com.wensby.terminablo.Validate;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.*;

import java.awt.*;
import java.util.Objects;

public class HealthBarRenderer implements Renderer {

  public static final int ROWS = 3;
  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  private final String name;
  private final float lifePercentage;
  private final String type;
  private final String subType;

  public HealthBarRenderer(TerminalLayerFactory layerFactory, TerminalCharacterFactory characterFactory, String name, float lifePercentage, String type, String subType) {
    this.layerFactory = Objects.requireNonNull(layerFactory);
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.name = Objects.requireNonNull(name);
    this.lifePercentage = Validate.requireUnitInterval(lifePercentage);
    this.type = type;
    this.subType = subType;
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(size);
    renderHealthSection(layer);
    renderTypeSection(layer);
    renderSubtypeSection(layer);
    return layer;
  }

  private void renderHealthSection(TerminalLayer layer) {
    var height = layer.getSize().getHeight();
    if (height > 0) {
      layer.put(characterFactory.createCharacter(name.charAt(0), Color.WHITE, Color.RED), InterfaceLocation.at(0, 0));
    }
  }

  private void renderTypeSection(TerminalLayer layer) {
    var height = layer.getSize().getHeight();
    if (height > 1) {
      layer.put(characterFactory.createCharacter(type.charAt(0)), InterfaceLocation.at(0, 1));
    }
  }

  private void renderSubtypeSection(TerminalLayer layer) {
    var height = layer.getSize().getHeight();
    if (height > 2) {
      layer.put(characterFactory.createCharacter(subType.charAt(0)), InterfaceLocation.at(0, 2));
    }
  }
}
