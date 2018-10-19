package com.wensby.terminablo.scene.mainmenu;

import static com.wensby.userinterface.InterfacePosition.atOrigin;
import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;
import com.wensby.userinterface.InterfacePosition;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayerFactory;

public class LinuxTerminalMainMenuView implements MainMenuView {

  private final MainMenuModel model;
  private final LinuxTerminalVisualCanvas canvas;
  private final TerminalLayerFactory layerFactory;
  private final TerminalCharacterFactory characterFactory;

  public LinuxTerminalMainMenuView(
      MainMenuModel model,
      LinuxTerminalVisualCanvas canvas,
      TerminalLayerFactory layerFactory,
      TerminalCharacterFactory characterFactory) {
    this.model = requireNonNull(model);
    this.canvas = requireNonNull(canvas);
    this.layerFactory = requireNonNull(layerFactory);
    this.characterFactory = requireNonNull(characterFactory);
  }

  @Override
  public void render() {
    var frame = canvas.createFrame();
    var layer = layerFactory.createBlankLayer(frame.getSize());

    var menuItems = model.getMenuItems();
    for (int i = 0; i < menuItems.size(); i++) {
      var menuItemTxt = menuItems.get(i);
      if (model.getSelectedMenuItemIndex() == i) {
        menuItemTxt = "> " + menuItemTxt;
      }
      var column = 0;
      for (var c : menuItemTxt.toCharArray()) {
        layer.put(InterfacePosition.of(column, i), characterFactory.createCharacter(c));
        column++;
      }
    }


    frame.put(atOrigin(), layer);
    canvas.renderFrame(frame);
  }
}
