package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.util.Fraction;
import java.awt.Color;
import java.util.Objects;
import java.util.StringJoiner;

public class DefaultOrb implements Orb {

  private final String label;
  private final Color color;
  private final Fraction values;

  public DefaultOrb(String label, Color color, Fraction values) {
    this.label = requireNonNull(label);
    this.color = requireNonNull(color);
    this.values = requireNonNull(values);
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public Fraction getValues() {
    return values;
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
    return Objects.equals(label, that.label) &&
        Objects.equals(color, that.color) &&
        Objects.equals(values, that.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, color, values);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DefaultOrb.class.getSimpleName() + "{", "}")
        .add("label='" + label + "'")
        .add("color=" + color)
        .add("values=" + values)
        .toString();
  }
}
