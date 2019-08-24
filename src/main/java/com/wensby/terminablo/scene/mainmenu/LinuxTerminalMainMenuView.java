package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;

import com.wensby.userinterface.LayerWriterImpl;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerFactory;

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
      writer.write(menuItem.toString(), InterfaceLocation.of(0, i));
    }

    return layer;
  }
}
