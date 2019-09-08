package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.application.userinterface.TerminalLayer;
import com.wensby.terminablo.userinterface.LayerWriterImpl;

import java.util.List;
import java.util.Objects;

import static com.wensby.application.userinterface.InterfaceLocation.at;

public class Button implements Component {

  private final TerminalCharacterFactory characterFactory;
  private final String label;
  private final Runnable onClick;

  public Button(TerminalCharacterFactory characterFactory, String label, Runnable onClick) {
    this.characterFactory = Objects.requireNonNull(characterFactory);
    this.label = Objects.requireNonNull(label);
    this.onClick = Objects.requireNonNull(onClick);
  }

  @Override
  public void render(TerminalLayer layer) {
    var topLeft = at((layer.getSize().getWidth() / 2) - (label.length() / 2), layer.getSize().getHeight() / 2);
    new LayerWriterImpl(characterFactory, layer).write(label, topLeft);
  }

  @Override
  public void sendKeys(List<Key> keys) {
    if (keys.contains(Key.CARRIAGE_RETURN)) {
      onClick.run();
    }
  }

}
