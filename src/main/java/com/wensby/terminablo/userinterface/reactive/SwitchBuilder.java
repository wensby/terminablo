package com.wensby.terminablo.userinterface.reactive;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SwitchBuilder {

  private final Map<String, Supplier<Component>> components = new HashMap<>();

  public ComponentSwitch build(String route) {
    return new ComponentSwitch(components, route);
  }

  public SwitchBuilder route(String path, Supplier<Component> componentSupplier) {
    components.put(path, componentSupplier);
    return this;
  }
}
