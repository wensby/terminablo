package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;
import com.wensby.terminablo.userinterface.reactive.Text;

public class MainMenuButton extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;

  public MainMenuButton(TerminalCharacterFactory characterFactory, String label) {
    super();
    this.characterFactory = characterFactory;
    setState("label", label);
  }

  @Override
  public Component createComponent() {
    var label = getState("label", String.class);
    return new Text(characterFactory, label, false);
  }
}
