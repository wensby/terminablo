package com.wensby.terminablo.userinterface;

import com.wensby.userinterface.UserInput;

public interface UserInterface {

  UserInput pollUserInput();

  VisualCanvas getCanvas();

  void release();
}
