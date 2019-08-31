package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.userinterface.LayerWriterImpl;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;

public class MainMenuViewImpl implements MainMenuView {

  private final MainMenuModel model;
  private final TerminalCharacterFactory characterFactory;

  public MainMenuViewImpl(MainMenuModel model, TerminalCharacterFactory characterFactory) {
    this.model = requireNonNull(model);
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public void render(TerminalLayerPainter painter) {

    var menuItems = model.getMenuItems();
    var expectedWidth = menuItems.stream().mapToInt(String::length).max().orElse(0);
    var expectedHeight = menuItems.size();
    var writer = new LayerWriterImpl(characterFactory, painter);
    var at = InterfaceLocation.at((painter.getAvailableSize().getWidth() / 2) - (expectedWidth / 2), (painter.getAvailableSize().getHeight() / 2) - (expectedHeight / 2));
    for (int i = 0; i < menuItems.size(); i++) {
      var menuItem = new StringBuilder();
      if (model.getSelectedMenuItemIndex() == i) {
        menuItem.append("> ");
      }
      menuItem.append(menuItems.get(i));
      writer.write(menuItem.toString(), at.plus(InterfaceLocation.at(0, i)));
    }

  }
}
