package com.wensby.terminablo.scene;

import static java.awt.Color.RED;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.wensby.terminablo.scene.levelscene.DefaultOrb;
import com.wensby.terminablo.scene.levelscene.Orb;
import com.wensby.util.Fraction;
import org.junit.Test;

public class DefaultOrbTest {

  @Test
  public void getColor() {
    Orb orb = new DefaultOrb("HP", RED, new Fraction(ONE, TEN));
    assertThat(orb.getColor(), is(RED));
  }

  @Test
  public void getLabel() {
    Orb orb = new DefaultOrb("HP", RED, new Fraction(ONE, TEN));
    assertThat(orb.getLabel(), is("HP"));
  }

  @Test
  public void getCapacity() {
    Orb orb = new DefaultOrb("HP", RED, new Fraction(ONE, TEN));
    assertThat(orb.getValues().getDenominator(), is(TEN));
  }

  @Test
  public void getValue() {
    Orb orb = new DefaultOrb("HP", RED, new Fraction(ONE, TEN));
    assertThat(orb.getValues().getNumerator(), is(ONE));
  }
}