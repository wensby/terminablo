package com.wensby.terminablo.world.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class LevelImpl implements Level {

  private final Map<LevelLocation, List<LevelEntity>> entitiesByLocation;

  public LevelImpl(Map<LevelLocation, List<LevelEntity>> entitiesByLocation) {
    this.entitiesByLocation = new HashMap<>(entitiesByLocation);
  }

  @Override
  public List<LevelEntity> entities(LevelLocation location) {
    return entitiesByLocation.getOrDefault(location, List.of());
  }

  @Override
  public Optional<LevelLocation> locationOf(LevelEntity entity) {
    return entryOfEntity(entity)
        .map(Entry::getKey);
  }

  private Optional<Entry<LevelLocation, List<LevelEntity>>> entryOfEntity(LevelEntity entity) {
    return entitiesByLocation.entrySet().parallelStream()
        .filter(entry -> containsEntity(entry, entity))
        .findAny();
  }

  @Override
  public void putEntity(LevelLocation location, LevelEntity entity) {
    entitiesByLocation.computeIfAbsent(location, levelLocation -> new ArrayList<>());
    entitiesByLocation.get(location).add(entity);
  }

  @Override
  public Optional<LevelLocation> removeEntity(LevelEntity entity) {
    final var levelLocationListEntry = entryOfEntity(
        entity);
    if (levelLocationListEntry.isPresent()) {
      levelLocationListEntry.get().getValue().remove(entity);
      return Optional.of(levelLocationListEntry.get().getKey());
    }
    return Optional.empty();
  }

  private boolean containsEntity(Entry<LevelLocation, List<LevelEntity>> entry,
      LevelEntity entity) {
    return entry.getValue().parallelStream().anyMatch(entity::equals);
  }
}
