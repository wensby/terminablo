package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ComponentFactory;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.Objects;

public class MainMenuPageSwitch extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExitTerminabloClicked;

  public MainMenuPageSwitch(ComponentFactory componentFactory, TerminalCharacterFactory characterFactory, Runnable onExitTerminabloClicked) {
    this.componentFactory = Objects.requireNonNull(componentFactory);
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.onExitTerminabloClicked = Objects.requireNonNull(onExitTerminabloClicked);
    setState("switch", "main");
  }

  @Override
  public Component render() {
    return componentFactory.aSwitch()
        .route("main", this::createFirstPage)
        .route("game", this::createGameSelectionPage)
        .build(getState("switch", String.class));
  }

  private FirstPage createFirstPage() {
    return new FirstPage(characterFactory, this::onSinglePlayerClicked, onExitTerminabloClicked);
  }

  private GameSelectionPage createGameSelectionPage() {
    return new GameSelectionPage(characterFactory, this::onBackFromCharacterSelection);
  }

  private void onBackFromCharacterSelection() {
    setState("switch", "main");
  }

  private void onSinglePlayerClicked() {
    setState("switch", "game");
  }
}
