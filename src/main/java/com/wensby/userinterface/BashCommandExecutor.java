package com.wensby.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BashCommandExecutor implements RuntimeProcessExecutor {

  @Override
  public String executeCommand(String command) {
    try {
      String[] commands = {"/bin/bash", "-c", command};
      Process process = Runtime.getRuntime().exec(commands);
      BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
      BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String s = null;
      StringBuilder stringBuilder = new StringBuilder();
      while ((s = stdInput.readLine()) != null) {
        stringBuilder.append(s);
      }
      while ((s = stdError.readLine()) != null) {
        stringBuilder.append(s);
      }
      process.waitFor();
      return stringBuilder.toString();
    } catch (InterruptedException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
