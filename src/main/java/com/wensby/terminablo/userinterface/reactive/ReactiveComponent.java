package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.TerminalLayer;

import java.util.HashMap;
import java.util.Map;

public abstract class ReactiveComponent implements Component {

  private final Map<String, Object> state;
  private boolean stateChanged;
  private Component component;

  protected ReactiveComponent() {
    this.state = new HashMap<>();
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

  @Override
  public void render(TerminalLayer layer) {
    if (stateChanged) {
      component = createComponent();
      stateChanged = false;
    }
    component.render(layer);
  }
}
