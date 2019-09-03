package com.wensby.terminablo.userinterface.component;

import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.Objects;

public class TriggerableComponent implements InterfaceComponent {

  private final InterfaceComponent component;
  private final Runnable triggerAction;

  public TriggerableComponent(InterfaceComponent component, Runnable triggerAction) {
    this.component = Objects.requireNonNull(component);
    this.triggerAction = Objects.requireNonNull(triggerAction);
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    component.render(painter);
  }
}
