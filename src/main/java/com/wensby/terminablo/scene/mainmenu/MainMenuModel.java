package com.wensby.terminablo.scene.mainmenu;

import java.util.List;

public interface MainMenuModel {

  List<String> getMenuItems();

  int getSelectedMenuItemIndex();

  void setSelectedMenuItemIndex(int index);
}
