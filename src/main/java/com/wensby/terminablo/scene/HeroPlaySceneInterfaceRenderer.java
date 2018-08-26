package com.wensby.terminablo.scene;

import com.wensby.terminablo.world.Agent;
import com.wensby.userinterface.InterfacePosition;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;

import java.awt.*;
import java.math.BigDecimal;

public class HeroPlaySceneInterfaceRenderer {

    private final OrbTerminalRepresentationFactory orbTerminalRepresentationFactory;
    private final TerminalLayerFactory terminalLayerFactory;

    public HeroPlaySceneInterfaceRenderer(OrbTerminalRepresentationFactory orbTerminalRepresentationFactory, TerminalLayerFactory terminalLayerFactory) {
        this.orbTerminalRepresentationFactory = orbTerminalRepresentationFactory;
        this.terminalLayerFactory = terminalLayerFactory;
    }

    public TerminalLayer render(Agent hero, InterfaceSize size) {
        TerminalLayer terminalLayer = terminalLayerFactory.createTerminalLayer(size);
        InterfaceSize orbSize = getOrbSize(terminalLayer.getSize().getWidth());
        Orb healthOrb = new DefaultOrb("HP", Color.RED, hero.getStats().getMaxLife(), hero.getStats().getLife());
        TerminalLayer healthOrbRepresentation = orbTerminalRepresentationFactory.createRepresentation(healthOrb, orbSize);
        int top = terminalLayer.getSize().getHeight() - orbSize.getHeight();
        InterfacePosition healthOrbPosition = InterfacePosition.of(0, top);
        terminalLayer.put(healthOrbRepresentation, healthOrbPosition);
        Orb manaOrb = new DefaultOrb("MP", Color.BLUE, BigDecimal.TEN, BigDecimal.TEN);
        TerminalLayer manaOrbRepresentation = orbTerminalRepresentationFactory.createRepresentation(manaOrb, orbSize);
        InterfacePosition manaOrbPosition = InterfacePosition.of(terminalLayer.getSize().getWidth() - orbSize.getWidth(), top);
        terminalLayer.put(manaOrbRepresentation, manaOrbPosition);
        return terminalLayer;
    }

    private InterfaceSize getOrbSize(int width) {
        int orbHeight = Math.max(1, width / 15);
        int orbWidth = orbHeight > 1 ? orbHeight * 2 : orbHeight;
        return new InterfaceSize(orbWidth, orbHeight);
    }
}
