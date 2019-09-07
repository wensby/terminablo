package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ComponentSwitch;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.Map;
import java.util.Objects;

public class MainMenuPageSwitch extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExitTerminabloClicked;

  public MainMenuPageSwitch(TerminalCharacterFactory characterFactory, Runnable onExitTerminabloClicked) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.onExitTerminabloClicked = Objects.requireNonNull(onExitTerminabloClicked);
    setState("switch", "main");
  }

  @Override
  public Component render() {
    return new ComponentSwitch(
        Map.of(
            "main", this::createFirstPage,
            "character", this::createCharacterSelectionPage
        ), getState("switch", String.class)
    );
  }

  private FirstPage createFirstPage() {
    return new FirstPage(characterFactory, this::onSinglePlayerClicked, onExitTerminabloClicked);
  }

  private CharacterSelectionPage createCharacterSelectionPage() {
    return new CharacterSelectionPage(characterFactory, this::onBackFromCharacterSelection);
  }

  private void onBackFromCharacterSelection() {
    setState("switch", "main");
  }

  private void onSinglePlayerClicked() {
    setState("switch", "character");
  }
}
