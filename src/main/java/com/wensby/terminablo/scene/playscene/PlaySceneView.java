package com.wensby.terminablo.scene.playscene;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.atOrigin;

import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.scene.View;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;

public class PlaySceneView implements View {

  private final PlaySceneInterfaceRenderer playSceneInterfaceRenderer;
  private final TerminalLevelRenderer terminalLevelRenderer;
  private final PlaySceneModel model;

  public PlaySceneView(
      PlaySceneInterfaceRenderer playSceneInterfaceRenderer,
      TerminalLevelRenderer terminalLevelRenderer,
      PlaySceneModel model) {
    this.playSceneInterfaceRenderer = playSceneInterfaceRenderer;
    this.terminalLevelRenderer = terminalLevelRenderer;
    this.model = model;
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    var level = model.getLevel();
    var hero = model.getCharacter();
    var heroLocation = level.locationOf(hero.getLevelEntity()).orElseThrow();
    var layerLevel = terminalLevelRenderer.render(level, heroLocation, size);
    var layerInterface = playSceneInterfaceRenderer.render(hero, size, model);
    layerLevel.put(layerInterface, atOrigin());
    return layerLevel;
  }
}
