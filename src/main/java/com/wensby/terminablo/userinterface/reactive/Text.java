package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.terminablo.userinterface.LayerWriterImpl;

import java.util.List;

import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;

public class Text implements Component {

  private final TerminalCharacterFactory characterFactory;
  private final String text;
  private final boolean bold;

  public Text(TerminalCharacterFactory characterFactory, String text, boolean bold) {
    this.characterFactory = characterFactory;
    this.text = text;
    this.bold = bold;
  }

  @Override
  public void render(TerminalLayer layer) {
    new LayerWriterImpl(characterFactory, layer).write(text, atOrigin());
  }

  @Override
  public void sendKeys(List<Key> keys) {

  }
}
