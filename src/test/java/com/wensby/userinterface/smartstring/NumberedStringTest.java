package com.wensby.userinterface.smartstring;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberedStringTest {

  @Test
  public void formatsCorrectly() {
    BigDecimal left = BigDecimal.valueOf(12345L);
    BigDecimal right = BigDecimal.valueOf(45234L);
    var result = NumberedString.format("%s/%s", left, right);
    assertThat(result, is("13K/46K"));
  }
}