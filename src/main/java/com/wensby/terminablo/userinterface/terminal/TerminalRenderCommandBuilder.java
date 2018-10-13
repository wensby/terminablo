package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.TerminalCharacter;
import java.util.ArrayList;
import java.util.List;

public class TerminalRenderCommandBuilder {

  private final List<TerminalRenderCommand> commands = new ArrayList<>();

  public void addCommand(TerminalRenderCommand command) {
    commands.add(command);
  }

  public void addTerminalCharacter(TerminalCharacter charSequence) {
    commands.add(new TerminalCharacterRenderCommand(charSequence));
  }

  public TerminalRenderCommand build() {
    return new CompositeTerminalRenderCommand(commands);
  }
}
