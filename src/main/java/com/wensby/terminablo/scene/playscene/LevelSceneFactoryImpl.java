package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.LevelEntityRenderer;
import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentBuilder;
import com.wensby.terminablo.world.level.LevelEntityFactory;
import com.wensby.terminablo.world.level.LevelEntityImpl;
import com.wensby.terminablo.world.level.LevelFactory;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.ComplexTerminalCharacterImpl;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Random;

public class LevelSceneFactoryImpl implements LevelSceneFactory {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayerFactory layerFactory;
  private final SceneStack sceneStack;

  public LevelSceneFactoryImpl(
      TerminalCharacterFactory characterFactory,
      TerminalLayerFactory layerFactory,
      SceneStack sceneStack
  ) {
    this.characterFactory = requireNonNull(characterFactory);
    this.layerFactory = requireNonNull(layerFactory);
    this.sceneStack = requireNonNull(sceneStack);
  }

  @Override
  public Scene createLevelScene() {
    var hero = new AgentBuilder().build();
    var entityFactory = new LevelEntityFactory();
    var levelFactory = new LevelFactory(entityFactory);
    final Path funnylevel = getResourceFilePath("funnylevel");
    var level = levelFactory.createLevelFromResourceFile(funnylevel);
    level.putEntity(LevelLocation.of(1, 1), hero.getLevelEntity());
    var monsterCharacter = new ComplexTerminalCharacterImpl("\uD83D\uDC7E");
    var monsters = new LinkedList<Agent>();
    for (int i = 0; i < 50; i++) {
      monsters.add(new AgentBuilder()
          .withName("monster")
          .withLevelEntity(new LevelEntityImpl(monsterCharacter))
          .build());
    }
    monsters.forEach(monster -> level.putEntity(LevelLocation.of(new Random().nextInt(100), new Random().nextInt(100)), monster.getLevelEntity()));
    var model = new LevelSceneModel(hero, level, monsters);
    var partialBlockCharacterFactory = new PartialBlockCharacterFactoryImpl();
    OrbContentTerminalRenderer orbContentTerminalRenderer = new OrbContentTerminalRendererImpl(
        partialBlockCharacterFactory, layerFactory, characterFactory);
    var orbTerminalRepresentationFactory = new TerminalOrbRenderer(layerFactory,
        characterFactory,
        orbContentTerminalRenderer);
    var levelSceneInterfaceRenderer = new LevelSceneInterfaceRenderer(
        orbTerminalRepresentationFactory, layerFactory, characterFactory);
    var levelEntityRenderer = new LevelEntityRenderer();
    var levelRenderer = new TerminalLevelRenderer(layerFactory, levelEntityRenderer);
    var levelSceneView = new TerminalView(layerFactory,
        levelSceneInterfaceRenderer,
        levelRenderer, model);
    AgentController agentController = new AgentController(level);
    var playerController = new PlayerMovementController(hero, level);
    var levelSceneController = new LevelSceneController(sceneStack, agentController, model, playerController);
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
