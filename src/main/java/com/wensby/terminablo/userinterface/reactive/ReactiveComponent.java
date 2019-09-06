package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalLayerPainter;

import java.util.Map;
import java.util.Objects;

public abstract class ReactiveComponent implements Component {

  private final Map<String, Object> state;
  private boolean stateChanged;
  private Component component;

  protected ReactiveComponent(Map<String, Object> state) {
    this.state = Objects.requireNonNull(state);
    stateChanged = true;
  }

  public void setState(String key, Object value) {
    state.put(key, value);
    stateChanged = true;
  }

  public <T> T getState(String key, Class<T> tClass) {
    var o = state.get(key);
    if (!tClass.isInstance(o)) {
      throw new RuntimeException(String.format("State %s not of class %s", key, tClass.getName()));
    }
    return tClass.cast(o);
  }

  public abstract Component createComponent();

  public void render(TerminalLayerPainter painter) {
    if (stateChanged) {
      component = createComponent();
      stateChanged = false;
    }
    component.render(painter);
  }
}
