package com.wensby.userinterface;

import java.util.Objects;

public class ComplexTerminalCharacterImpl implements ComplexTerminalCharacter {

  private final CharSequence charSequence;
  private final CharacterDecoration decoration;

  public ComplexTerminalCharacterImpl(CharSequence charSequence, CharacterDecoration decoration) {
    this.charSequence = Objects.requireNonNull(charSequence);
    this.decoration = Objects.requireNonNull(decoration);
  }

  public ComplexTerminalCharacterImpl(CharSequence charSequence) {
    this(charSequence, new CharacterDecoration(null, null));
  }

  @Override
  public String toString() {
    return "ComplexTerminalCharacterImpl{" +
        "charSequence=" + charSequence +
        ", decoration=" + decoration +
        '}';
  }

  @Override
  public CharSequence getCharSequence() {
    return charSequence;
  }

  @Override
  public CharacterDecoration getDecoration() {
    return decoration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComplexTerminalCharacterImpl that = (ComplexTerminalCharacterImpl) o;
    return Objects.equals(charSequence, that.charSequence) &&
        Objects.equals(decoration, that.decoration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(charSequence, decoration);
  }
}
