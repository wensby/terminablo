package com.wensby.terminablo.userinterface.terminal;

import org.junit.Test;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CompositeTerminalRenderCommandTest {

    @Test
    public void toRenderString_empty_whenEmptyComposite() {
        TerminalRenderCommand command = new CompositeTerminalRenderCommand(emptyList());
        assertThat(command.toRenderString(), is(""));
    }

    @Test
    public void getCommands_empty_whenEmptyComposite() {
        CompositeTerminalRenderCommand command = new CompositeTerminalRenderCommand(emptyList());
        assertTrue(command.getCommands().isEmpty());
    }

    @Test
    public void string_whenEmptyComposite() {
        TerminalRenderCommand command = new CompositeTerminalRenderCommand(emptyList());
        assertThat(command.toString(), is("CompositeTerminalRenderCommand{commands=[]}"));
    }
}