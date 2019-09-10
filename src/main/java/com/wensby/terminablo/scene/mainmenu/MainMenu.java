package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Container;
import com.wensby.terminablo.userinterface.reactive.FlexibleGrid;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MainMenu extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Runnable onSinglePlayerClicked;
  private final Runnable onExitTerminabloClicked;
  private final List<String> itemLabels;
  private List<MainMenuButton> buttons = List.of();

  public MainMenu(TerminalCharacterFactory characterFactory, Runnable onSinglePlayerClicked, Runnable onExitTerminabloClicked) {
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
    return new FlexibleGrid(
        Map.of(
            "top", new Container(buttons.subList(0, 3)),
            "bottom", new Container(buttons.subList(3, 5))
        ),
        "top\n_\nbottom",
        List.of(1),
        List.of(3, 2,2)
    );
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
