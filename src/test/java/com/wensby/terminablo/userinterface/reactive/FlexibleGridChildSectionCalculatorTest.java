package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalLayerSection;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;
import static com.wensby.application.userinterface.InterfaceSize.of;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FlexibleGridChildSectionCalculatorTest {

  @Test
  public void whenUnitSize_oneRow_oneColumn_oneChild() {
    var component = mock(Component.class);
    var totalSize = of(1, 1);
    var columnRatios = List.of(1);
    var rowRatios = List.of(1);
    var layoutRows = List.of(List.of("key"));
    var child = new FlexibleGridChild("key", component);
    var calculator = new FlexibleGridChildSectionCalculator(totalSize, columnRatios, rowRatios, layoutRows, child);

    var section = calculator.findChildLayerSection();

    assertThat(section, is(Optional.of(new TerminalLayerSection(atOrigin(), of(1, 1)))));
  }

  @Test
  public void whenTwoColumns_oneRow_oneChild() {
    var component = mock(Component.class);
    var totalSize = of(2, 1);
    var columnRatios = List.of(1, 1);
    var rowRatios = List.of(1);
    var layoutRows = List.of(List.of("_", "key"));
    var child = new FlexibleGridChild("key", component);
    var calculator = new FlexibleGridChildSectionCalculator(totalSize, columnRatios, rowRatios, layoutRows, child);

    var section = calculator.findChildLayerSection();

    assertThat(section, is(Optional.of(new TerminalLayerSection(at(1, 0), of(1, 1)))));
  }
}