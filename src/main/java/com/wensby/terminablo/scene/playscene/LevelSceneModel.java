package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.Level;

import java.util.LinkedList;
import java.util.List;

public class LevelSceneModel {

  private final Agent hero;
  private final Level level;
  private final List<Agent> monsters;

  public LevelSceneModel(Agent hero, Level level, List<Agent> monsters) {
    this.hero = hero;
    this.level = level;
    this.monsters = monsters;
  }

  public Agent getHero() {
    return hero;
  }

  public Level getLevel() {
    return level;
  }

  public List<Agent> getMonsters() {
    return monsters;
  }
}
