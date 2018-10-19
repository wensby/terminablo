package com.wensby.terminablo.scene.levelscene;

import static com.wensby.userinterface.InterfacePosition.atOrigin;

import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;

public class LevelSceneTerminalView implements LevelSceneView {

  private final LinuxTerminalVisualCanvas canvas;
  private final LevelSceneInterfaceRenderer levelSceneInterfaceRenderer;
  private final TerminalLevelRenderer terminalLevelRenderer;
  private final LevelSceneModel model;

  public LevelSceneTerminalView(LinuxTerminalVisualCanvas canvas,
      LevelSceneInterfaceRenderer levelSceneInterfaceRenderer,
      TerminalLevelRenderer terminalLevelRenderer,
      LevelSceneModel model) {
    this.canvas = canvas;
    this.levelSceneInterfaceRenderer = levelSceneInterfaceRenderer;
    this.terminalLevelRenderer = terminalLevelRenderer;
    this.model = model;
  }

  @Override
  public void render() {
    var level = model.getLevel();
    var hero = model.getHero();
    var heroLocation = level.locationOf(hero.getLevelEntity()).orElseThrow();
    var frame = canvas.createFrame();
    var interfaceSize = frame.getSize();
    var layerLevel = terminalLevelRenderer.render(level, heroLocation, interfaceSize);
    var layerInterface = levelSceneInterfaceRenderer.render(hero, interfaceSize);
    frame.put(atOrigin(), layerLevel);
    frame.put(atOrigin(), layerInterface);
    canvas.renderFrame(frame);
  }
}
