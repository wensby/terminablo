package com.wensby.terminablo;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.world.level.LevelEntityFactory;
import com.wensby.terminablo.world.level.LevelFactory;
import com.wensby.terminablo.world.level.LevelLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class TerminalLevelRendererTest {

  private LevelEntityRenderer entityRenderer;
  private TerminalLayerFactory layerFactory;
  private LevelEntityFactory entityFactory;
  private LevelFactory levelFactory;

  @Before
  public void setUp() throws Exception {
    layerFactory = mock(TerminalLayerFactory.class);
    entityRenderer = mock(LevelEntityRenderer.class);
    entityFactory = new LevelEntityFactory();
    levelFactory = new LevelFactory(entityFactory);
  }

  @Test
  public void name() {
    var interfaceSize = InterfaceSize.of(5, 5);
    when(entityRenderer.getTerminalCharacter(any())).thenReturn(Optional.of(new SimpleTerminalCharacterImpl('#')));
    var renderer = new TerminalLevelRenderer(layerFactory, entityRenderer);
    when(layerFactory.createBlankLayer(interfaceSize)).thenReturn(new TwoDimensionalCharacterArrayLayer(new TerminalCharacter[5][5]));
    var level = levelFactory.createLevelFromString("###\n# #\n###");
    renderer.render(level, LevelLocation.of(1, 1), interfaceSize);
  }
}