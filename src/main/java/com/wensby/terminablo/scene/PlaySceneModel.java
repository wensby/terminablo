package com.wensby.terminablo.scene;

import com.wensby.terminablo.world.Agent;

public class PlaySceneModel {

    private final Agent hero;

    public PlaySceneModel(Agent hero) {
        this.hero = hero;
    }

    public Agent getHero() {
        return hero;
    }
}
