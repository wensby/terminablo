package com.wensby.terminablo.world.level;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class LevelFactoryTest {

  private LevelEntityFactory levelEntityFactory;

  @Before
  public void setUp() {
    levelEntityFactory = mock(LevelEntityFactory.class);
  }

  @Test
  public void createsSimpleLevelFromString() {
    var wallEntity = mock(LevelEntity.class);
    when(levelEntityFactory.createWall()).thenReturn(wallEntity);
    var levelFactory = new LevelFactory(levelEntityFactory);

    var level = levelFactory.createFactoryFromString(
        "###\n" +
        "# #\n" +
        "###");

    assertThat(level.entities(LevelLocation.of(0, 0)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(1, 0)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(2, 0)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(0, 1)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(1, 1)), is(List.of()));
    assertThat(level.entities(LevelLocation.of(2, 1)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(0, 2)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(1, 2)), hasItem(wallEntity));
    assertThat(level.entities(LevelLocation.of(2, 2)), hasItem(wallEntity));
  }
}