package com.wensby.terminablo.scene.playscene;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.atOrigin;

import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;

public class LevelSceneTerminalView implements LevelSceneView {

  private final TerminalLayerFactory layerFactory;
  private final LevelSceneInterfaceRenderer levelSceneInterfaceRenderer;
  private final TerminalLevelRenderer terminalLevelRenderer;
  private final LevelSceneModel model;

  public LevelSceneTerminalView(
      TerminalLayerFactory layerFactory,
      LevelSceneInterfaceRenderer levelSceneInterfaceRenderer,
      TerminalLevelRenderer terminalLevelRenderer,
      LevelSceneModel model) {
    this.layerFactory = layerFactory;
    this.levelSceneInterfaceRenderer = levelSceneInterfaceRenderer;
    this.terminalLevelRenderer = terminalLevelRenderer;
    this.model = model;
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    var level = model.getLevel();
    var hero = model.getHero();
    var heroLocation = level.locationOf(hero.getLevelEntity()).orElseThrow();
    var layerLevel = terminalLevelRenderer.render(level, heroLocation, size);
    var layerInterface = levelSceneInterfaceRenderer.render(hero, size);
    layerLevel.put(layerInterface, atOrigin());
    return layerLevel;
  }
}
