package com.wensby.terminablo.scene;

import static java.awt.Color.RED;
import static java.math.BigDecimal.ONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wensby.terminablo.scene.levelscene.DefaultOrb;
import com.wensby.terminablo.scene.levelscene.OrbContentTerminalRenderer;
import com.wensby.terminablo.scene.levelscene.TerminalOrbRenderer;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.util.Fraction;
import org.junit.Before;
import org.junit.Test;

public class TerminalOrbRendererTest {

  private TerminalLayerFactory layerFactory;
  private LinuxTerminalCharacterFactory characterFactory;
  private OrbContentTerminalRenderer contentRenderer;
  private TerminalOrbRenderer factory;

  @Before
  public void setUp() {
    layerFactory = mock(TerminalLayerFactory.class);
    characterFactory = new LinuxTerminalCharacterFactory();
    contentRenderer = mock(OrbContentTerminalRenderer.class);
    factory = new TerminalOrbRenderer(layerFactory, characterFactory, contentRenderer);
  }

  @Test
  public void render_when1By1Size_andFullHealth() {
    var orb = new DefaultOrb("HP", RED, new Fraction(ONE, ONE));
    var size = InterfaceSize.of(1, 1);
    var layer = mock(TerminalLayer.class);
    var orbContentLayer = mock(TerminalLayer.class);
    when(layerFactory.createBlankLayer(size)).thenReturn(layer);
    when(contentRenderer.render(orb, size)).thenReturn(orbContentLayer);

    var representation = factory.render(orb, size);

    verify(layer).put(orbContentLayer, InterfaceLocation.atOrigin());
    assertThat(representation, is(layer));
   }
}