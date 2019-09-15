package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.ComponentFactory;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class FirstPage extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Runnable onSinglePlayerClicked;
  private final Runnable onExitTerminabloClicked;

  public FirstPage(ComponentFactory componentFactory, TerminalCharacterFactory characterFactory, Runnable onSinglePlayerClicked, Runnable onExitTerminabloClicked) {
    this.componentFactory = requireNonNull(componentFactory);
    this.characterFactory = characterFactory;
    this.onSinglePlayerClicked = onSinglePlayerClicked;
    this.onExitTerminabloClicked = onExitTerminabloClicked;
  }

  @Override
  public Component render() {
    return componentFactory.aFlexibleGrid()
        .withChild("menu", new MainMenu(componentFactory, characterFactory, onSinglePlayerClicked, onExitTerminabloClicked))
        .withColumnRatios(List.of(1, 1, 1))
        .addRow(List.of("_", "_", "_"), 1)
        .addRow(List.of("_", "menu", "_"), 1)
        .build();
  }
}
