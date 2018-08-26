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
        LinuxTerminal linuxTerminal = new LinuxTerminal(System.in, System.out, new BashCommandExecutor());
        LinuxTerminalCharacterFactory terminalCharacterFactory = new LinuxTerminalCharacterFactory();
        LinuxTerminalRenderCommandFactory linuxTerminalRenderCommandFactory = new LinuxTerminalRenderCommandFactory(terminalCharacterFactory);
        LinuxTerminalUserInterfaceFactory userInterfaceFactory = new LinuxTerminalUserInterfaceFactory(linuxTerminal, linuxTerminalRenderCommandFactory);
        LinuxTerminalUserInterface userInterface = userInterfaceFactory.createUserInterface();
        LinuxTerminalVisualCanvas canvas = (LinuxTerminalVisualCanvas) userInterface.getCanvas();
        Agent hero = new AgentBuilder().build();
        PlaySceneModel playSceneModel = new PlaySceneModel(hero);
        OrbTerminalRepresentationFactory orbTerminalRepresentationFactory = new OrbTerminalRepresentationFactory(terminalCharacterFactory);
        TerminalLayerFactory terminalLayerFactory = new TerminalLayerFactoryImpl();
        HeroPlaySceneInterfaceRenderer heroPlaySceneInterfaceRenderer = new HeroPlaySceneInterfaceRenderer(orbTerminalRepresentationFactory, terminalLayerFactory);
        PlaySceneView playSceneView = new PlaySceneView(canvas, playSceneModel, heroPlaySceneInterfaceRenderer);
        PlaySceneController playSceneController = new PlaySceneController(playSceneModel);
        Scene scene = new PlayScene(playSceneController, playSceneModel, playSceneView);
        final SceneTicker gameTicker = new SceneTicker(scene);
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
