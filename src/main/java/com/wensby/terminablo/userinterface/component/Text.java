package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.userinterface.LayerWriterImpl;

import java.util.List;
import java.util.Objects;

import static com.wensby.application.userinterface.InterfaceLocation.at;

public class Text implements InterfaceComponent {

  private final TerminalCharacterFactory characterFactory;
  private final String text;
  private final boolean bold;

  public Text(TerminalCharacterFactory characterFactory, String text, boolean bold) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.text = Objects.requireNonNull(text);
    this.bold = bold;
  }

  @Override
  public void render(TerminalLayer layer) {
    InterfaceLocation topLeft = at((layer.getSize().getWidth() / 2) - (text.length() / 2), layer.getSize().getHeight() / 2);
    new LayerWriterImpl(characterFactory, layer, bold).write(text, topLeft);
  }

  @Override
  public InterfaceSize contentSize() {
    var rows = List.of(text.split("\n"));
    var width = rows.stream().mapToInt(String::length).max().orElse(0);
    return InterfaceSize.of(width, rows.size());
  }
}
