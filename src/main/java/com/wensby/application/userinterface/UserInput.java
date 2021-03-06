package com.wensby.application.userinterface;

import java.util.ArrayList;
import java.util.List;

public class UserInput {

  private final List<Key> keyStrokes;

  public UserInput(List<Key> keyStrokes) {
    this.keyStrokes = new ArrayList<>(keyStrokes);
  }

  public List<Key> getKeyStrokes() {
    return new ArrayList<>(keyStrokes);
  }
}
