package com.wensby.terminablo.scene.playscene;

import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayerFactoryImpl;
import com.wensby.userinterface.linux.TerminalCharacterFactoryImpl;
import org.junit.Test;

import static java.awt.Color.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HealthBarRendererTest {

  @Test
  public void rendersCorrectly_whenEnoughSizeAndFullLife() {
    var characterFactory = new TerminalCharacterFactoryImpl();
    var layerFactory = new TerminalLayerFactoryImpl(characterFactory);
    var renderer = new HealthBarRenderer(layerFactory, characterFactory, "A", 1f, "B", "C");

    var render = renderer.render(InterfaceSize.of(1, 3));

    assertThat(render.getCharacter(InterfaceLocation.of(0, 0)), is(characterFactory.createCharacter('A', WHITE, RED)));
    assertThat(render.getCharacter(InterfaceLocation.of(0, 1)), is(characterFactory.createCharacter('B')));
    assertThat(render.getCharacter(InterfaceLocation.of(0, 2)), is(characterFactory.createCharacter('C')));
  }
}