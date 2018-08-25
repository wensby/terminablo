package com.wensby.terminablo;

import com.wensby.SceneTicker;
import com.wensby.terminablo.scene.*;
import com.wensby.terminablo.userinterface.terminal.*;
import com.wensby.terminablo.world.Agent;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;

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
        HeroPlaySceneInterfaceRenderer heroPlaySceneInterfaceRenderer = new HeroPlaySceneInterfaceRenderer(orbTerminalRepresentationFactory);
        Scene scene = new PlayScene(new PlaySceneController(playSceneModel), playSceneModel, new PlaySceneView(terminalCharacterFactory, canvas, playSceneModel, heroPlaySceneInterfaceRenderer));
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
