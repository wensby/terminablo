package com.wensby.terminablo.scene;

import com.wensby.terminablo.world.Agent;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;

import java.awt.*;
import java.math.BigDecimal;

public class HeroPlaySceneInterfaceRenderer {

    private final OrbTerminalRepresentationFactory orbTerminalRepresentationFactory;

    public HeroPlaySceneInterfaceRenderer(OrbTerminalRepresentationFactory orbTerminalRepresentationFactory) {
        this.orbTerminalRepresentationFactory = orbTerminalRepresentationFactory;
    }

    public void render(TerminalLayer terminalLayer, Agent hero) {
        int height = terminalLayer.getHeight();
        int width = terminalLayer.getWidth();
        BigDecimal maxLife = hero.getStats().getMaxLife();
        BigDecimal life = hero.getStats().getLife();
        Orb healthOrb = new DefaultOrb("HP", Color.RED, maxLife, life);
        Orb manaOrb = new DefaultOrb("MP", Color.BLUE, maxLife, life);

        int orbHeight = Math.max(1, terminalLayer.getWidth() / 15);
        int orbWidth = orbHeight > 1 ? orbHeight * 2 : orbHeight;
        InterfaceSize size = new InterfaceSize(orbWidth, orbHeight);
        TerminalLayer healthOrbRepresentation = orbTerminalRepresentationFactory.createRepresentation(healthOrb, size);
        layer(terminalLayer, healthOrbRepresentation, height-healthOrbRepresentation.getHeight(), 0);
        TerminalLayer manaOrbRepresentation = orbTerminalRepresentationFactory.createRepresentation(manaOrb, size);
        layer(terminalLayer, manaOrbRepresentation, height-manaOrbRepresentation.getHeight(), width-manaOrbRepresentation.getWidth());
    }

    private void layer(TerminalLayer frame, TerminalLayer sphere, int y, int x) {
        frame.put(sphere, y, x);
    }
}
