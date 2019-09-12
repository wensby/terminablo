package com.wensby.terminablo;

import com.wensby.application.TerminalApplication;
import com.wensby.application.TerminalApplicationContext;
import com.wensby.terminablo.game.GameRepository;
import com.wensby.terminablo.scene.Scene;
import com.wensby.terminablo.scene.SceneStackImpl;
import com.wensby.terminablo.scene.mainmenu.MainMenuSceneFactory;
import com.wensby.terminablo.scene.testscene.StressTestSceneView;

public class TerminabloFactory {

  public TerminalApplication createTerminablo(TerminalApplicationContext context) {
    var characterFactory = context.getCharacterFactory();
    var sceneStack = new SceneStackImpl();
    var gameRepository = new GameRepository();
    var mainMenuSceneFactory = new MainMenuSceneFactory(sceneStack, characterFactory, gameRepository);
    var scene = mainMenuSceneFactory.createMainMenuScene();
    var stressTestScene = new Scene((elapsedTime, input) -> {}, new StressTestSceneView(characterFactory));
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
