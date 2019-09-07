package com.wensby.terminablo.userinterface.reactive;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ComponentSwitch extends ReactiveComponent {

  private final Map<String, Component> components;
  private final String route;

  public ComponentSwitch(Map<String, Component> components, String route) {
    this.components = Objects.requireNonNull(components);
    this.route = Objects.requireNonNull(route);
  }

  @Override
  public Component render() {
    return components.getOrDefault(route, new Container(List.of()));
  }
}
