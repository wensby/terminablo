package com.wensby.terminablo.game;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.world.AgentStats;
import com.wensby.terminablo.world.level.LevelEntityImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;


public class GameRepository {

  private final Path gamesDirectoryPath;
  private final TerminalCharacterFactory characterFactory;

  public GameRepository(Path gamesDirectoryPath, TerminalCharacterFactory characterFactory) {
    this.gamesDirectoryPath = requireNonNull(gamesDirectoryPath);
    this.characterFactory = requireNonNull(characterFactory);
  }

  public List<Game> getGames() {
    var gamesDirectory = gamesDirectoryPath.toFile();
    if (!gamesDirectory.exists()) {
      if (!gamesDirectory.mkdir()) {
        throw new RuntimeException("Failed to create games directory.");
      }
    }
    var gameDirectories = requireNonNull(gamesDirectory.listFiles());
    return Stream.of(gameDirectories).map(this::readGame).collect(toList());
  }

  private Game readGame(File file) {
    var entity = new LevelEntityImpl(characterFactory.createCharacter("\uD83D\uDE42"));
    var character = new PlayerCharacter("name", new AgentStats());
    return new Game(character);
  }
}
