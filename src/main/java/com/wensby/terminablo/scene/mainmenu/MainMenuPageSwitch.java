package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.game.GameRepository;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ComponentFactory;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import static java.util.Objects.requireNonNull;

public class MainMenuPageSwitch extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Runnable onExitTerminabloClicked;
  private final GameRepository gameRepository;

  public MainMenuPageSwitch(ComponentFactory componentFactory, TerminalCharacterFactory characterFactory, Runnable onExitTerminabloClicked, GameRepository gameRepository) {
    this.componentFactory = requireNonNull(componentFactory);
    this.characterFactory = requireNonNull(characterFactory);
    this.onExitTerminabloClicked = requireNonNull(onExitTerminabloClicked);
    this.gameRepository = requireNonNull(gameRepository);
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
    return new FirstPage(componentFactory, characterFactory, this::onSinglePlayerClicked, onExitTerminabloClicked);
  }

  private GameSelectionPage createGameSelectionPage() {
    var games = gameRepository.getGames();
    return new GameSelectionPage(componentFactory, characterFactory, this::onBackFromCharacterSelection, games);
  }

  private void onBackFromCharacterSelection() {
    setState("switch", "main");
  }

  private void onSinglePlayerClicked() {
    setState("switch", "game");
  }
}
