package com.wensby.terminablo;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wensby.terminablo.world.level.Level;
import com.wensby.terminablo.world.level.LevelEntityFactory;
import com.wensby.terminablo.world.level.LevelFactory;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.SimpleTerminalCharacter;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalLayerFactory;
import com.wensby.userinterface.TerminalLayerImpl;
import org.junit.Before;
import org.junit.Test;

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
    var interfaceSize = new InterfaceSize(5, 5);
    when(entityRenderer.getTerminalCharacter(any())).thenReturn(new SimpleTerminalCharacter('#'));
    var renderer = new TerminalLevelRenderer(layerFactory, entityRenderer);
    when(layerFactory.createTerminalLayer(interfaceSize)).thenReturn(new TerminalLayerImpl(new TerminalCharacter[5][5]));
    var level = levelFactory.createFactoryFromString("###\n# #\n###");
    renderer.render(level, LevelLocation.of(1, 1), interfaceSize);
  }
}