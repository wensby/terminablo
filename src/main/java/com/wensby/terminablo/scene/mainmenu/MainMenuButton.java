package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Button;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;


public class MainMenuButton extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onClick;
  private final boolean focused;
  private final String label;

  public MainMenuButton(TerminalCharacterFactory characterFactory, String label, Runnable onClick, boolean focused) {
    super();
    this.characterFactory = characterFactory;
    this.label = label;
    this.onClick = onClick;
    this.focused = focused;
  }

  @Override
  public Component render() {
    if (focused) {
      return new Button(characterFactory, String.format(">%s<", label), onClick);
    }
    else {
      return new Button(characterFactory, label, onClick);
    }
  }
}
