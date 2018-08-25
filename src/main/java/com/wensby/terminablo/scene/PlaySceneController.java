package com.wensby.terminablo.scene;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.world.AgentStats;
import com.wensby.userinterface.UserInput;

import java.math.BigDecimal;
import java.time.Duration;

public class PlaySceneController {

    private final PlaySceneModel model;

    public PlaySceneController(PlaySceneModel model) {
        this.model = model;
    }

    public void update(Duration elapsedTime, UserInput input) {
        AgentStats stats = model.getHero().getStats();
        if (input.getKeyStrokes().contains(Key.ARROW_UP)) {
            stats.setLife(stats.getLife().add(BigDecimal.ONE));
        }
        if (input.getKeyStrokes().contains(Key.ARROW_DOWN)) {
            stats.setLife(stats.getLife().add(BigDecimal.ONE.negate()));
        }
    }
}
