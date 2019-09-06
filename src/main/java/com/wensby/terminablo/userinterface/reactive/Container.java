package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.List;

public class Container implements Component {

  private final List<Component> children;

  public Container(List<Component> children) {
    this.children = children;
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    new ContainerPainter(painter, children).paint();
  }
}
