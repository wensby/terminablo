package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.LevelEntityRenderer;
import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.player.PlayerCharacter;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentBuilder;
import com.wensby.terminablo.world.AgentStats;
import com.wensby.terminablo.world.level.LevelEntityFactory;
import com.wensby.terminablo.world.level.LevelEntityImpl;
import com.wensby.terminablo.world.level.LevelFactory;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.application.userinterface.ComplexTerminalCharacterImpl;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerFactory;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Random;

public class PlaySceneFactoryImpl implements PlaySceneFactory {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayerFactory layerFactory;
  private final SceneStack sceneStack;

  public PlaySceneFactoryImpl(
      TerminalCharacterFactory characterFactory,
      TerminalLayerFactory layerFactory,
      SceneStack sceneStack
  ) {
    this.characterFactory = requireNonNull(characterFactory);
    this.layerFactory = requireNonNull(layerFactory);
    this.sceneStack = requireNonNull(sceneStack);
  }

  @Override
  public Scene createPlayScene() {
    var playerCharacter = new PlayerCharacter("hero", new AgentStats(), new LevelEntityImpl(characterFactory.createCharacter('o')));
    var entityFactory = new LevelEntityFactory();
    var levelFactory = new LevelFactory(entityFactory);
    final Path funnylevel = getResourceFilePath("funnylevel");
    var level = levelFactory.createLevelFromResourceFile(funnylevel);
    level.putEntity(LevelLocation.of(1, 1), playerCharacter.getLevelEntity());
    var monsterCharacter = new ComplexTerminalCharacterImpl("\uD83D\uDC7E");
    var monsters = new LinkedList<Agent>();
    for (int i = 0; i < 50; i++) {
      monsters.add(new AgentBuilder()
          .withName("monster")
          .withLevelEntity(new LevelEntityImpl(monsterCharacter))
          .build());
    }
    monsters.forEach(monster -> level.putEntity(LevelLocation.of(new Random().nextInt(100), new Random().nextInt(100)), monster.getLevelEntity()));
    var model = new PlaySceneModel(playerCharacter, level, monsters);
    var partialBlockCharacterFactory = new PartialBlockCharacterFactoryImpl();
    var orbContentTerminalRenderer = new OrbContentTerminalRendererImpl(
        partialBlockCharacterFactory, layerFactory, characterFactory);
    var orbTerminalRepresentationFactory = new TerminalOrbRenderer(layerFactory,
        characterFactory,
        orbContentTerminalRenderer);
    var levelSceneInterfaceRenderer = new PlaySceneInterfaceRenderer(
        orbTerminalRepresentationFactory, layerFactory, characterFactory);
    var levelEntityRenderer = new LevelEntityRenderer();
    var levelRenderer = new TerminalLevelRenderer(layerFactory, levelEntityRenderer);
    var levelSceneView = new PlaySceneView(levelSceneInterfaceRenderer, levelRenderer, model);
    var agentController = new AgentController(level);
    var playerCombatController = new PlayerCombatController(playerCharacter, model);
    var playerController = new PlayerMovementController(playerCharacter, level);
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
