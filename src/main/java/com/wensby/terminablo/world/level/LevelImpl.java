package com.wensby.terminablo.world.level;

import java.util.*;

public class LevelImpl implements Level {

  private final Map<LevelLocation, Set<LevelEntity>> entitiesByLocation;
  private final Map<LevelEntity, LevelLocation> locationByEntity;

  public LevelImpl(Map<LevelLocation, Set<LevelEntity>> entitiesByLocation) {
    this.entitiesByLocation = new HashMap<>(entitiesByLocation);
    this.locationByEntity = new HashMap<>();
    for (var entry : entitiesByLocation.entrySet()) {
      var location = entry.getKey();
      for (var entity : entry.getValue()) {
        this.locationByEntity.put(entity, location);
      }
    }
  }

  @Override
  public Set<LevelEntity> entities(LevelLocation location) {
    return entitiesByLocation.getOrDefault(location, Set.of());
  }

  @Override
  public Optional<LevelLocation> locationOf(LevelEntity entity) {
    return Optional.ofNullable(locationByEntity.getOrDefault(entity, null));
  }

  @Override
  public void putEntity(LevelLocation location, LevelEntity entity) {
    entitiesByLocation.computeIfAbsent(location, levelLocation -> new HashSet<>()).add(entity);
    locationByEntity.put(entity, location);
  }

  @Override
  public Optional<LevelLocation> removeEntity(LevelEntity entity) {
    var location = locationOf(entity);
    if (location.isPresent()) {
      locationByEntity.remove(entity);
      entitiesByLocation.get(location.get()).remove(entity);
      return location;
    }
    return Optional.empty();
  }

}
