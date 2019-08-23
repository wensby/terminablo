package com.wensby.terminablo.userinterface;

import org.junit.Test;

import java.util.List;

import static com.wensby.terminablo.userinterface.Key.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KeyParserTest {

  @Test
  public void parsesSingleByteKeys() {
    assertThat(new KeyParser(List.of(48)).parseKey(), is(ZERO));
  }

  @Test
  public void parsesDoubleByteKeys() {
    assertThat(new KeyParser(List.of(194, 167)).parseKey(), is(PARAGRAPH));
  }

  @Test
  public void parsesEscapedKeys() {
    assertThat(new KeyParser(List.of(27)).parseKey(), is(ESCAPE));
    assertThat(new KeyParser(List.of(27, 91, 65)).parseKey(), is(ARROW_UP));
  }
}