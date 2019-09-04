package com.wensby.terminablo.scene.playscene;

import com.wensby.application.userinterface.*;
import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.util.Fraction;
import java.awt.Color;
import java.math.BigDecimal;

public class PlaySceneInterfaceRenderer {

  private final TerminalOrbRenderer orbRenderer;
  private final TerminalCharacterFactory terminalCharacterFactory;

  public PlaySceneInterfaceRenderer(
      TerminalOrbRenderer orbRenderer,
      final TerminalCharacterFactory terminalCharacterFactory) {
    this.orbRenderer = orbRenderer;
    this.terminalCharacterFactory = terminalCharacterFactory;
  }

  public void render(Agent hero, TerminalLayerPainter painter, PlaySceneModel model) {
    renderHero(hero, painter);
    renderOrbs(hero, painter);
    model.getCurrentTarget().ifPresent(agent -> renderHealthBar(agent, painter));
  }

  private void renderHealthBar(Agent agent, TerminalLayerPainter painter) {
    var healthBarRenderer = new HealthBarRenderer(terminalCharacterFactory, agent.getName(), 1, " ", " ");
    healthBarRenderer.render(painter.createSubsectionPainter(InterfaceLocation.at((painter.getAvailableSize().getWidth() / 2) - (agent.getName().length() / 2), 1), InterfaceSize.of(agent.getName().length(), 3)));
  }

  private void renderHero(Agent hero, TerminalLayerPainter painter) {
    InterfaceSize layerSize = painter.getAvailableSize();
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
    painter.put(character, layerMidpoint);
  }

  private void renderOrbs(final Agent hero, final TerminalLayerPainter painter) {
    var orbSize = getOrbSize(painter.getAvailableSize().getWidth());
    var healthOrb = new DefaultOrb("HP", Color.RED, new Fraction(hero.getStats().getLife(), hero.getStats().getMaxLife()));
    int top = painter.getAvailableSize().getHeight() - orbSize.getHeight();
    var healthOrbPosition = InterfaceLocation.at(0, top);
    orbRenderer.render(healthOrb, painter.createSubsectionPainter(healthOrbPosition, orbSize));
    var manaOrb = new DefaultOrb("MP", Color.BLUE, new Fraction(hero.getStats().getMana(), hero.getStats().getMaxMana()));
    var manaOrbPosition = InterfaceLocation.at(painter.getAvailableSize().getWidth() - orbSize.getWidth(), top);
    orbRenderer.render(manaOrb, painter.createSubsectionPainter(manaOrbPosition, orbSize));
  }

  private InterfaceSize getOrbSize(int width) {
    int orbHeight = Math.max(1, width / 15);
    int orbWidth = orbHeight > 1 ? orbHeight * 2 : orbHeight;
    return InterfaceSize.of(orbWidth, orbHeight);
  }
}
