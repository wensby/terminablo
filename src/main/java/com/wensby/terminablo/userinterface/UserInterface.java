package com.wensby.terminablo.userinterface;

import com.wensby.terminablo.userinterface.reactive.Component;

public class UserInterface {

  private final Component topComponent;

  public UserInterface(Component topComponent) {
    this.topComponent = topComponent;
  }

  public Component getTopComponent() {
    return topComponent;
  }
}
