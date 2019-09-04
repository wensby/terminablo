package com.wensby.application.userinterface;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LayerDifferenceCalculatorTest {

  private TerminalCharacterFactoryImpl characterFactory;

  @Before
  public void setUp() {
    characterFactory = new TerminalCharacterFactoryImpl();
  }

  @Test
  public void recognizesLongCharactersOverwritesPreviousCharacter() {
    var layerSize = InterfaceSize.of(2, 1);
    var prev = new SparseLayer(layerSize);
    var next = new SparseLayer(layerSize);
    var longCharacter = characterFactory.createCharacter("\uD83D\uDC7E");
    prev.put(characterFactory.createCharacter('1'), at(0, 0));
    prev.put(characterFactory.createCharacter('2'), at(1, 0));
    next.put(longCharacter, at(0, 0));
    var calculator = new LayerDifferenceCalculator(characterFactory, prev, next);

    var result = calculator.getDifference();

    assertThat(result, is(List.of(new PositionedTerminalCharacter(at(0, 0), longCharacter))));
  }

  @Test
  public void recognizesOverwrittenLongCharacters() {
    var layerSize = InterfaceSize.of(2, 1);
    var prev = new SparseLayer(layerSize);
    var next = new SparseLayer(layerSize);
    var longCharacter = characterFactory.createCharacter("\uD83D\uDC7E");
    var shortCharacter = characterFactory.createCharacter('1');
    prev.put(longCharacter, at(0, 0));
    next.put(shortCharacter, at(1, 0));
    var calculator = new LayerDifferenceCalculator(characterFactory, prev, next);

    var result = calculator.getDifference();

    assertThat(result, is(List.of(
        new PositionedTerminalCharacter(at(0, 0), characterFactory.createCharacter(' ')),
        new PositionedTerminalCharacter(at(1, 0), shortCharacter)
    )));
  }
}
