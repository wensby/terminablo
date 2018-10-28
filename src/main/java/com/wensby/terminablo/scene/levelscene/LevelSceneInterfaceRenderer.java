package com.wensby.terminablo.scene.levelscene;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.terminablo.world.Agent;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.util.Fraction;
import java.awt.Color;
import java.math.BigDecimal;

public class LevelSceneInterfaceRenderer {

  private final TerminalOrbRenderer orbRenderer;
  private final TerminalLayerFactory terminalLayerFactory;
  private final TerminalCharacterFactory terminalCharacterFactory;

  public LevelSceneInterfaceRenderer(
      TerminalOrbRenderer orbRenderer,
      TerminalLayerFactory terminalLayerFactory,
      final TerminalCharacterFactory terminalCharacterFactory) {
    this.orbRenderer = orbRenderer;
    this.terminalLayerFactory = terminalLayerFactory;
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  public TerminalLayer render(Agent hero, InterfaceSize size) {
    TerminalLayer terminalLayer = terminalLayerFactory.createBlankLayer(size);
    renderHero(hero, terminalLayer);
    renderOrbs(hero, terminalLayer);
    return terminalLayer;
  }

  private void renderHero(Agent hero, TerminalLayer layer) {
    InterfaceSize layerSize = layer.getSize();
    int midColumn = layerSize.getWidth() / 2;
    int midRow = layerSize.getHeight() / 2;
    InterfaceLocation layerMidpoint = InterfaceLocation.of(midColumn, midRow);
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
    var orbSize = getOrbSize(terminalLayer.getSize().getWidth());
    var healthOrb = new DefaultOrb("HP", Color.RED, new Fraction(hero.getStats().getLife(), hero.getStats().getMaxLife()));
    var healthOrbRepresentation = orbRenderer.render(healthOrb, orbSize);
    int top = terminalLayer.getSize().getHeight() - orbSize.getHeight();
    var healthOrbPosition = InterfaceLocation.of(0, top);
    terminalLayer.put(healthOrbRepresentation, healthOrbPosition);
    var manaOrb = new DefaultOrb("MP", Color.BLUE, new Fraction(BigDecimal.TEN, new BigDecimal(100)));
    var manaOrbRepresentation = orbRenderer.render(manaOrb, orbSize);
    var manaOrbPosition = InterfaceLocation.of(terminalLayer.getSize().getWidth() - orbSize.getWidth(), top);
    terminalLayer.put(manaOrbRepresentation, manaOrbPosition);
  }

  private InterfaceSize getOrbSize(int width) {
    int orbHeight = Math.max(1, width / 15);
    int orbWidth = orbHeight > 1 ? orbHeight * 2 : orbHeight;
    return InterfaceSize.of(orbWidth, orbHeight);
  }
}
