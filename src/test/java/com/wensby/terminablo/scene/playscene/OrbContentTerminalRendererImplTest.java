package com.wensby.terminablo.scene.playscene;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.util.Fraction;
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
    renderer = new OrbContentTerminalRendererImpl(partialBlockFactory, layerFactory, characterFactory);
  }

  @Test
  public void fullOrbRenderedWithFullColorBlock() {
    var orb = mock(Orb.class);
    var size = InterfaceSize.of(1, 1);
    var layer = mock(TerminalLayer.class);
    var colorLayer = mock(TerminalLayer.class);
    var color = mock(Color.class);
    when(orb.getColor()).thenReturn(color);
    when(orb.getValues()).thenReturn(new Fraction(ONE, ONE));
    when(layerFactory.createBlankLayer(size)).thenReturn(layer);
    when(layerFactory.createColoredLayer(size, color)).thenReturn(colorLayer);

    var result = renderer.render(orb, size);

    verify(layer).put(colorLayer, InterfaceLocation.at(0, 0));
    verifyNoMoreInteractions(layer);
    assertThat(result, is(layer));
  }

  @Test
  public void whenOrbFullWithWidth18Height8() {
    var orb = mock(Orb.class);
    var size = InterfaceSize.of(18, 8);
    var layer = mock(TerminalLayer.class);
    var character = mock(TerminalCharacter.class);
    var color = mock(Color.class);
    when(orb.getColor()).thenReturn(color);
    when(orb.getValues()).thenReturn(new Fraction(TEN, TEN));
    when(layerFactory.createBlankLayer(size)).thenReturn(layer);
    when(characterFactory.createCharacter(' ', null, color)).thenReturn(character);

    var result = renderer.render(orb, size);

    assertThat(result, is(layer));
  }

  @Test
  public void rendersPartialCharacters_whenPartiallyFilledOrb() {
    var orb = mock(Orb.class);
    var size = InterfaceSize.of(1, 1);
    var color = mock(Color.class);
    var character = ' ';
    var layer = mock(TerminalLayer.class);
    var terminalCharacter = mock(TerminalCharacter.class);
    when(layerFactory.createBlankLayer(size)).thenReturn(layer);
    when(orb.getColor()).thenReturn(color);
    when(orb.getValues()).thenReturn(new Fraction(ONE, TEN));
    when(partialBlockFactory.getPartialBlockCharacter(any())).thenReturn(character);
    when(characterFactory.createCharacter(character, color, null)).thenReturn(terminalCharacter);

    var result = renderer.render(orb, size);

    verify(layer).put(terminalCharacter, InterfaceLocation.at(0, 0));
    verifyNoMoreInteractions(layer);
    assertThat(result, is(layer));
  }
}