package com.wensby.terminablo.userinterface.terminal;

import static java.util.Objects.requireNonNull;

import com.wensby.terminablo.ListUtil;
import com.wensby.terminablo.userinterface.Key;
import com.wensby.terminablo.userinterface.Keyboard;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LinuxTerminalKeyboard implements Keyboard, AutoCloseable {

  private final InputStream inputStream;

  LinuxTerminalKeyboard(InputStream inputStream) {
    this.inputStream = requireNonNull(inputStream);
  }

  @Override
  public List<Key> getKeyStrokes() {
    List<Integer> bytes = new ArrayList<>();
    while (isInputBytesAvailable()) {
      bytes.add(readNextByte());
    }
    return parseKeys(bytes);
  }

  private List<Key> parseKeys(List<Integer> bytes) {
    List<Key> keyStrokes = new ArrayList<>();
    while (!bytes.isEmpty()) {
      Key key = pollKey(bytes);
      keyStrokes.add(key);
    }
    return keyStrokes;
  }

  private Key pollKey(List<Integer> bytes) {
    Key key = Key.ofBytes(bytes);
    int keyByteCount = key.getBytes().size();
    ListUtil.removeFirstElements(bytes, keyByteCount);
    return key;
  }

  private boolean isInputBytesAvailable() {
    return getAvailableBytes() > 0;
  }

  private int getAvailableBytes() {
    try {
      return inputStream.available();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private int readNextByte() {
    try {
      return inputStream.read();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() {
  }
}
