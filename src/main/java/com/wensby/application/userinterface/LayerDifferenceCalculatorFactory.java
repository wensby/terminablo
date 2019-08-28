package com.wensby.application.userinterface;

import java.util.*;

public class LayerDifferenceCalculatorFactory {

  private final TerminalCharacterFactory characterFactory;

  public LayerDifferenceCalculatorFactory(TerminalCharacterFactory characterFactory) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
  }

  public LayerDifferenceCalculator createLayerDifferenceCalculator(TerminalLayer prev, TerminalLayer next) {
    return new LayerDifferenceCalculator(characterFactory, prev, next);
  }


}
