package com.wensby.terminablo;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.atOrigin;
import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.linux.LinuxTerminalVisualCanvas;
import com.wensby.util.BenchmarkView;
import org.apache.log4j.Logger;

public class RendererImpl implements Renderer {

  private static final Logger LOGGER = Logger.getLogger(RendererImpl.class);

  private final LinuxTerminalVisualCanvas canvas;
  private final SceneStack sceneStack;
  private final BenchmarkModel benchmarkModel;
  private final BenchmarkView benchmarkView;

  RendererImpl(LinuxTerminalVisualCanvas canvas, SceneStack sceneStack,
      BenchmarkModel benchmarkModel, BenchmarkView benchmarkView) {
    this.canvas = requireNonNull(canvas);
    this.sceneStack = requireNonNull(sceneStack);
    this.benchmarkModel = requireNonNull(benchmarkModel);
    this.benchmarkView = benchmarkView;
  }

  @Override
  public void render() {
    var frame = canvas.createFrame();
    if (!sceneStack.isEmpty()) {
      var layer = sceneStack.getTop().render(frame.getSize());
      frame.put(layer, atOrigin());
    }
    if (benchmarkModel.isDisplayed()) {
      var layer = benchmarkView.render(frame.getSize());
      frame.put(layer, atOrigin());
    }
    LOGGER.debug("Rendering frame of size " + frame.getSize());
    canvas.renderFrame(frame);
  }
}
