package com.wensby.terminablo.scene.levelscene;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class DefaultOrb implements Orb {

  private final BigDecimal value;
  private final BigDecimal capacity;
  private final String label;
  private final Color color;

  public DefaultOrb(String label, Color color, BigDecimal capacity, BigDecimal value) {
    this.value = value;
    this.capacity = capacity;
    this.label = label;
    this.color = color;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public String getLabel() {
    return label;
  }

  @Override
  public BigDecimal getCapacity() {
    return capacity;
  }

  @Override
  public BigDecimal getValue() {
    return value;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DefaultOrb.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .add("capacity=" + capacity)
        .add("label='" + label + "'")
        .add("color=" + color)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefaultOrb that = (DefaultOrb) o;
    return Objects.equals(value, that.value) &&
        Objects.equals(capacity, that.capacity) &&
        Objects.equals(label, that.label) &&
        Objects.equals(color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, capacity, label, color);
  }
}
