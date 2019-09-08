package com.wensby.application.userinterface;

import java.util.ArrayList;
import java.util.List;

public class LinuxTerminalRenderCommandBuilder {

  private final List<TerminalRenderCommand> commands = new ArrayList<>();
  private final int terminalWidth;

  private TerminalCoordinates currentCursorPosition;
  private CharacterDecoration currentDecoration;
  private StringBuffer ongoingPrint;

  public LinuxTerminalRenderCommandBuilder(int terminalWidth) {
    this.terminalWidth = terminalWidth;
    currentCursorPosition = null;
    currentDecoration = null;
    ongoingPrint = new StringBuffer();
  }

  public void moveCursor(TerminalCoordinates position) {
    if (isNewCursorPosition(position)) {
      flushOngoingPrintBufferToCommand();
      commands.add(new LinuxMoveCursorCommand(position));
      currentCursorPosition = position;
    }
  }

  public void printCharacter(PositionedTerminalCharacter character) {
    var location = character.getLocation();
    var terminalCharacter = character.getCharacter();
    moveCursor(TerminalCoordinates.of(location.getRow(), location.getColumn()));
    printCharacter(terminalCharacter);
  }

  public void printCharacter(TerminalCharacter character) {
    if (character instanceof SimpleTerminalCharacter) {
      printSimpleTerminalCharacter((SimpleTerminalCharacter) character);
    }
    else if (character instanceof ComplexTerminalCharacter) {
      printComplexTerminalCharacter((ComplexTerminalCharacter) character);
    }
    else {
      throw new RuntimeException("Unknown TerminalCharacter type: " + character.getClass());
    }
  }

  public TerminalRenderCommand build() {
    flushOngoingPrintBufferToCommand();
    if (isCursorCurrentlyDecorated()) {
      commands.add(new LinuxColorResetCommand());
      currentDecoration = null;
    }
    return new CompositeTerminalRenderCommand(commands);
  }

  private void printSimpleTerminalCharacter(SimpleTerminalCharacter character) {
    if (isCursorCurrentlyDecorated()) {
      flushOngoingPrintBufferToCommand();
      commands.add(new LinuxColorResetCommand());
      currentDecoration = null;
    }
    ongoingPrint.append(character.getCharacter());
    incrementInternalCursorPosition(1);
  }

  private void incrementInternalCursorPosition(int i) {
    currentCursorPosition = currentCursorPosition.plus(0, i);
    if (currentCursorPosition.getColumn() >= terminalWidth) {
      currentCursorPosition = currentCursorPosition.plus(1, -terminalWidth);
    }
  }

  private void printComplexTerminalCharacter(ComplexTerminalCharacter character) {
    var decoration = character.getDecoration();
    if (isNewDecoration(decoration)) {
      flushOngoingPrintBufferToCommand();
      commands.add(new LinuxColorAdjustmentCommand(decoration));
      currentDecoration = decoration;
    }
    var charSequence = character.getCharSequence();
    ongoingPrint.append(charSequence);
    incrementInternalCursorPosition(charSequence.length());
  }

  private boolean isNewDecoration(CharacterDecoration decoration) {
    return !decoration.equals(currentDecoration);
  }

  private void flushOngoingPrintBufferToCommand() {
    if (ongoingPrint.length() > 0) {
      commands.add(new LinuxPrintRenderCommand(ongoingPrint.toString()));
      ongoingPrint = new StringBuffer();
    }
  }

  private boolean isNewCursorPosition(TerminalCoordinates position) {
    return !position.equals(currentCursorPosition);
  }

  private boolean isCursorCurrentlyDecorated() {
    return currentDecoration != null;
  }
}
