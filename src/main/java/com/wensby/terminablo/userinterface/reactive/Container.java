package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalLayer;

import java.util.List;

public class Container implements Component {

  private final List<? extends Component> children;

  public Container(List<? extends Component> children) {
    this.children = children;
  }

  @Override
  public void render(TerminalLayer layer) {
    new ContainerPainter(layer, children).paint();
  }
}
