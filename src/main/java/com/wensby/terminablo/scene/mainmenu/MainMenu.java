package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.*;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class MainMenu extends ReactiveComponent {

  private final ComponentFactory componentFactory;
  private final TerminalCharacterFactory characterFactory;
  private final Runnable onSinglePlayerClicked;
  private final Runnable onExitTerminabloClicked;
  private final List<String> itemLabels;
  private List<MainMenuButton> buttons = List.of();

  public MainMenu(ComponentFactory componentFactory, TerminalCharacterFactory characterFactory, Runnable onSinglePlayerClicked, Runnable onExitTerminabloClicked) {
    this.componentFactory = requireNonNull(componentFactory);
    this.characterFactory = characterFactory;
    this.onSinglePlayerClicked = onSinglePlayerClicked;
    this.onExitTerminabloClicked = onExitTerminabloClicked;
    setState("indexFocused", 0);
    itemLabels = List.of("SINGLE PLAYER", "BATTLE.NET", "OTHER MULTIPLAYER", "CREDITS/CINEMATICS", "EXIT TERMINABLO");
  }

  @Override
  public Component render() {
    buttons = itemLabels.stream()
        .map(this::createMainMenuButton)
        .collect(toList());
    return componentFactory.aFlexibleGrid()
        .withChild("top", new Container(buttons.subList(0, 3)))
        .withChild("bottom", new Container(buttons.subList(3, 5)))
        .withColumnRatios(List.of(1))
        .addRow(List.of("top"), 3)
        .addRow(List.of("_"), 2)
        .addRow(List.of("bottom"), 2)
        .build();
  }

  private MainMenuButton createMainMenuButton(String label) {
    var currentIndex = getState("indexFocused", Integer.class);
    var focused = itemLabels.get(currentIndex).equals(label);
    var onClick = label.equalsIgnoreCase("EXIT TERMINABLO") ? onExitTerminabloClicked : (Runnable)() -> {};
    onClick = label.equalsIgnoreCase("SINGLE PLAYER") ? onSinglePlayerClicked : onClick;
    return new MainMenuButton(characterFactory, label, onClick, focused);
  }

  @Override
  public void sendKeys(List<Key> keys) {
    updateFocusedButton(keys);
    sendKeysToFocusedButton(keys);
  }

  private void updateFocusedButton(List<Key> keys) {
    var indexFocused = getState("indexFocused", Integer.class);
    var buttonCount = buttons.size();
    if (keys.contains(Key.ARROW_UP)) {
      setState("indexFocused", (indexFocused + buttonCount - 1) % buttonCount);
    }
    else if (keys.contains(Key.ARROW_DOWN)) {
      setState("indexFocused", (indexFocused + 1) % buttonCount);
    }
  }

  private void sendKeysToFocusedButton(List<Key> keys) {
    if (!buttons.isEmpty()) {
      buttons.get(getState("indexFocused", Integer.class)).sendKeys(keys);
    }
  }
}
