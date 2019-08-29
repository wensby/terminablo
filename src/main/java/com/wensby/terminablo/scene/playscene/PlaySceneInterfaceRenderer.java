package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.terminablo.world.Agent;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;
import com.wensby.terminablo.util.Fraction;
import java.awt.Color;
import java.math.BigDecimal;

public class PlaySceneInterfaceRenderer {

  private final TerminalOrbRenderer orbRenderer;
  private final TerminalLayerFactory terminalLayerFactory;
  private final TerminalCharacterFactory terminalCharacterFactory;

  public PlaySceneInterfaceRenderer(
      TerminalOrbRenderer orbRenderer,
      TerminalLayerFactory terminalLayerFactory,
      final TerminalCharacterFactory terminalCharacterFactory) {
    this.orbRenderer = orbRenderer;
    this.terminalLayerFactory = terminalLayerFactory;
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  public TerminalLayer render(Agent hero, InterfaceSize size, PlaySceneModel model) {
    TerminalLayer layer = terminalLayerFactory.createBlankLayer(size);
    renderHero(hero, layer);
    renderOrbs(hero, layer);
    model.getCurrentTarget().ifPresent(agent -> renderHealthBar(agent, layer));
    return layer;
  }

  private void renderHealthBar(Agent agent, TerminalLayer layer) {
    var healthBarRenderer = new HealthBarRenderer(terminalLayerFactory, terminalCharacterFactory, agent.getName(), 1, " ", " ");
    var render = healthBarRenderer.render(InterfaceSize.of(agent.getName().length(), 3));
    layer.put(render, InterfaceLocation.at((layer.getSize().getWidth() / 2) - (agent.getName().length() / 2), 1));
  }

  private void renderHero(Agent hero, TerminalLayer layer) {
    InterfaceSize layerSize = layer.getSize();
    int midColumn = layerSize.getWidth() / 2;
    int midRow = layerSize.getHeight() / 2;
    InterfaceLocation layerMidpoint = InterfaceLocation.at(midColumn, midRow);
    TerminalCharacter character;
    if (hero.getStats().getLife().compareTo(BigDecimal.ZERO) == 0) {
      character = terminalCharacterFactory.createCharacter("\uD83D\uDC80");
    } else if (hero.getStats().getLife().compareTo(BigDecimal.TEN) < 0) {
      character = terminalCharacterFactory.createCharacter("\uD83D\uDE41");
    } else {
      character = terminalCharacterFactory.createCharacter("\uD83D\uDE42");
    }
    layer.put(character, layerMidpoint);
  }

  private void renderOrbs(final Agent hero, final TerminalLayer layer) {
    var orbSize = getOrbSize(layer.getSize().getWidth());
    var healthOrb = new DefaultOrb("HP", Color.RED, new Fraction(hero.getStats().getLife(), hero.getStats().getMaxLife()));
    var healthOrbRepresentation = orbRenderer.render(healthOrb, orbSize);
    int top = layer.getSize().getHeight() - orbSize.getHeight();
    var healthOrbPosition = InterfaceLocation.at(0, top);
    layer.put(healthOrbRepresentation, healthOrbPosition);
    var manaOrb = new DefaultOrb("MP", Color.BLUE, new Fraction(hero.getStats().getMana(), hero.getStats().getMaxMana()));
    var manaOrbRepresentation = orbRenderer.render(manaOrb, orbSize);
    var manaOrbPosition = InterfaceLocation.at(layer.getSize().getWidth() - orbSize.getWidth(), top);
    layer.put(manaOrbRepresentation, manaOrbPosition);
  }

  private InterfaceSize getOrbSize(int width) {
    int orbHeight = Math.max(1, width / 15);
    int orbWidth = orbHeight > 1 ? orbHeight * 2 : orbHeight;
    return InterfaceSize.of(orbWidth, orbHeight);
  }
}
