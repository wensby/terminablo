package com.wensby.terminablo.userinterface.reactive;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class Switch extends ReactiveComponent {

  private final Map<String, Supplier<Component>> components;
  private final String route;

  public Switch(Map<String, Supplier<Component>> components, String route) {
    this.components = Objects.requireNonNull(components);
    this.route = Objects.requireNonNull(route);
  }

  @Override
  public Component render() {
    return components.getOrDefault(route, () -> new Container(List.of())).get();
  }
}
