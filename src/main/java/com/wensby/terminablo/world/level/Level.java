package com.wensby.terminablo.world.level;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface Level {

  Map<LevelLocation, Set<LevelEntity>> entities();

  Set<LevelEntity> entities(LevelLocation location);

  Optional<LevelLocation> locationOf(LevelEntity levelEntity);

  void putEntity(LevelLocation location, LevelEntity entity);

  Optional<LevelLocation> removeEntity(LevelEntity entity);
}
