package com.wensby.terminablo.userinterface.terminal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacter;
import java.util.List;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

public class LinuxTerminalRenderCommandFactoryTest {

  @Test
  public void createCommand_ofSimpleEmptyCharSequenceArray() {
    var characterFactory = new LinuxTerminalCharacterFactory();
    var commandFactory = new LinuxTerminalRenderCommandFactory(characterFactory);
    var characters = new TerminalCharacter[1][1];

    var command = commandFactory.createCommand(characters);

    assertThat(command, instanceOf(CompositeTerminalRenderCommand.class));
    CompositeTerminalRenderCommand composite = (CompositeTerminalRenderCommand) command;
    List<TerminalRenderCommand> commands = composite.getCommands();
    assertThat(commands.size(), is(1));
    assertThat(commands.get(0), instanceOf(ClearScreenRenderCommand.class));
  }
}