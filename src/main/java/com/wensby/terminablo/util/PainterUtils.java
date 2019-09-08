package com.wensby.terminablo.util;

import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.function.Supplier;

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

  public void cover(TerminalLayer layer, Supplier<TerminalCharacter> characterSupplier) {
    for (int row = 0; row < layer.getSize().getHeight(); row++) {
      coverRow(layer, characterSupplier, row);
    }
  }

  private void coverRow(TerminalLayer layer, Supplier<TerminalCharacter> characterSupplier, int row) {
    var character = characterSupplier.get();
    for (int column = 0; column < layer.getSize().getWidth(); column += character.getRenderLength()) {
      character = characterSupplier.get();
      layer.put(character, at(column, row));
    }
  }
}
