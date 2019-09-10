package com.wensby.terminablo.scene.playscene;

import com.wensby.application.userinterface.*;
import com.wensby.application.userinterface.InterfaceLocation;
import org.junit.Test;

import static java.awt.Color.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HealthBarRendererTest {

  @Test
  public void rendersCorrectly_whenEnoughSizeAndFullLife() {
    var characterFactory = new TerminalCharacterFactoryImpl();
    var renderer = new HealthBarRenderer(characterFactory, "A", 1f, "B", "C");
    var layer = new SparseLayer(InterfaceSize.of(1, 3));
    var fullSection = new TerminalLayerSection(InterfaceLocation.atOrigin(), layer.size());
    var painter = (TerminalLayer) new TerminalLayerSubsection(layer, fullSection);

    renderer.render(painter);

    assertThat(layer.getCharacter(InterfaceLocation.at(0, 0)), is(characterFactory.createCharacter('A', new CharacterDecoration(RED, WHITE, false))));
    assertThat(layer.getCharacter(InterfaceLocation.at(0, 1)), is(characterFactory.createCharacter('B')));
    assertThat(layer.getCharacter(InterfaceLocation.at(0, 2)), is(characterFactory.createCharacter('C')));
  }
}