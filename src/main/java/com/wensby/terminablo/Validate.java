package com.wensby.terminablo;

public class Validate {

  public static void notNegative(int integer, String message) {
    if (integer < 0) {
      throw new IllegalArgumentException(message);
    }
  }
}
