package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.level.AgentPresence;
import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelLocation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlaySceneModel {

  private final PlayerCharacter character;
  private final Level level;
  private final List<Agent> monsters;

  private LevelLocation playerLocation = LevelLocation.ZERO;
  private Agent currentTarget;
  private final List<AgentPresence> monsterPresences;
  private final AgentPresence heroPresence;

  public PlaySceneModel(PlayerCharacter character, Level level, List<Agent> monsters, List<AgentPresence> monsterPresences, AgentPresence heroPresence) {
    this.character = Objects.requireNonNull(character);
    this.level = level;
    this.monsters = monsters;
    this.monsterPresences = monsterPresences;
    this.heroPresence = heroPresence;
    level.putEntity(playerLocation, this.heroPresence);
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

  public List<AgentPresence> getMonsterPresences() {
    return monsterPresences;
  }

  public Optional<Agent> getCurrentTarget() {
    return Optional.ofNullable(currentTarget);
  }

  public void setCurrentTarget(Agent agent) {
    this.currentTarget = agent;
  }

  public Optional<LevelLocation> getPlayerLocation() {
    return level.locationOf(heroPresence);
  }

  public void setPlayerLocation(LevelLocation playerLocation) {
    this.playerLocation = playerLocation;
  }

  public AgentPresence getCharacterPresence() {
    return heroPresence;
  }
}
