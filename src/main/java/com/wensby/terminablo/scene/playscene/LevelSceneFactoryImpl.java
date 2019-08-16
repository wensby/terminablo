package com.wensby.terminablo.scene.playscene;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.LevelEntityRenderer;
import com.wensby.terminablo.TerminalLevelRenderer;
import com.wensby.terminablo.scene.SceneStack;
import com.wensby.userinterface.linux.LinuxTerminalVisualCanvas;
import com.wensby.terminablo.world.Agent;
import com.wensby.terminablo.world.AgentBuilder;
import com.wensby.terminablo.world.level.LevelEntityFactory;
import com.wensby.terminablo.world.level.LevelEntityImpl;
import com.wensby.terminablo.world.level.LevelFactory;
import com.wensby.terminablo.world.level.LevelLocation;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class LevelSceneFactoryImpl implements LevelSceneFactory {

  private final TerminalCharacterFactory characterFactory;
  private final TerminalLayerFactory layerFactory;
  private final LinuxTerminalVisualCanvas canvas;
  private final SceneStack sceneStack;

  public LevelSceneFactoryImpl(
      TerminalCharacterFactory characterFactory,
      TerminalLayerFactory layerFactory,
      LinuxTerminalVisualCanvas canvas,
      SceneStack sceneStack
  ) {
    this.characterFactory = requireNonNull(characterFactory);
    this.layerFactory = requireNonNull(layerFactory);
    this.canvas = requireNonNull(canvas);
    this.sceneStack = requireNonNull(sceneStack);
  }

  @Override
  public LevelScene createLevelScene() {
    var hero = new AgentBuilder().build();
    var entityFactory = new LevelEntityFactory();
    var levelFactory = new LevelFactory(entityFactory);
    final Path funnylevel = getResourceFilePath("funnylevel");
    var level = levelFactory.createFactoryFromResourceFile(funnylevel);
    level.putEntity(LevelLocation.of(1, 1), hero.getLevelEntity());
    for (int i = 0; i < 50; i++) {
      Agent monster = new AgentBuilder().withLevelEntity(new LevelEntityImpl("\uD83D\uDC7E")).build();
      level.putEntity(LevelLocation.of(new Random().nextInt(100), new Random().nextInt(100)), monster.getLevelEntity());
    }
    var model = new LevelSceneModel(hero, level);
    var partialBlockCharacterFactory = new PartialBlockCharacterFactoryImpl();
    OrbContentTerminalRenderer orbContentTerminalRenderer = new OrbContentTerminalRendererImpl(
        partialBlockCharacterFactory, layerFactory, characterFactory);
    var orbTerminalRepresentationFactory = new TerminalOrbRenderer(layerFactory,
        characterFactory,
        orbContentTerminalRenderer);
    var levelSceneInterfaceRenderer = new LevelSceneInterfaceRenderer(
        orbTerminalRepresentationFactory, layerFactory, characterFactory);
    var levelEntityRenderer = new LevelEntityRenderer(characterFactory);
    var levelRenderer = new TerminalLevelRenderer(layerFactory, levelEntityRenderer);
    var levelSceneView = new LevelSceneTerminalView(layerFactory,
        levelSceneInterfaceRenderer,
        levelRenderer, model);
    var levelSceneController = new LevelSceneController(sceneStack, model);
    return new LevelScene(levelSceneController, levelSceneView);
  }

  private Path getResourceFilePath(final String resourceFilepath) {
    try {
      return Paths.get(getClass().getClassLoader().getResource(resourceFilepath).toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
