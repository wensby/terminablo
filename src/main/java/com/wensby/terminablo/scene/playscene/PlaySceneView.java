package com.wensby.terminablo.scene.playscene;

import com.wensby.application.userinterface.TerminalLayer;
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
  public void render(TerminalLayer layer) {
    var level = model.getLevel();
    var hero = model.getCharacterPresence();
    var heroLocation = level.locationOf(hero).orElseThrow();
    terminalLevelRenderer.render(level, heroLocation, layer);
    playSceneInterfaceRenderer.render(hero, layer, model);
  }
}
