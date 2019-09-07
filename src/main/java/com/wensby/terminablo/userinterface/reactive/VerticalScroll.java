package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalCharacterFactory;
import com.wensby.terminablo.util.UnitInterval;

import static java.lang.Math.min;
import static java.util.Objects.requireNonNull;

public class VerticalScroll extends ReactiveComponent {

  private final TerminalCharacterFactory characterFactory;
  private final Component component;
  private final float childHeightRatio;
  private final UnitInterval scroll;

  public VerticalScroll(TerminalCharacterFactory characterFactory, Component component, float childHeightRatio, UnitInterval scroll) {
    this.characterFactory = characterFactory;
    this.component = component;
    this.childHeightRatio = childHeightRatio;
    this.scroll = requireNonNull(scroll);
  }

  @Override
  public Component render() {
    var topRatio = min(-scroll.getValue()/childHeightRatio, 0);
    var partialViewComponent = new PartialViewComponent(component, childHeightRatio, 1f, (float) topRatio, 0f);
    return new ScrollBarDecorated(partialViewComponent, characterFactory, scroll);
  }
}
