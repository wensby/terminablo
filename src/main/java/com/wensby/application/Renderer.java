package com.wensby.application;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.at;
import static com.wensby.terminablo.userinterface.component.InterfaceLocation.atOrigin;
import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.terminablo.userinterface.TerminalCanvas;
import com.wensby.util.BenchmarkView;
import org.apache.log4j.Logger;

public class Renderer {

  private static final Logger LOGGER = Logger.getLogger(Renderer.class);

  private final TerminalCanvas canvas;
  private final BenchmarkModel benchmarkModel;
  private final BenchmarkView benchmarkView;
  private final ApplicationRenderer applicationRenderer;

  public Renderer(TerminalCanvas canvas,
      BenchmarkModel benchmarkModel, BenchmarkView benchmarkView,
      ApplicationRenderer applicationRenderer) {
    this.canvas = requireNonNull(canvas);
    this.benchmarkModel = requireNonNull(benchmarkModel);
    this.benchmarkView = benchmarkView;
    this.applicationRenderer = requireNonNull(applicationRenderer);
  }

  public void render() {
    var frame = canvas.createFrame();
    if (benchmarkModel.isDisplayed()) {
      var benchmarkLayer = benchmarkView.render(frame.getSize());
      frame.put(benchmarkLayer, atOrigin());
      var benchmarkLayerHeight = benchmarkLayer.getSize().getHeight();
      frame.put(applicationRenderer.renderFrame(frame.getSize().minus(InterfaceSize.of(0, benchmarkLayerHeight))), at(0, benchmarkLayerHeight));
    }
    else {
      frame.put(applicationRenderer.renderFrame(frame.getSize()), atOrigin());
    }
    LOGGER.debug("Rendering frame at size " + frame.getSize());
    canvas.renderFrame(frame);
  }
}
