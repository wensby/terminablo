package com.wensby.terminablo.scene;

import com.wensby.terminablo.world.Agent;
import com.wensby.userinterface.InterfacePosition;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;
import java.awt.Color;
import java.math.BigDecimal;

public class LevelSceneInterfaceRenderer {

  private final OrbTerminalRepresentationFactory orbTerminalRepresentationFactory;
  private final TerminalLayerFactory terminalLayerFactory;
  private final TerminalCharacterFactory terminalCharacterFactory;

  public LevelSceneInterfaceRenderer(
      OrbTerminalRepresentationFactory orbTerminalRepresentationFactory,
      TerminalLayerFactory terminalLayerFactory,
      final TerminalCharacterFactory terminalCharacterFactory) {
    this.orbTerminalRepresentationFactory = orbTerminalRepresentationFactory;
    this.terminalLayerFactory = terminalLayerFactory;
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  public TerminalLayer render(Agent hero, InterfaceSize size) {
    TerminalLayer terminalLayer = terminalLayerFactory.createTerminalLayer(size);
    renderHero(hero, terminalLayer);
    renderOrbs(hero, terminalLayer);
    return terminalLayer;
  }

  private void renderHero(Agent hero, TerminalLayer layer) {
    InterfaceSize layerSize = layer.getSize();
    int midColumn = layerSize.getWidth() / 2;
    int midRow = layerSize.getHeight() / 2;
    InterfacePosition layerMidpoint = InterfacePosition.of(midColumn, midRow);
    TerminalCharacter character;
    if (hero.getStats().getLife().compareTo(BigDecimal.ZERO) == 0) {
      character = terminalCharacterFactory.createCharacter("\uD83D\uDC80");
    } else if (hero.getStats().getLife().compareTo(BigDecimal.TEN) < 0) {
      character = terminalCharacterFactory.createCharacter("\uD83D\uDE41");
    } else {
      character = terminalCharacterFactory.createCharacter("\uD83D\uDE42");
    }
    layer.put(layerMidpoint, character);
  }

  private void renderOrbs(final Agent hero, final TerminalLayer terminalLayer) {
    InterfaceSize orbSize = getOrbSize(terminalLayer.getSize().getWidth());
    Orb healthOrb = new DefaultOrb("HP", Color.RED, hero.getStats().getMaxLife(),
        hero.getStats().getLife());
    TerminalLayer healthOrbRepresentation = orbTerminalRepresentationFactory
        .createRepresentation(healthOrb, orbSize);
    int top = terminalLayer.getSize().getHeight() - orbSize.getHeight();
    InterfacePosition healthOrbPosition = InterfacePosition.of(0, top);
    terminalLayer.put(healthOrbRepresentation, healthOrbPosition);
    Orb manaOrb = new DefaultOrb("MP", Color.BLUE, BigDecimal.TEN, BigDecimal.TEN);
    TerminalLayer manaOrbRepresentation = orbTerminalRepresentationFactory
        .createRepresentation(manaOrb, orbSize);
    InterfacePosition manaOrbPosition = InterfacePosition
        .of(terminalLayer.getSize().getWidth() - orbSize.getWidth(), top);
    terminalLayer.put(manaOrbRepresentation, manaOrbPosition);
  }

  private InterfaceSize getOrbSize(int width) {
    int orbHeight = Math.max(1, width / 15);
    int orbWidth = orbHeight > 1 ? orbHeight * 2 : orbHeight;
    return new InterfaceSize(orbWidth, orbHeight);
  }
}
