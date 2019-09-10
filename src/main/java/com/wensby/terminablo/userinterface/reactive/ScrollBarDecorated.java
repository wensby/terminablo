package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.util.UnitInterval;

import java.awt.*;
import java.util.List;

import static com.wensby.application.userinterface.InterfaceLocation.at;
import static com.wensby.application.userinterface.InterfaceLocation.atOrigin;
import static com.wensby.application.userinterface.InterfaceSize.of;
import static java.util.Objects.requireNonNull;

public class ScrollBarDecorated implements Component {

  private final TerminalCharacterFactory characterFactory;
  private final Component component;
  private final UnitInterval scroll;

  public ScrollBarDecorated(Component component, TerminalCharacterFactory characterFactory, UnitInterval scroll) {
    this.characterFactory = requireNonNull(characterFactory);
    this.component = requireNonNull(component);
    this.scroll = scroll;
  }

  @Override
  public void render(TerminalLayer layer) {
    component.render(layer.getSubsection(atOrigin(), layer.size().minus(of(1, 0))));
    layer.put(characterFactory.createCharacter('▲', new CharacterDecoration(new Color(36, 35, 11), new Color(122, 120, 69), true)), at(layer.size().getWidth()-1, 0));
    layer.put(characterFactory.createCharacter('▼', new CharacterDecoration(new Color(36, 35, 11), new Color(122, 120, 69), true)), at(layer.size().getWidth()-1, layer.size().getHeight()-1));
    for (int r = 1; r < layer.size().getHeight() -1; r++) {
      layer.put(characterFactory.createCharacter('▏', new CharacterDecoration(new Color(36, 35, 11), new Color(219, 218, 180), true)), at(layer.size().getWidth()-1, r));
    }
    var location = (int) ((layer.size().getHeight() - 3) * scroll.getValue()) + 1;
    layer.put(characterFactory.createCharacter('▏', new CharacterDecoration(new Color(122, 120, 69), new Color(219, 218, 180), true)), at(layer.size().getWidth()-1, location));
  }

  @Override
  public void sendKeys(List<Key> keys) {

  }
}
