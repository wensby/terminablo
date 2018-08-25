package com.wensby.terminablo.scene;

import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;

public class PlaySceneView {

    int renders = 0;
    private final TerminalCharacterFactory terminalCharacterFactory;
    private final PlaySceneModel playSceneModel;
    private final LinuxTerminalVisualCanvas canvas;
    private final HeroPlaySceneInterfaceRenderer heroPlaySceneInterfaceRenderer;

    public PlaySceneView(TerminalCharacterFactory terminalCharacterFactory, LinuxTerminalVisualCanvas canvas, PlaySceneModel playSceneModel, HeroPlaySceneInterfaceRenderer heroPlaySceneInterfaceRenderer) {
        this.terminalCharacterFactory = terminalCharacterFactory;
        this.canvas = canvas;
        this.playSceneModel = playSceneModel;
        this.heroPlaySceneInterfaceRenderer = heroPlaySceneInterfaceRenderer;
    }

    public void render() {
        TerminalCharacter[][] frame = canvas.createFrame();
        String s = renders++ + "";
        for (int i = 0; i < s.length(); i++) {
            frame[0][i] = terminalCharacterFactory.createCharacter(s.charAt(i));
        }
        heroPlaySceneInterfaceRenderer.render(new TerminalLayer(frame), playSceneModel.getHero());
        canvas.render(frame);
    }
}
