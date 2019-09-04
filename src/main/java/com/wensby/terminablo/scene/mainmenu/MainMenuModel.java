package com.wensby.terminablo.scene.mainmenu;

import java.util.List;

public class MainMenuModel {

  private final List<String> menuItems;
  private int selectedItemIndex;

  public MainMenuModel(List<String> menuItems) {
    this.menuItems = menuItems;
  }

  public List<String> getMenuItems() {
    return menuItems;
  }

  public void selectNextItem() {
    selectedItemIndex = (selectedItemIndex + 1) % menuItems.size();
  }

  public void selectPreviousItem() {
    selectedItemIndex = (selectedItemIndex + menuItems.size() - 1) % menuItems.size();
  }

  public String getSelectedMenu() {
    return menuItems.get(selectedItemIndex);
  }

  public void setSelectedMenuItemIndex(int index) {
    selectedItemIndex = index;
  }
}
