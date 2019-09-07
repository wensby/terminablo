package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.scene.View;


public class MainMenuView implements View {

  private final MainMenuModel model;

  public MainMenuView(MainMenuModel model) {
    this.model = requireNonNull(model);
  }

  @Override
  public void render(TerminalLayer layer) {
    model.getUserInterface().render(layer);
  }
}
