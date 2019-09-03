package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.TerminalCharacterFactory;

import java.util.Objects;

public class InterfaceComponentFactory {

  private final TerminalCharacterFactory characterFactory;

  public InterfaceComponentFactory(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  public Text createText(String text) {
    return new Text(characterFactory , text);
  }

  public InterfaceComponent createButton(String label, boolean selected) {
    return new Border(characterFactory, new Text(characterFactory, !selected ? label : ">"+ label + "<"));
  }

  public ContainerBuilder buildContainer() {
    return new ContainerBuilder();
  }
}
