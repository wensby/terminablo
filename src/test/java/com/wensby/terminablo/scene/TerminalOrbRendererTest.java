package com.wensby.terminablo.scene;

import static java.awt.Color.RED;
import static java.math.BigDecimal.ONE;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.scene.playscene.DefaultOrb;
import com.wensby.terminablo.scene.playscene.OrbContentTerminalRenderer;
import com.wensby.terminablo.scene.playscene.TerminalOrbRenderer;
import com.wensby.terminablo.util.Fraction;
import org.junit.Before;
import org.junit.Test;

public class TerminalOrbRendererTest {

  private TerminalCharacterFactoryImpl characterFactory;
  private OrbContentTerminalRenderer contentRenderer;
  private TerminalOrbRenderer factory;

  @Before
  public void setUp() {
    characterFactory = new TerminalCharacterFactoryImpl();
    contentRenderer = mock(OrbContentTerminalRenderer.class);
    factory = new TerminalOrbRenderer(characterFactory, contentRenderer);
  }

  @Test
  public void render_when1By1Size_andFullHealth() {
    var orb = new DefaultOrb("HP", RED, new Fraction(ONE, ONE));
    var size = InterfaceSize.of(1, 1);
    var layer = new SparseLayer(size);
    var fullSection = new TerminalLayerSection(InterfaceLocation.atOrigin(), layer.getSize());
    var painter = (TerminalLayer) new TerminalLayerSubsection(layer, fullSection);

    factory.render(orb, painter);

    verify(contentRenderer).render(eq(orb), eq(painter));
   }
}