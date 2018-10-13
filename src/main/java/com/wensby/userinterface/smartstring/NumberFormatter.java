package com.wensby.userinterface.smartstring;

import static java.math.BigDecimal.ONE;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class NumberFormatter {

  public List<String> format(List<BigDecimal> numbers) {
    var commonMagnitudeGroup = getCommonMagnitudeGroup(numbers);
    return numbers.stream()
        .map(commonMagnitudeGroup::format)
        .collect(toList());
  }

  private MagnitudeGroup getCommonMagnitudeGroup(List<BigDecimal> numbers) {
    return numbers.stream()
        .map(this::getMagnitude)
        .max(Comparator.comparing(magnitudeGroup -> magnitudeGroup.magnitude))
        .orElseThrow();
  }

  private MagnitudeGroup getMagnitude(BigDecimal number) {
    if (number.compareTo(ONE) < 0) {
      return MagnitudeGroup.ONE;
    }
    return EnumSet.allOf(MagnitudeGroup.class).stream()
        .sorted((a, b) -> b.magnitude.compareTo(a.magnitude))
        .filter(magnitudeGroup -> magnitudeGroup.fits(number))
        .findFirst()
        .orElseThrow();
  }

  private enum MagnitudeGroup {
    ONE(BigDecimal.ONE, ""),
    THOUSANDS(BigDecimal.valueOf(1_000), "K"),
    MILLIONS(BigDecimal.valueOf(1_000_000L), "M");

    private final BigDecimal magnitude;
    private final String suffix;

    MagnitudeGroup(BigDecimal magnitude, String suffix) {
      this.magnitude = magnitude;
      this.suffix = suffix;
    }

    boolean fits(BigDecimal number) {
      return number.divide(magnitude, RoundingMode.DOWN).compareTo(BigDecimal.ONE) >= 0;
    }

    String format(BigDecimal number) {
      var roundedValue = number.divide(magnitude, RoundingMode.UP).toBigInteger().toString();
      return roundedValue + suffix;
    }
  }
}
