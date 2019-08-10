package com.wensby.terminablo.userinterface.terminal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.LinuxDecorativeCharacter;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.SimpleTerminalCharacter;
import com.wensby.userinterface.TerminalCharacter;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class LinuxTerminalRenderCommandFactoryTest {

  private LinuxTerminalCharacterFactory characterFactory;

  @Before
  public void setUp() {
    characterFactory = new LinuxTerminalCharacterFactory();
  }

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

  @Test
  public void createCommand_ofEmptyMap() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var command = commandFactory.createCommand(Map.of());
    assertThat(command, is(new CompositeTerminalRenderCommand(List.of())));
  }

  @Test
  public void createCommand_ofOneCharacterMap() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var character = new SimpleTerminalCharacter('a');
    var characters = Map.of(InterfaceLocation.of(0, 0), character);

    var command = commandFactory.createCommand(characters);

    assertThat(command, is(new CompositeTerminalRenderCommand(List.of(
        new LinuxMoveCursorCommand(TerminalCoordinates.of(0, 0)),
        new TerminalCharacterRenderCommand(character)
    ))));
  }

  @Test
  public void createCommand_ofTwoFollowingCharacterMap() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var character = new SimpleTerminalCharacter('a');
    var characters = Map.of(
        InterfaceLocation.of(0, 0), character,
        InterfaceLocation.of(1, 0), character);

    var command = commandFactory.createCommand(characters);

    assertThat(command, is(new CompositeTerminalRenderCommand(List.of(
        new LinuxMoveCursorCommand(TerminalCoordinates.of(0, 0)),
        new TerminalCharacterRenderCommand(character),
        new TerminalCharacterRenderCommand(character)
    ))));
  }

  @Test
  public void createCommand_ofComplexMap() {
    var commandFactory = new LinuxTerminalRenderCommandFactory();
    var character = new SimpleTerminalCharacter('a');
    var skull = characterFactory.createCharacter("\uD83D\uDC80");
    var characters = Map.of(
        InterfaceLocation.of(2, 0), character,
        InterfaceLocation.of(0, 0), skull,
        InterfaceLocation.of(1, 1), character);

    var command = commandFactory.createCommand(characters);

    assertThat(command, is(new CompositeTerminalRenderCommand(List.of(
        new LinuxMoveCursorCommand(TerminalCoordinates.of(0, 0)),
        new TerminalCharacterRenderCommand(skull),
        new TerminalCharacterRenderCommand(character),
        new LinuxMoveCursorCommand(TerminalCoordinates.of(1, 1)),
        new TerminalCharacterRenderCommand(character)
    ))));
  }
}