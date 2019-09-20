package com.wensby.terminablo.util;

import java.util.Collection;
import java.util.List;

import static java.util.List.copyOf;


public class Integers {

  private final List<Integer> integers;

  public Integers(Collection<Integer> integers) {
    this.integers = copyOf(integers);
  }

  public int sum() {
    return this.integers.stream().mapToInt(Integer::intValue).sum();
  }
}
