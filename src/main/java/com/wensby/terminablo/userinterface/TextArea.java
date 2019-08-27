package com.wensby.terminablo.userinterface;

import com.wensby.terminablo.userinterface.component.Component;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import com.wensby.application.userinterface.InterfaceSize;

public class TextArea implements Component {

  private InterfaceSize size;
  private InterfaceLocation location;
  private String text = "";

  public TextArea(InterfaceSize size, InterfaceLocation location) {
    this.size = size;
    this.location = location;
  }

  @Override
  public void render(char[][] canvas) {
    InterfaceLocation location = getLocation();
    int row = location.getRow();
    int column = location.getColumn();
    System.arraycopy(text.toCharArray(), 0, canvas[row], column, text.length());
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public InterfaceLocation getLocation() {
    return location;
  }

  @Override
  public InterfaceSize getSize() {
    return size;
  }
}
