package com.wensby.terminablo.userinterface.terminal;

import java.util.ArrayList;
import java.util.List;

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
}
