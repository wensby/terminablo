package com.wensby.terminablo.scene.levelscene;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.util.UnitInterval;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PartialBlockCharacterFactoryImplTest {

  private PartialBlockCharacterFactoryImpl factory;

  @Before
  public void setUp() {
    factory = new PartialBlockCharacterFactoryImpl();
  }

  @Test
  public void partialBlocks() {
    double step0 = 0.0 / 9.0;
    double step1 = 1.0 / 9.0;
    double step2 = 2.0 / 9.0;
    double step3 = 3.0 / 9.0;
    double step4 = 4.0 / 9.0;
    double step5 = 5.0 / 9.0;
    double step6 = 6.0 / 9.0;
    double step7 = 7.0 / 9.0;
    double step8 = 8.0 / 9.0;
    double step9 = 9.0 / 9.0;
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step0)), is(' '));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step0))), is(' '));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step1))), is(' '));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step1)), is('▁'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step1))), is('▁'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step2))), is('▁'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step2)), is('▂'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step2))), is('▂'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step3))), is('▂'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step3)), is('▃'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step3))), is('▃'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step4))), is('▃'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step4)), is('▄'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step4))), is('▄'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step5))), is('▄'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step5)), is('▅'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step5))), is('▅'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step6))), is('▅'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step6)), is('▆'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step6))), is('▆'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step7))), is('▆'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step7)), is('▇'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step7))), is('▇'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step8))), is('▇'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(step8)), is('█'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextUp(step8))), is('█'));
    assertThat(factory.getPartialBlockCharacter(UnitInterval.of(Math.nextDown(step9))), is('█'));
  }
}