package com.wensby.application;

import static com.wensby.terminablo.userinterface.component.InterfaceLocation.at;
import static com.wensby.terminablo.userinterface.component.InterfaceLocation.atOrigin;
import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCanvas;
import com.wensby.application.userinterface.BenchmarkView;
import com.wensby.application.userinterface.TerminalFrame;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
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
      renderApplication(frame, at(0, benchmarkLayerHeight));
    }
    else {
      renderApplication(frame, atOrigin());
    }
    LOGGER.debug("Rendering frame at size " + frame.getSize());
    var renderResult = canvas.renderFrame(frame);
    benchmarkModel.setLastRenderStringLength(renderResult.getRenderStringLength());
  }

  private void renderApplication(TerminalFrame frame, InterfaceLocation topLeft) {
    frame.put(applicationRenderer.renderFrame(frame.getSize().minus(InterfaceSize.of(0, topLeft.getRow()))), topLeft);
  }
}
