package com.wensby.terminablo;

import com.wensby.SceneTicker;
import com.wensby.terminablo.scene.*;
import com.wensby.terminablo.userinterface.terminal.*;
import com.wensby.terminablo.world.Agent;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.TerminalLayerFactoryImpl;

public class Diablo {

    public static void main(String[] args) {
        var linuxTerminal = new LinuxTerminal(System.in, System.out, new BashCommandExecutor());
        var terminalCharacterFactory = new LinuxTerminalCharacterFactory();
        var linuxTerminalRenderCommandFactory = new LinuxTerminalRenderCommandFactory(terminalCharacterFactory);
        var userInterfaceFactory = new LinuxTerminalUserInterfaceFactory(linuxTerminal, linuxTerminalRenderCommandFactory);
        var userInterface = userInterfaceFactory.createUserInterface();
        var canvas = (LinuxTerminalVisualCanvas) userInterface.getCanvas();
        var hero = new AgentBuilder().build();
        var playSceneModel = new PlaySceneModel(hero);
        var orbTerminalRepresentationFactory = new OrbTerminalRepresentationFactory(terminalCharacterFactory);
        var terminalLayerFactory = new TerminalLayerFactoryImpl();
        var heroPlaySceneInterfaceRenderer = new HeroPlaySceneInterfaceRenderer(orbTerminalRepresentationFactory, terminalLayerFactory, terminalCharacterFactory);
        var playSceneView = new PlaySceneView(canvas, playSceneModel, heroPlaySceneInterfaceRenderer);
        var playSceneController = new PlaySceneController(playSceneModel);
        var scene = new PlayScene(playSceneController, playSceneModel, playSceneView);
        var gameTicker = new SceneTicker(scene);
        try {
            new GameLooperBuilder()
                    .withTickable(gameTicker)
                    .withUserInterface(userInterface)
                    .build()
                    .run();
        } finally {
            userInterface.release();
        }
    }

}
