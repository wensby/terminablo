package com.wensby.userinterface;

import java.util.Objects;

public class LinuxPrintRenderCommand implements TerminalRenderCommand {

  private final CharSequence characters;

  public LinuxPrintRenderCommand(CharSequence characters) {
    this.characters = characters;
  }

  @Override
  public String toString() {
    return "LinuxPrintRenderCommand{" + "character=" + characters + '}';
  }

  @Override
  public String toRenderString() {
    return characters.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LinuxPrintRenderCommand that = (LinuxPrintRenderCommand) o;
    return Objects.equals(characters, that.characters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(characters);
  }
}
