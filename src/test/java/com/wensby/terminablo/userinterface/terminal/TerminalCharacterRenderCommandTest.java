package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TerminalCharacterRenderCommandTest {

    @Test
    public void toRenderString() {
        TerminalCharacterFactory characterFactory = new LinuxTerminalCharacterFactory();
        TerminalCharacter character = characterFactory.createCharacter('a');
        TerminalRenderCommand command = new TerminalCharacterRenderCommand(character);
        assertThat(command.toRenderString(), is("a"));
    }

    @Test
    public void string() {
        TerminalCharacterFactory characterFactory = new LinuxTerminalCharacterFactory();
        TerminalCharacter character = characterFactory.createCharacter('a');
        TerminalRenderCommand command = new TerminalCharacterRenderCommand(character);
        assertThat(command.toString(), is("TerminalCharacterRenderCommand{character=SimpleTerminalCharacter{character=a}}"));
    }
}