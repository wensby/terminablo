package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.wensby.terminablo.LevelEntityRenderer;
import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentBuilder;
import com.wensby.terminablo.world.AgentStats;
import com.wensby.terminablo.world.level.*;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class PlaySceneFactoryImpl implements PlaySceneFactory {

  private final TerminalCharacterFactory characterFactory;
  private final SceneStack sceneStack;

  public PlaySceneFactoryImpl(TerminalCharacterFactory characterFactory, SceneStack sceneStack) {
    this.characterFactory = requireNonNull(characterFactory);
    this.sceneStack = requireNonNull(sceneStack);
  }

  @Override
  public Scene createPlayScene() {
    var playerCharacter = new PlayerCharacter("hero", new AgentStats());
    var entityFactory = new LevelEntityFactory();
    var levelFactory = new LevelFactory(entityFactory);
    final Path funnylevel = getResourceFilePath("funnylevel");
    var level = levelFactory.createLevelFromResourceFile(funnylevel);
    var monsters = new LinkedList<Agent>();
    for (int i = 0; i < 50; i++) {
      monsters.add(new AgentBuilder()
          .withName("monster")
          .build());
    }
    var monsterPresences = monsters.stream().map(AgentPresence::new).collect(toList());
    monsterPresences.forEach(agentPresence -> level.putEntity(LevelLocation.of(new Random().nextInt(100), new Random().nextInt(100)), agentPresence));
    var heroPresence = new AgentPresence(playerCharacter);
    var model = new PlaySceneModel(playerCharacter, level, monsters, monsterPresences, heroPresence);
    var partialBlockCharacterFactory = new PartialBlockCharacterFactoryImpl();
    var orbContentTerminalRenderer = new OrbContentTerminalRendererImpl(
        partialBlockCharacterFactory, characterFactory);
    var orbTerminalRepresentationFactory = new TerminalOrbRenderer(
        characterFactory,
        orbContentTerminalRenderer);
    var levelSceneInterfaceRenderer = new PlaySceneInterfaceRenderer(
        orbTerminalRepresentationFactory, characterFactory);
    var levelEntityRenderer = new LevelEntityRenderer();
    var levelRenderer = new TerminalLevelRenderer(levelEntityRenderer);
    var levelSceneView = new PlaySceneView(levelSceneInterfaceRenderer, levelRenderer, model);
    var agentController = new AgentController(level, new HashSet<>(monsterPresences));
    var playerCombatController = new PlayerCombatController(playerCharacter, model);
    var playerController = new PlayerMovementController(playerCharacter, level, heroPresence, LevelLocation.of(1, 1));
    var levelSceneController = new PlaySceneController(sceneStack, agentController, model, playerController, playerCombatController);
    return new Scene(levelSceneController, levelSceneView);
  }

  private Path getResourceFilePath(final String resourceFilepath) {
    try {
      return Paths.get(getClass().getClassLoader().getResource(resourceFilepath).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
