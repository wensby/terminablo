package com.wensby.terminablo.userinterface.reactive;


import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class FlexibleGridChild {

  private final String key;
  private final Component component;

  public FlexibleGridChild(String key, Component component) {
    this.key = requireNonNull(key);
    this.component = requireNonNull(component);
  }

  public String getKey() {
    return key;
  }

  public Component getComponent() {
    return component;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FlexibleGridChild that = (FlexibleGridChild) o;
    return Objects.equals(key, that.key) &&
        Objects.equals(component, that.component);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, component);
  }
}
