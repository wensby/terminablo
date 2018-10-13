package com.wensby.terminablo.world.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LevelFactory {

  private final LevelEntityFactory levelEntityFactory;

  public LevelFactory(LevelEntityFactory levelEntityFactory) {
    this.levelEntityFactory = levelEntityFactory;
  }

  public Level createFactoryFromString(String stringRepresentation) {
    var lines = stringRepresentation.split("\n");
    var y = 0;
    var entities = new HashMap<LevelLocation, List<LevelEntity>>();
    for (var line : lines) {
      for (int x = 0; x < line.length(); x++) {
        var entity = parseEntity(line.charAt(x));
        if (entity.isPresent()) {
          var location = LevelLocation.of(x, y);
          entities
              .computeIfAbsent(location, levelLocation -> new ArrayList<>());
          entities.get(location).add(entity.get());
        }
      }
      y++;
    }
    return new LevelImpl(entities);
  }

  private Optional<LevelEntity> parseEntity(char c) {
    return c == '#' ? Optional.of(levelEntityFactory.createWall()) : Optional.empty();
  }
}
