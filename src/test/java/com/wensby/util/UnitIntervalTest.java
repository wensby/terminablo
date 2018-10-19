package com.wensby.util;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class UnitIntervalTest {

  @Test
  public void toIntRoundedDown() {
    assertThat(UnitInterval.of(0.5).toIntRoundedDown(10), is(5));
    assertThat(UnitInterval.of(1).toIntRoundedDown(10), is(10));
  }
}