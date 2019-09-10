package com.wensby.terminablo.scene.playscene;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.Validate;
import com.wensby.application.userinterface.InterfaceLocation;

import java.awt.*;
import java.util.Objects;

public class HealthBarRenderer {

  public static final int ROWS = 3;
  private final TerminalCharacterFactory characterFactory;

  private final String name;
  private final float lifePercentage;
  private final String type;
  private final String subType;

  public HealthBarRenderer(TerminalCharacterFactory characterFactory, String name, float lifePercentage, String type, String subType) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.name = Objects.requireNonNull(name);
    this.lifePercentage = Validate.requireUnitInterval(lifePercentage);
    this.type = type;
    this.subType = subType;
  }

  public void render(TerminalLayer layer) {
    renderHealthSection(layer);
    renderTypeSection(layer);
    renderSubtypeSection(layer);
  }

  private void renderHealthSection(TerminalLayer layer) {
    var height = layer.size().getHeight();
    if (height > 0) {
      var decoration = new CharacterDecoration(Color.RED, Color.WHITE, false);
      for (int i = 0; i < name.length(); i++) {
        layer.put(characterFactory.createCharacter(name.charAt(i), decoration), InterfaceLocation.at(i, 0));
      }
    }
  }

  private void renderTypeSection(TerminalLayer layer) {
    var height = layer.size().getHeight();
    if (height > 1) {
      layer.put(characterFactory.createCharacter(type.charAt(0)), InterfaceLocation.at(0, 1));
    }
  }

  private void renderSubtypeSection(TerminalLayer layer) {
    var height = layer.size().getHeight();
    if (height > 2) {
      layer.put(characterFactory.createCharacter(subType.charAt(0)), InterfaceLocation.at(0, 2));
    }
  }
}
