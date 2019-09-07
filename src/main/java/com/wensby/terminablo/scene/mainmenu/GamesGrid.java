package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.Game;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.userinterface.reactive.VerticalScroll;
import com.wensby.terminablo.userinterface.reactive.SimpleGrid;
import com.wensby.terminablo.util.UnitInterval;
import com.wensby.terminablo.world.level.LevelEntityImpl;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class GamesGrid extends ReactiveComponent {

  private final List<Game> games;
  private final TerminalCharacterFactory characterFactory;
  private final float visibleRows;
  private final float itemsPerRow;
  private final int requiredRows;

  public GamesGrid(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
    var character = new PlayerCharacter("Dj", null, new LevelEntityImpl(characterFactory.createCharacter('A')));
    games = IntStream.range(0, 12).mapToObj(a -> character).map(Game::new).collect(toList());
    setSelectedGameIndex(0);
    setTopRow(0);
    visibleRows = 4f;
    itemsPerRow = 2f;
    requiredRows = (int) (games.size() / itemsPerRow + 0.5f);
  }

  private void setTopRow(int row) {
    setState("topRow", row);
  }

  @Override
  public Component render() {
    var characterGrid = createCharacterGrid();
    var scroll = (float)getTopRow() / (Math.max(requiredRows, visibleRows) - 4f);
    return new VerticalScroll(characterFactory, characterGrid, requiredRows / visibleRows, UnitInterval.of(scroll));
  }

  private int getTopRow() {
    return getState("topRow", Integer.class);
  }

  private SimpleGrid createCharacterGrid() {
    return new SimpleGrid(createGameGridItems(), 2);
  }

  private List<GameGridItem> createGameGridItems() {
    return IntStream.range(0, games.size())
        .mapToObj(i -> {
          var selected = i == getSelectedGameIndex();
          return new GameGridItem(games.get(i).getCharacter(), selected, characterFactory);
        })
        .collect(toList());
  }

  @Override
  public void sendKeys(List<Key> keys) {
    if (keys.contains(Key.ARROW_DOWN)) {
      if (!isOnLastRow()) {
        incrementSelectedGameIndex();
        incrementSelectedGameIndex();
      }
    }
    if (keys.contains(Key.ARROW_UP)) {
      if (getRowOfSelected() > 0) {
        decrementSelectedGameIndex();
        decrementSelectedGameIndex();
      }
    }
    if (keys.contains(Key.ARROW_RIGHT)) {
      incrementSelectedGameIndex();
    }
    if (keys.contains(Key.ARROW_LEFT)) {
      decrementSelectedGameIndex();
    }
    super.sendKeys(keys);
  }

  private boolean isOnLastRow() {
    return getRowOfSelected() == requiredRows - 1;
  }

  private void decrementSelectedGameIndex() {
    var decremented = getSelectedGameIndex() - 1;
    setSelectedGameIndex(Math.max(0, decremented));
    if (getRowOfSelected() < getTopRow()) {
      setTopRow(getTopRow() - 1);
    }
  }

  private void incrementSelectedGameIndex() {
    var incremented = getSelectedGameIndex() + 1;
    setSelectedGameIndex(Math.min(games.size() - 1, incremented));
    if (getRowOfSelected() >= getTopRow() + 4) {
      setTopRow(getTopRow() + 1);
    }
  }

  private void setSelectedGameIndex(int index) {
    setState("selectedGameIndex", index);
  }

  private int getRowOfSelected() {
    return getSelectedGameIndex() / 2;
  }

  private int getSelectedGameIndex() {
    return getState("selectedGameIndex", Integer.class);
  }
}
