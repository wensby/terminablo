package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;

import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;

public class Graphic implements Component {

  private final TerminalCharacter character;

  public Graphic(TerminalCharacter character) {
    this.character = character;
  }

  @Override
  public void render(TerminalLayer layer) {
    layer.put(character, atOrigin());
  }

  @Override
  public void sendKeys(List<Key> keys) {

  }
}
