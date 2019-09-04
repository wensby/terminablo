package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.userinterface.LayerWriterImpl;

import java.util.Objects;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.at;

public class Text implements InterfaceComponent {

  private final TerminalCharacterFactory characterFactory;
  private final String text;

  public Text(TerminalCharacterFactory characterFactory, String text) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.text = Objects.requireNonNull(text);
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    InterfaceLocation topLeft = at((painter.getAvailableSize().getWidth() / 2) - (text.length() / 2), painter.getAvailableSize().getHeight() / 2);
    new LayerWriterImpl(characterFactory, painter).write(text, topLeft);
  }
}
