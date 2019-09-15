package com.wensby.terminablo.userinterface.reactive;

public class ComponentFactory {

  public SwitchBuilder aSwitch() {
    return new SwitchBuilder();
  }

  public FlexibleGridBuilder aFlexibleGrid() {
    return new FlexibleGridBuilder();
  }
}
