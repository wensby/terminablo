package com.wensby.terminablo.scene;

import com.wensby.terminablo.Scene;
import com.wensby.userinterface.UserInput;

import java.time.Duration;

public class PlayScene implements Scene {

    private final PlaySceneController playSceneController;
    private final PlaySceneModel playSceneModel;
    private final PlaySceneView playSceneView;

    public PlayScene(PlaySceneController playSceneController, PlaySceneModel playSceneModel, PlaySceneView playSceneView) {
        this.playSceneController = playSceneController;
        this.playSceneModel = playSceneModel;
        this.playSceneView = playSceneView;
    }

    @Override
    public Scene update(Duration elapsedTime, UserInput input) {
        playSceneController.update(elapsedTime, input);
        playSceneView.render();
        return this;
    }
}
