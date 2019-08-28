package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.Level;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlaySceneModel {

  private final PlayerCharacter character;
  private final Level level;
  private final List<Agent> monsters;

  private Agent currentTarget;

  public PlaySceneModel(PlayerCharacter character, Level level, List<Agent> monsters) {
    this.character = Objects.requireNonNull(character);
    this.level = level;
    this.monsters = monsters;
  }

  public PlayerCharacter getCharacter() {
    return character;
  }

  public Level getLevel() {
    return level;
  }

  public List<Agent> getMonsters() {
    return monsters;
  }

  public Optional<Agent> getCurrentTarget() {
    return Optional.ofNullable(currentTarget);
  }

  public void setCurrentTarget(Agent agent) {
    this.currentTarget = agent;
  }
}
