package com.wensby.terminablo.userinterface.terminal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacter;
import java.util.List;
import org.junit.Test;

public class LinuxTerminalRenderCommandFactoryTest {

  @Test
  public void createCommand_ofSimpleEmptyCharSequenceArray() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var characters = new TerminalCharacter[1][1];

    var command = commandFactory.createCommand(characters);

    assertThat(command, instanceOf(CompositeTerminalRenderCommand.class));
    CompositeTerminalRenderCommand composite = (CompositeTerminalRenderCommand) command;
    List<TerminalRenderCommand> commands = composite.getCommands();
    assertThat(commands.size(), is(0));
  }
}