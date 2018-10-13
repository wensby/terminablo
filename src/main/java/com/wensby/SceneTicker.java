package com.wensby;

import com.wensby.terminablo.Scene;
import com.wensby.terminablo.userinterface.Key;
import com.wensby.userinterface.UserInput;
import java.time.Duration;

public class SceneTicker implements Ticker {

  private Scene scene;

  public SceneTicker(Scene scene) {
    this.scene = scene;
  }

  @Override
  public TickResult tick(Duration elapsedTime, UserInput userInput) {
    if (userInput.getKeyStrokes().contains(Key.END_OF_FILE)) {
      return TickResult.FINAL_TICK;
    } else {
      scene = scene.update(elapsedTime, userInput);
      if (scene == null) {
        return TickResult.FINAL_TICK;
      }
      return TickResult.CONTINUE;
    }
  }
}
