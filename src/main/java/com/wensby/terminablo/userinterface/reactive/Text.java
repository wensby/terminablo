package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.userinterface.LayerWriterImpl;

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
  public void render(TerminalLayerPainter painter) {
    new LayerWriterImpl(characterFactory, painter, bold).write(text, atOrigin());
  }
}
