package com.wensby.terminablo.scene.playscene;

import static com.wensby.application.userinterface.InterfaceSize.of;
import static com.wensby.application.userinterface.InterfaceLocation.at;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.util.Fraction;
import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class OrbContentTerminalRendererImplTest {

  private TerminalLayerFactory layerFactory;
  private TerminalCharacterFactory characterFactory;
  private PartialBlockCharacterFactory partialBlockFactory;
  private OrbContentTerminalRendererImpl renderer;

  @Before
  public void setUp() {
    partialBlockFactory = mock(PartialBlockCharacterFactory.class);
    layerFactory = mock(TerminalLayerFactory.class);
    characterFactory = mock(TerminalCharacterFactory.class);
    renderer = new OrbContentTerminalRendererImpl(partialBlockFactory, characterFactory);
  }

  @Test
  public void fullOrbRenderedWithFullColorBlock() {
    var orb = mock(Orb.class);
    var color = mock(Color.class);
    var layer = new SparseLayer(of(1, 1));
    var fullSection = new TerminalLayerSection(InterfaceLocation.atOrigin(), layer.size());
    var painter = (TerminalLayer) new TerminalLayerSubsection(layer, fullSection);
    var character = mock(TerminalCharacter.class);
    when(orb.getColor()).thenReturn(color);
    when(orb.getValues()).thenReturn(new Fraction(ONE, ONE));
    when(characterFactory.createCharacter(eq(' '), any())).thenReturn(character);

    renderer.render(orb, painter);

    assertThat(layer.getCharacter(at(0, 0)), is(character));
  }

  @Test
  public void rendersPartialCharacters_whenPartiallyFilledOrb() {
    var orb = mock(Orb.class);
    var size = of(1, 1);
    var color = mock(Color.class);
    var character = ' ';
    var terminalCharacter = mock(TerminalCharacter.class);
    var layer = new SparseLayer(size);
    var fullSection = new TerminalLayerSection(InterfaceLocation.atOrigin(), layer.size());
    var painter = (TerminalLayer) new TerminalLayerSubsection(layer, fullSection);
    when(layerFactory.createBlankLayer(size)).thenReturn(layer);
    when(orb.getColor()).thenReturn(color);
    when(orb.getValues()).thenReturn(new Fraction(ONE, TEN));
    when(partialBlockFactory.getPartialBlockCharacter(any())).thenReturn(character);
    when(characterFactory.createCharacter(eq(character), any())).thenReturn(terminalCharacter);

    renderer.render(orb, painter);

    assertThat(layer.getCharacter(at(0, 0)), is(terminalCharacter));
  }
}