package com.wensby.terminablo.scene.mainmenu;

import java.util.List;

public class MainMenuModelImpl implements MainMenuModel {

  private final List<String> menuItems;
  private int selectedItemIndex;

  public MainMenuModelImpl() {
    menuItems = List.of("SINGLE PLAYER", "Level Scene", "BATTLE.NET", "OTHER MULTIPLAYER", "CREDITS", "CINEMATICS", "EXIT TERMINABLO");
  }

  @Override
  public List<String> getMenuItems() {
    return menuItems;
  }

  @Override
  public int getSelectedMenuItemIndex() {
    return selectedItemIndex;
  }

  @Override
  public void setSelectedMenuItemIndex(int index) {
    selectedItemIndex = index;
  }
}
