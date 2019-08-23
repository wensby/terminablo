package com.wensby.terminablo.scene.playscene;

import java.time.Duration;

public class AgentSpeed {

  private final float yardsPerSecond;

  private AgentSpeed(float yardsPerSecond) {
    this.yardsPerSecond = yardsPerSecond;
  }

  public static AgentSpeed yardsPerSecond(float yardsPerSecond) {
    return new AgentSpeed(yardsPerSecond);
  }

  public Duration durationPerYard() {
    return Duration.ofMillis((long) (1000f / yardsPerSecond));
  }
}
