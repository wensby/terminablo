package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerFactory;

import java.util.Objects;

public class InterfaceComponentFactory {

  private final TerminalCharacterFactory characterFactory;
  private final BorderStyleFactory borderStyleFactory;

  public InterfaceComponentFactory(TerminalCharacterFactory characterFactory, TerminalLayerFactory layerFactory, BorderStyleFactory borderStyleFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.borderStyleFactory = borderStyleFactory;
  }

  public Text createText(String text) {
    return new Text(characterFactory , text);
  }

  public InterfaceComponent createButton(String label, boolean selected) {
    return new Border(new Text(characterFactory, !selected ? label : ">"+ label + "<"), borderStyleFactory.createButtonBorderStyle());
  }

  public ContainerBuilder buildContainer() {
    return new ContainerBuilder();
  }
}
