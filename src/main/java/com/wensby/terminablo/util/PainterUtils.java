package com.wensby.terminablo.util;

import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalLayer;

import static com.wensby.application.userinterface.InterfaceLocation.at;


public class PainterUtils {

  public void cover(TerminalLayer layer, TerminalCharacter character) {
    for (int row = 0; row < layer.getSize().getHeight(); row++) {
      coverRow(layer, character, row);
    }
  }

  private void coverRow(TerminalLayer layer, TerminalCharacter character, int row) {
    for (int column = 0; column < layer.getSize().getWidth(); column += character.getRenderLength()) {
      layer.put(character, at(column, row));
    }
  }
}
