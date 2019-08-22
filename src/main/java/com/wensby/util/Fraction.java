package com.wensby.util;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Fraction {

  private final BigDecimal numerator;
  private final BigDecimal denominator;

  public Fraction(BigDecimal numerator, BigDecimal denominator) {
    this.numerator = requireNonNull(numerator);
    this.denominator = requireNonNull(denominator);
  }

  public BigDecimal getNumerator() {
    return numerator;
  }

  public BigDecimal getDenominator() {
    return denominator;
  }

  public BigDecimal toUnitInterval() {
    var decimalFraction = numerator.divide(denominator, 10, HALF_UP);
    if (decimalFraction.abs().compareTo(ONE) > 0) {
      return decimalFraction.remainder(ONE);
    } else {
      return decimalFraction;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Fraction fraction = (Fraction) o;
    return Objects.equals(numerator, fraction.numerator) &&
        Objects.equals(denominator, fraction.denominator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Fraction.class.getSimpleName() + "{", "}")
        .add("numerator=" + numerator)
        .add("denominator=" + denominator)
        .toString();
  }
}
