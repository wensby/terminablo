package com.wensby.terminablo.util;

import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalLayerPainter;

import static com.wensby.application.userinterface.InterfaceLocation.at;


public class PainterUtils {

  public void cover(TerminalLayerPainter painter, TerminalCharacter character) {
    for (int row = 0; row < painter.getAvailableSize().getHeight(); row++) {
      coverRow(painter, character, row);
    }
  }

  private void coverRow(TerminalLayerPainter painter, TerminalCharacter character, int row) {
    for (int column = 0; column < painter.getAvailableSize().getWidth(); column += character.getRenderLength()) {
      painter.put(character, at(column, row));
    }
  }
}
