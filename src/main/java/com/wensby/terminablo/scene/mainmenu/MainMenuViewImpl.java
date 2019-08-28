package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.LayerWriterImpl;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;

public class MainMenuViewImpl implements MainMenuView {

  private final MainMenuModel model;
  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  public MainMenuViewImpl(
      MainMenuModel model,
      TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory
  ) {
    this.model = requireNonNull(model);
    this.layerFactory = requireNonNull(layerFactory);
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public TerminalLayer render(InterfaceSize size) {
    var layer = layerFactory.createBlankLayer(size);

    var menuItems = model.getMenuItems();
    var expectedWidth = menuItems.stream().mapToInt(String::length).max().orElse(0);
    var expectedHeight = menuItems.size();
    var writer = new LayerWriterImpl(characterFactory, layer);
    var at = InterfaceLocation.at((size.getWidth() / 2) - (expectedWidth / 2), (size.getHeight() / 2) - (expectedHeight / 2));
    for (int i = 0; i < menuItems.size(); i++) {
      var menuItem = new StringBuilder();
      if (model.getSelectedMenuItemIndex() == i) {
        menuItem.append("> ");
      }
      menuItem.append(menuItems.get(i));
      writer.write(menuItem.toString(), at.plus(InterfaceLocation.at(0, i)));
    }

    return layer;
  }
}
