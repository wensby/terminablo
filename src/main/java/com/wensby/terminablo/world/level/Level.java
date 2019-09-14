package com.wensby.terminablo.world.level;

import java.util.*;

public class Level {

  private final Map<LevelLocation, Set<LevelEntity>> entitiesByLocation;
  private final Map<LevelEntity, LevelLocation> locationByEntity;

  public Level(Map<LevelLocation, Set<LevelEntity>> entitiesByLocation) {
    this.entitiesByLocation = new HashMap<>(entitiesByLocation);
    this.locationByEntity = new HashMap<>();
    for (var levelLocationSetEntry : entitiesByLocation.entrySet()) {
      levelLocationSetEntry.getValue().forEach(levelEntity -> locationByEntity.put(levelEntity, levelLocationSetEntry.getKey()));
    }
  }

  public Set<LevelEntity> entities(LevelLocation location) {
    return entitiesByLocation.getOrDefault(location, Set.of());
  }

  public void putEntity(LevelLocation location, LevelEntity entity) {
    entitiesByLocation.computeIfAbsent(location, levelLocation -> new HashSet<>()).add(entity);
    locationByEntity.put(entity, location);
  }

  public void removeEntity(LevelLocation location, LevelEntity entity) {
    entitiesByLocation.get(location).remove(entity);
    locationByEntity.remove(entity);
  }

  public Optional<LevelLocation> locationOf(LevelEntity entity) {
    return Optional.ofNullable(locationByEntity.getOrDefault(entity, null));
  }
}
