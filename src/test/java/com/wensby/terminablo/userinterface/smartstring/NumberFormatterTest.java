package com.wensby.terminablo.userinterface.smartstring;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;

public class NumberFormatterTest {

  private static final BigDecimal THOUSAND = BigDecimal.valueOf(1_000);
  private static final BigDecimal MILLION = BigDecimal.valueOf(1_000_000);

  @Test
  public void formatsSmallNumber() {
    var numbers = List.of(ONE);
    var formattedNumbers = new NumberFormatter().format(numbers);
    assertThat(formattedNumbers, hasItems("1"));
  }

  @Test
  public void formatsThousands() {
    var numbers = List.of(THOUSAND);
    var formattedNumbers = new NumberFormatter().format(numbers);
    assertThat(formattedNumbers, hasItems("1K"));
  }

  @Test
  public void formatsMillions() {
    var numbers = List.of(MILLION);
    var formattedNumbers = new NumberFormatter().format(numbers);
    assertThat(formattedNumbers, hasItems("1M"));
  }

  @Test
  public void formatsMillionsOfVariousMagnitudes() {
    var numbers = List.of(ZERO, ONE, THOUSAND, MILLION);
    var formattedNumbers = new NumberFormatter().format(numbers);
    assertThat(formattedNumbers, hasItems("0M", "1M", "1M", "1M"));
  }
}