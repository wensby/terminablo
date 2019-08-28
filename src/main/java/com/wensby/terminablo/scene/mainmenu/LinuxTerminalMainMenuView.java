package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.LayerWriterImpl;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.application.userinterface.TerminalLayerFactory;

public class LinuxTerminalMainMenuView implements MainMenuView {

  private final MainMenuModel model;
  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  public LinuxTerminalMainMenuView(
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
    var writer = new LayerWriterImpl(characterFactory, layer);
    for (int i = 0; i < menuItems.size(); i++) {
      var menuItem = new StringBuilder();
      if (model.getSelectedMenuItemIndex() == i) {
        menuItem.append("> ");
      }
      menuItem.append(menuItems.get(i));
      writer.write(menuItem.toString(), InterfaceLocation.at(0, i));
    }

    return layer;
  }
}
