package com.wensby.terminablo.scene.mainmenu;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.userinterface.reactive.Component;
import com.wensby.terminablo.userinterface.reactive.Container;
import com.wensby.terminablo.userinterface.reactive.ReactiveComponent;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class MainMenu extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final List<String> itemLabels;
  private List<MainMenuButton> buttons = List.of();

  public MainMenu(TerminalCharacterFactory characterFactory) {
    this.characterFactory = characterFactory;
    setState("indexFocused", 0);
    itemLabels = List.of("SINGLE PLAYER", "BATTLE.NET", "OTHER MULTIPLAYER", "CREDITS/CINEMATICS", "EXIT TERMINABLO");
  }

  @Override
  public Component render() {
    buttons = itemLabels.stream()
        .map(this::createMainMenuButton)
        .collect(toList());
    return new Container(buttons);
  }

  private MainMenuButton createMainMenuButton(String s) {
    var currentIndex = getState("indexFocused", Integer.class);
    var focused = itemLabels.get(currentIndex).equals(s);
    return new MainMenuButton(characterFactory, s, () -> { }, focused);
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
