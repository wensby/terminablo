package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Container;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;

public class MainMenu extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;

  public MainMenu(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
  }

  @Override
  public Component createComponent() {
    return new Container(List.of(new MainMenuButton(characterFactory, "myLabel")));
  }
}
