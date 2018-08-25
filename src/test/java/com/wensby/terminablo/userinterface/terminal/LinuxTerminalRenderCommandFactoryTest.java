package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacter;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class LinuxTerminalRenderCommandFactoryTest {

    @Test
    public void rcreateCommand_ofSimpleEmptyCharSequenceArray() {
        LinuxTerminalCharacterFactory terminalCharacterFactory = new LinuxTerminalCharacterFactory();
        TerminalRenderCommandFactory factory = new LinuxTerminalRenderCommandFactory(terminalCharacterFactory);
        TerminalCharacter[][] characters = new TerminalCharacter[1][1];

        TerminalRenderCommand command = factory.createCommand(characters);

        assertThat(command, instanceOf(CompositeTerminalRenderCommand.class));
        CompositeTerminalRenderCommand composite = (CompositeTerminalRenderCommand) command;
        List<TerminalRenderCommand> commands = composite.getCommands();
        assertThat(commands.size(), is(2));
        assertThat(commands.get(0), instanceOf(LinuxMoveCursorCommand.class));
        MoveCursorCommand moveCursorCommand = (MoveCursorCommand) commands.get(0);
        assertThat(moveCursorCommand.getCoordinates(), is(TerminalCoordinates.of(0, 0)));
        assertThat(commands.get(1), instanceOf(TerminalCharacterRenderCommand.class));
        assertThat(commands.get(1).toRenderString(), is(" "));
    }
}