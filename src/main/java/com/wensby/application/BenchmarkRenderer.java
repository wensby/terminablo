package com.wensby.application;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.scene.playscene.PartialBlockCharacterFactoryImpl;
import com.wensby.terminablo.userinterface.LayerWriterImpl;
import com.wensby.terminablo.util.UnitInterval;

import java.awt.*;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static java.util.Objects.requireNonNull;

public class BenchmarkRenderer {

  private final TerminalCharacterFactory characterFactory;
  private final Benchmark benchmark;
  private final PartialBlockCharacterFactoryImpl partialBlockCharacterFactory = new PartialBlockCharacterFactoryImpl();

  public BenchmarkRenderer(TerminalCharacterFactory characterFactory, Benchmark benchmark) {
    this.characterFactory = requireNonNull(characterFactory);
    this.benchmark = requireNonNull(benchmark);
  }

  public void render(TerminalLayerPainter painter) {
    var writer = new LayerWriterImpl(characterFactory, painter, false);
    var updateTimeMs = Long.toString(benchmark.getLastUpdateTime().toMillis());
    var renderTimeMs = Long.toString(benchmark.getLastRenderTime().toMillis());
    var tickTimeMs = Long.toString(benchmark.getLastTickTime().toMillis());
    renderGraph(painter);
    writer.write(
        "Update time ms: " + updateTimeMs + "\n" +
        "Render time ms: " + renderTimeMs + "\n" +
        "Tick time ms: " + tickTimeMs + "\n" +
        "Render string length: " + benchmark.getLastRenderStringLength(), at(0, 1));
  }

  private void renderGraph(TerminalLayerPainter painter) {
    var latestTickTimes = benchmark.getLatestTickTimes();
    for (int col = 0; col - 1 < painter.getAvailableSize().getWidth() && col < latestTickTimes.size(); col++) {
      var unit = UnitInterval.truncate((double) latestTickTimes.get(col).toMillis() / 30d);
      painter.put(createCharacter(unit), at(painter.getAvailableSize().getWidth() - col, 0));
    }
  }

  private TerminalCharacter createCharacter(UnitInterval unit) {
    if (unit.toIntRoundedDown(9) == 9) {
      return characterFactory.createCharacter(' ', new CharacterDecoration(Color.RED, null, false));
    }
    else {
      var partialBlockCharacter = this.partialBlockCharacterFactory.getPartialBlockCharacter(unit);
      return characterFactory.createCharacter(partialBlockCharacter);
    }
  }
}
