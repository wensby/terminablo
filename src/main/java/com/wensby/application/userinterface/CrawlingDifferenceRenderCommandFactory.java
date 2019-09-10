package com.wensby.application.userinterface;


public class CrawlingDifferenceRenderCommandFactory implements LayerDifferenceRenderCommandFactory {

  private final TerminalCharacter emptyShortCharacter;
  private final TerminalCharacter emptyLongCharacter;

  private int frameHeight;
  private int frameWidth;
  private TerminalCharacter[][] prevCharacters;
  private TerminalCharacter[][] nextCharacters;
  private int lastColumn;
  private LinuxTerminalRenderCommandBuilder commandBuilder;

  public CrawlingDifferenceRenderCommandFactory(TerminalCharacterFactory characterFactory) {
    emptyShortCharacter = characterFactory.createCharacter(' ');
    emptyLongCharacter = characterFactory.createCharacter("  ");
  }

  @Override
  public TerminalRenderCommand createCommand(TerminalFrame previousFrame, TerminalFrame frame) {
    frameHeight = frame.size().getHeight();
    frameWidth = frame.size().getWidth();
    prevCharacters = previousFrame != null ? previousFrame.getCharacters() : new TerminalCharacter[frameHeight][frameWidth];
    nextCharacters = frame.getCharacters();
    lastColumn = frameWidth - 1;
    commandBuilder = new LinuxTerminalRenderCommandBuilder(frameWidth);
    crawlFrame();
    return commandBuilder.build();
  }

  private void crawlFrame() {
    for (int row = 0; row < frameHeight; row++) {
      crawlRow(row);
    }
  }

  private void crawlRow(int row) {
    for (int col = 0; col < frameWidth; col++) {
      crawlCoordinate(row, col);
    }
  }

  private void crawlCoordinate(int row, int col) {
    var character = getCharacterDifference(row, col);
    if (character != null) {
      commandBuilder.moveCursor(TerminalCoordinates.of(row, col));
      commandBuilder.printCharacter(character);
    }
  }

  private TerminalCharacter getCharacterDifference(int row, int col) {
    if (isSameCharacter(row, col)) {
      return null;
    }
    if (isCharacterEmptied(row, col)) {
      return getEmptyingCharacter(row, col);
    }
    return nextCharacters[row][col];
  }

  private boolean isSameCharacter(int row, int col) {
    var next = nextCharacters[row][col];
    if (next != null) {
      var prev = prevCharacters[row][col];
      return next.equals(prev);
    }
    return false;
  }

  private boolean isCharacterEmptied(int row, int col) {
    if (prevCharacters[row][col] != null) {
      return nextCharacters[row][col] == null;
    }
    return false;
  }

  /**
   * When emptying a row, it needs to be emptied either by a double space or a single space,
   * depending on if the character in the previous frame covered two spaces or one, and if the
   * following character exists or not.
   */
  private TerminalCharacter getEmptyingCharacter(int row, int col) {
    if (col != lastColumn && isPreviousLongCharacterFullyRemoved(row, col)) {
      return emptyLongCharacter;
    }
    return emptyShortCharacter;
  }

  private boolean isPreviousLongCharacterFullyRemoved(int row, int col) {
    var prev = prevCharacters[row][col];
    return prev.getRenderLength() > 1 && isSucceedingNextCharacterOutOfBoundsOrNull(row, col);
  }

  private boolean isSucceedingNextCharacterOutOfBoundsOrNull(int row, int col) {
    if (row >= frameHeight || col + 1 >= frameWidth) {
      return true;
    }
    return nextCharacters[row][col + 1] == null;
  }

}
