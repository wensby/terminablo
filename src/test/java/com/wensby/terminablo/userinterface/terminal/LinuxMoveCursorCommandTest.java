package com.wensby.terminablo.userinterface.terminal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.wensby.application.userinterface.LinuxMoveCursorCommand;
import com.wensby.application.userinterface.TerminalCoordinates;
import com.wensby.application.userinterface.TerminalRenderCommand;
import org.junit.Test;

public class LinuxMoveCursorCommandTest {

  @Test
  public void getCoordinates() {
    TerminalCoordinates coordinates = TerminalCoordinates.of(0, 0);
    LinuxMoveCursorCommand command = new LinuxMoveCursorCommand(coordinates);
    assertThat(command.getCoordinates(), is(coordinates));
  }

  @Test
  public void toRenderString() {
    TerminalCoordinates coordinates = TerminalCoordinates.of(0, 0);
    TerminalRenderCommand command = new LinuxMoveCursorCommand(coordinates);
    assertThat(command.toRenderString(), is((char) 0x1B + "[1;1f"));
  }

  @Test
  public void string() {
    TerminalCoordinates coordinates = TerminalCoordinates.of(0, 0);
    TerminalRenderCommand command = new LinuxMoveCursorCommand(coordinates);
    assertThat(command.toString(),
        is("LinuxMoveCursorCommand{coordinates=TerminalCoordinates{row=0, column=0}}"));

  }
}