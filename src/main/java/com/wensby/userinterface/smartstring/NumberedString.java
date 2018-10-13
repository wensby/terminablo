package com.wensby.userinterface.smartstring;

import java.math.BigDecimal;
import java.util.List;

public class NumberedString {

  public static String format(String format, BigDecimal ... numbers) {
    var formattedNumbers = new NumberFormatter().format(List.of(numbers));
    return String.format(format, formattedNumbers.toArray());
  }
}
