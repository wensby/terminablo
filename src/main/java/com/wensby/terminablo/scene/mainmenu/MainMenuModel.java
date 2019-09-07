package com.wensby.terminablo.scene.mainmenu;

import com.wensby.terminablo.userinterface.UserInterface;

import java.util.List;
import java.util.Objects;

public class MainMenuModel {

  private final UserInterface userInterface;

  public MainMenuModel(UserInterface userInterface) {
    this.userInterface = Objects.requireNonNull(userInterface);
  }

  public UserInterface getUserInterface() {
    return userInterface;
  }
}
