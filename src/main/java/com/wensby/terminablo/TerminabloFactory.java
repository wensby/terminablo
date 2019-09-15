package com.wensby.terminablo;

import com.wensby.application.TerminalApplication;
import com.wensby.application.TerminalApplicationContext;
import com.wensby.terminablo.game.GameRepository;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuSceneFactory;
import com.wensby.terminablo.scene.playscene.PlaySceneFactoryImpl;
import com.wensby.terminablo.scene.testscene.StressTestSceneView;

import java.nio.file.Paths;

public class TerminabloFactory {

  public TerminalApplication createTerminablo(TerminalApplicationContext context) {
    var characterFactory = context.getCharacterFactory();
    var sceneStack = new SceneStackImpl();
    var gameRepository = new GameRepository(Paths.get("game"), characterFactory);
    var mainMenuSceneFactory = new MainMenuSceneFactory(sceneStack, characterFactory, gameRepository);
    var scene = mainMenuSceneFactory.createMainMenuScene();
    var stressTestScene = new Scene((elapsedTime, input) -> {}, new StressTestSceneView(characterFactory));
    var playSceneFactory = new PlaySceneFactoryImpl(characterFactory, sceneStack);
    var playScene = playSceneFactory.createPlayScene();
    sceneStack.push(scene);
    var terminabloUpdater = new TerminabloUpdater(sceneStack);
    var terminabloRenderer = new TerminabloApplicationRenderer(sceneStack);
    return context.getApplicationBuilder()
        .withUpdater(terminabloUpdater)
        .withRenderer(terminabloRenderer)
        .withTargetTicksPerSecond(30)
        .build();
  }
}
