package com.wensby.terminablo.world.level;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LevelFactory {

  private final LevelEntityFactory levelEntityFactory;

  public LevelFactory(LevelEntityFactory levelEntityFactory) {
    this.levelEntityFactory = levelEntityFactory;
  }
  
  public Level createLevelFromResourceFile(Path resourcePath) {
    final String mapString = readString(resourcePath);
    return createLevelFromString(mapString);
  }

  private String readString(Path filepath) {
    try (var lines = Files.lines(filepath)){
      return lines.collect(joining("\n"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Level createLevelFromString(String stringRepresentation) {
    var lines = stringRepresentation.split("\n");
    var y = 0;
    var entities = new HashMap<LevelLocation, Set<LevelEntity>>();
    for (var line : lines) {
      for (int x = 0; x < line.length(); x++) {
        var entity = parseEntity(line.charAt(x));
        if (entity.isPresent()) {
          var location = LevelLocation.of(x, y);
          entities.computeIfAbsent(location, levelLocation -> new HashSet<>()).add(entity.get());
        }
      }
      y++;
    }
    return new LevelImpl(entities);
  }

  private Optional<LevelEntity> parseEntity(char c) {
    if (c == '#') {
      return Optional.of(levelEntityFactory.createWall());
    } 
    else {
      return Optional.empty();
    }
  }
}
