package com.wensby.application;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;
import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
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
    var painter = frame.getPainter();
    if (benchmarkModel.isDisplayed()) {
      var sectionSize = InterfaceSize.of(frame.getSize().getWidth(), 5);
      benchmarkView.render(painter.createSubsectionPainter(atOrigin(), sectionSize));
      var applicationViewportSize = frame.getSize().minus(InterfaceSize.of(0, 5));
      var applicationPainter = painter.createSubsectionPainter(at(0, 5), applicationViewportSize);
      applicationRenderer.renderApplication(applicationPainter);
    }
    else {
      applicationRenderer.renderApplication(painter);
    }
    LOGGER.debug("Rendering frame at size " + frame.getSize());
    var renderResult = canvas.renderFrame(frame);
    benchmarkModel.setLastRenderStringLength(renderResult.getRenderStringLength());
  }
}
