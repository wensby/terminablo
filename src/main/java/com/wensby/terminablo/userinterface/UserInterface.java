package com.wensby.terminablo.userinterface;

import com.wensby.application.userinterface.InterfaceLocation;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.UserInput;
import com.wensby.terminablo.userinterface.reactive.Component;

import java.time.Duration;

public class UserInterface {

  private final Component topComponent;
  private InterfaceLocation focusLocation;

  public UserInterface(Component topComponent) {
    this.topComponent = topComponent;
  }

  public Component getTopComponent() {
    return topComponent;
  }

  public void update(Duration elapsedTime, UserInput input) {
    topComponent.sendKeys(input.getKeyStrokes());
  }

  public void render(TerminalLayer layer) {
    topComponent.render(layer);
  }
}
