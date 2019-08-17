package com.wensby.userinterface;

public interface TerminalCharacter {

  /**
   * Returns the number of cursor positions that rendering this character will require. For simple
   * characters, this is always 1, but for emojis, this is most often 2.
   */
  int getRenderLength();
}
