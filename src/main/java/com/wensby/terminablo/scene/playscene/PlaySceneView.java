package com.wensby.terminablo.scene.playscene;

import com.wensby.application.userinterface.TerminalLayerPainter;
import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.scene.View;

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
  public void render(TerminalLayerPainter painter) {
    var level = model.getLevel();
    var hero = model.getCharacter();
    var heroLocation = level.locationOf(hero.getLevelEntity()).orElseThrow();
    terminalLevelRenderer.render(level, heroLocation, painter);
    playSceneInterfaceRenderer.render(hero, painter, model);
  }
}
