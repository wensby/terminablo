package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayerFactoryImpl;
import com.wensby.terminablo.userinterface.reactive.*;


public class MainMenuButton extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onClick;
  private final boolean focused;
  private final String label;
  private final BorderStyle buttonBorderStyle;
  private final BorderStyle selectedButtonBorderStyle;

  public MainMenuButton(TerminalCharacterFactory characterFactory, String label, Runnable onClick, boolean focused) {
    super();
    this.characterFactory = characterFactory;
    this.label = label;
    this.onClick = onClick;
    this.focused = focused;
    var borderStyleFactory = new BorderStyleFactory(new TerminalLayerFactoryImpl(), this.characterFactory);
    buttonBorderStyle = borderStyleFactory.createButtonBorderStyle();
    selectedButtonBorderStyle = borderStyleFactory.createSelectedButtonBorderStyle();
  }

  @Override
  public Component render() {
    if (focused) {
      return new Border(new Button(characterFactory, String.format(">%s<", label), onClick), buttonBorderStyle);
    }
    else {
      return new Border(new Button(characterFactory, label, onClick), selectedButtonBorderStyle);
    }
  }
}
