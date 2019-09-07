package com.wensby.terminablo.userinterface.reactive;

import com.wensby.application.userinterface.Key;
import com.wensby.application.userinterface.TerminalLayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

  public abstract Component render();

  @Override
  public final void render(TerminalLayer layer) {
    if (stateChanged) {
      component = render();
      stateChanged = false;
    }
    component.render(layer);
  }

  @Override
  public void sendKeys(List<Key> keys) {
    Optional.ofNullable(component).ifPresent(c -> c.sendKeys(keys));
  }
}
