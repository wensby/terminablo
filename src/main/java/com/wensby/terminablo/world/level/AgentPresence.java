package com.wensby.terminablo.world.level;

import com.wensby.application.userinterface.SimpleTerminalCharacterImpl;
import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.terminablo.world.Agent;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class AgentPresence implements LevelEntity {

  private final Agent agent;

  public AgentPresence(Agent agent) {
    this.agent = requireNonNull(agent);
  }

  @Override
  public boolean isPassable() {
    return false;
  }

  @Override
  public Optional<TerminalCharacter> getCharacter() {
    return Optional.of(new SimpleTerminalCharacterImpl(')'));
  }

  public Agent getAgent() {
    return agent;
  }
}
