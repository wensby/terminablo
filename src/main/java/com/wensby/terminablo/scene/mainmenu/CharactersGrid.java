package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.userinterface.reactive.Scroll;
import com.wensby.terminablo.userinterface.reactive.SimpleGrid;
import com.wensby.terminablo.world.level.LevelEntityImpl;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CharactersGrid extends ReactiveComponent {

  private final List<PlayerCharacter> characters;
  private final TerminalCharacterFactory characterFactory;
  private final float visibleRows;
  private final float itemsPerRow;
  private final int requiredRows;

  public CharactersGrid(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
    var character = new PlayerCharacter("Dj", null, new LevelEntityImpl(characterFactory.createCharacter('A')));
    characters = IntStream.range(0, 12).mapToObj(a -> character).collect(toList());
    setSelectedCharacterIndex(0);
    setTopRow(0);
    visibleRows = 4f;
    itemsPerRow = 2f;
    requiredRows = (int) (characters.size() / itemsPerRow + 0.5f);
  }

  private void setTopRow(int row) {
    setState("topRow", row);
  }

  @Override
  public Component render() {
    var characterGrid = createCharacterGrid();
    return new Scroll(characterGrid, requiredRows / visibleRows, - (getTopRow() / (float) requiredRows));
  }

  private int getTopRow() {
    return getState("topRow", Integer.class);
  }

  private SimpleGrid createCharacterGrid() {
    return new SimpleGrid(createCharacterGridItems(), 2);
  }

  private List<CharactersGridItem> createCharacterGridItems() {
    return IntStream.range(0, characters.size())
        .mapToObj(i -> {
          var selected = i == getSelectedCharacterIndex();
          return new CharactersGridItem(characters.get(i), selected, characterFactory);
        })
        .collect(toList());
  }

  @Override
  public void sendKeys(List<Key> keys) {
    if (keys.contains(Key.ARROW_DOWN)) {
      if (!isOnLastRow()) {
        incrementSelectedCharacterIndex();
        incrementSelectedCharacterIndex();
      }
    }
    if (keys.contains(Key.ARROW_UP)) {
      if (getRowOfSelected() > 0) {
        decrementSelectedCharacterIndex();
        decrementSelectedCharacterIndex();
      }
    }
    if (keys.contains(Key.ARROW_RIGHT)) {
      incrementSelectedCharacterIndex();
    }
    if (keys.contains(Key.ARROW_LEFT)) {
      decrementSelectedCharacterIndex();
    }
    super.sendKeys(keys);
  }

  private boolean isOnLastRow() {
    return getRowOfSelected() == requiredRows - 1;
  }

  private void decrementSelectedCharacterIndex() {
    var decremented = getSelectedCharacterIndex() - 1;
    setSelectedCharacterIndex(Math.max(0, decremented));
    if (getRowOfSelected() < getTopRow()) {
      setTopRow(getTopRow() - 1);
    }
  }

  private void incrementSelectedCharacterIndex() {
    var incremented = getSelectedCharacterIndex() + 1;
    setSelectedCharacterIndex(Math.min(characters.size() - 1, incremented));
    if (getRowOfSelected() >= getTopRow() + 4) {
      setTopRow(getTopRow() + 1);
    }
  }

  private void setSelectedCharacterIndex(int index) {
    setState("selectedCharacterIndex", index);
  }

  private int getRowOfSelected() {
    return getSelectedCharacterIndex() / 2;
  }

  private int getSelectedCharacterIndex() {
    return getState("selectedCharacterIndex", Integer.class);
  }
}
