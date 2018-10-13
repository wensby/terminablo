package com.wensby.terminablo.world.level;

import java.util.List;
import java.util.Optional;

public interface Level {

  List<LevelEntity> entities(LevelLocation location);

  Optional<LevelLocation> locationOf(LevelEntity levelEntity);

  void putEntity(LevelLocation location, LevelEntity entity);

  Optional<LevelLocation> removeEntity(LevelEntity entity);
}
