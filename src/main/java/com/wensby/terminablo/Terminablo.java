package com.wensby.terminablo;

import com.wensby.application.TerminalApplicationContext;

public class Terminablo {

  public static void main(String[] args) {
    var context = new TerminalApplicationContext();
    var terminablo = new TerminabloFactory().createTerminablo(context);
    context.run(terminablo);
  }

}
