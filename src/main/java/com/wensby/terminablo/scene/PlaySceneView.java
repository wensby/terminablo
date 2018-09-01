package com.wensby.terminablo.scene;

import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;
import com.wensby.userinterface.InterfacePosition;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalFrame;
import com.wensby.userinterface.TerminalLayer;

public class PlaySceneView {

    private final PlaySceneModel playSceneModel;
    private final LinuxTerminalVisualCanvas canvas;
    private final HeroPlaySceneInterfaceRenderer heroPlaySceneInterfaceRenderer;

    public PlaySceneView(LinuxTerminalVisualCanvas canvas, PlaySceneModel playSceneModel, HeroPlaySceneInterfaceRenderer heroPlaySceneInterfaceRenderer) {
        this.canvas = canvas;
        this.playSceneModel = playSceneModel;
        this.heroPlaySceneInterfaceRenderer = heroPlaySceneInterfaceRenderer;
    }

    public void render() {
        var frame = canvas.createFrame();
        var layer = heroPlaySceneInterfaceRenderer.render(playSceneModel.getHero(), frame.getSize());
        frame.put(layer, InterfacePosition.atOrigin());
        canvas.renderFrame(frame);
    }
}
