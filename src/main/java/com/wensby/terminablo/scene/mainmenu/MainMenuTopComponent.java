package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ComponentSwitch;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.Map;
import java.util.Objects;

public class MainMenuTopComponent extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExitTerminabloClicked;

  public MainMenuTopComponent(TerminalCharacterFactory characterFactory, Runnable onExitTerminabloClicked) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.onExitTerminabloClicked = Objects.requireNonNull(onExitTerminabloClicked);
    setState("switch", "main");
  }

  @Override
  public Component render() {
    var aSwitch = getState("switch", String.class);
    return new ComponentSwitch(
        Map.of(
            "main", new MainMenuPage(characterFactory, this::onSinglePlayerClicked, onExitTerminabloClicked),
            "character", new CharacterSelectionPage(characterFactory, this::onBackFromCharacterSelection)
        ), aSwitch
    );
  }

  private void onBackFromCharacterSelection() {
    setState("switch", "main");
  }

  private void onSinglePlayerClicked() {
    setState("switch", "character");
  }
}
