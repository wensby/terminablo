package com.wensby.terminablo.userinterface.reactive;

public class Scroll extends ReactiveComponent {

  private final Component childComponent;
  private final float childHeightRatio;
  private final float topRatio;

  public Scroll(Component childComponent, float childHeightRatio, float topRatio) {
    this.childComponent = childComponent;
    this.childHeightRatio = childHeightRatio;
    this.topRatio = topRatio;
  }

  @Override
  public Component render() {
    return new PartialViewComponent(childComponent, childHeightRatio, 1f, 0f, topRatio);
  }
}
