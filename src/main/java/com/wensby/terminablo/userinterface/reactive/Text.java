package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.userinterface.LayerWriterImpl;

import static com.wensby.application.userinterface.InterfaceLocation.at;

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
    InterfaceLocation topLeft = at((painter.getAvailableSize().getWidth() / 2) - (text.length() / 2), painter.getAvailableSize().getHeight() / 2);
    new LayerWriterImpl(characterFactory, painter, bold).write(text, topLeft);
  }
}
