package com.wensby.terminablo.scene;

import static java.awt.Color.RED;
import static java.math.BigDecimal.ONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.LinuxDecorativeCharacter;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

public class OrbTerminalRepresentationFactoryTest {

  @Test
  public void createRepresentation_when1By1Size_andFullHealth() {
    TerminalCharacterFactory characterFactory = new LinuxTerminalCharacterFactory();
    OrbTerminalRepresentationFactory factory = new OrbTerminalRepresentationFactory(
        characterFactory);
    Orb orb = new DefaultOrb("HP", RED, ONE, ONE);
    InterfaceSize size = new InterfaceSize(1, 1);

    TerminalLayer representation = factory.createRepresentation(orb, size);

    assertThat(representation.getCharacters()[0][0], instanceOf(LinuxDecorativeCharacter.class));
    LinuxDecorativeCharacter character = (LinuxDecorativeCharacter) representation
        .getCharacters()[0][0];
    assertThat(character.getBackgroundColor(), is(RED));
    assertThat(character.getCharSequence().toString(), is(String.valueOf(' ')));
  }

  @Test
  public void createRepresentation_when1By1Size_andPartialHealth() {
    Map<BigDecimal, Character> expectedCharacterByValue = new HashMap<>();
    expectedCharacterByValue.put(new BigDecimal(1), '▁');
    expectedCharacterByValue.put(new BigDecimal(2), '▂');
    expectedCharacterByValue.put(new BigDecimal(3), '▃');
    expectedCharacterByValue.put(new BigDecimal(4), '▄');
    expectedCharacterByValue.put(new BigDecimal(5), '▅');
    expectedCharacterByValue.put(new BigDecimal(6), '▆');
    expectedCharacterByValue.put(new BigDecimal(7), '▇');
    expectedCharacterByValue.put(new BigDecimal(8), '█');

    for (Entry<BigDecimal, Character> entry : expectedCharacterByValue.entrySet()) {
      TerminalCharacterFactory characterFactory = new LinuxTerminalCharacterFactory();
      OrbTerminalRepresentationFactory factory = new OrbTerminalRepresentationFactory(
          characterFactory);
      Orb orb = new DefaultOrb("HP", RED, new BigDecimal(9), entry.getKey());
      InterfaceSize size = new InterfaceSize(1, 1);

      TerminalLayer representation = factory.createRepresentation(orb, size);

      assertThat(representation.getCharacters()[0][0], instanceOf(LinuxDecorativeCharacter.class));
      LinuxDecorativeCharacter character = (LinuxDecorativeCharacter) representation
          .getCharacters()[0][0];
      assertThat(character.getForegroundColor(), is(RED));
      assertThat(character.getCharSequence().toString(), is(String.valueOf(entry.getValue())));
    }
  }
}