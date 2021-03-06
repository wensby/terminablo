package com.wensby.application.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class CompositeTerminalRenderCommand implements TerminalRenderCommand {

  private final List<TerminalRenderCommand> commands;

  public CompositeTerminalRenderCommand(List<TerminalRenderCommand> commands) {
    this.commands = new ArrayList<>(commands);
  }

  public List<TerminalRenderCommand> getCommands() {
    return new ArrayList<>(commands);
  }

  @Override
  public String toString() {
    return "CompositeTerminalRenderCommand{" +
        "commands=" + commands +
        '}';
  }

  @Override
  public String toRenderString() {
    return commands.stream()
        .map(TerminalRenderCommand::toRenderString)
        .collect(joining());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CompositeTerminalRenderCommand that = (CompositeTerminalRenderCommand) o;
    return Objects.equals(commands, that.commands);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commands);
  }
}
