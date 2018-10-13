package com.wensby.terminablo;

import com.wensby.Ticker;
import com.wensby.terminablo.userinterface.UserInterface;

public class GameLooperBuilder {

  private Ticker ticker;
  private UserInterface userInterface;

  public GameLooperBuilder withTickable(Ticker ticker) {
    this.ticker = ticker;
    return this;
  }

  public GameLooperBuilder withUserInterface(UserInterface userInterface) {
    this.userInterface = userInterface;
    return this;
  }

  GameLooper build() {
    return new StandardGameLooper(userInterface, ticker);
  }
}
