package com.wensby.terminablo.userinterface;


import com.wensby.application.userinterface.CharacterDecoration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static com.wensby.terminablo.Validate.validateThat;
import static java.lang.System.arraycopy;
import static java.util.Arrays.fill;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public final class DecoratedText {

  private final String text;
  private final List<CharacterDecoration> decorations;
  private final int[] decorationIndexByTextIndex;

  public static DecoratedText blank() {
    return new DecoratedText("", List.of(), new int[0]);
  }

  public int length() {
    return text.length();
  }

  public char charAt(int index) {
    return text.charAt(index);
  }

  public Optional<CharacterDecoration> getDecoration(int index) {
    if (decorationIndexByTextIndex[index] == -1) {
      return Optional.empty();
    }
    return Optional.of(decorations.get(decorationIndexByTextIndex[index]));
  }

  public DecoratedText append(String text) {
    return appendPlainText(text);
  }

  public DecoratedText append(String text, CharacterDecoration decoration) {
    return isNull(decoration) ? appendPlainText(text) : appendDecoratedText(text, decoration);
  }

  private DecoratedText appendPlainText(String text) {
    var oldLength = this.text.length();
    var newText = this.text + text;
    var newLength = newText.length();
    var newDecorationIndexByTextIndex = new int[newLength];
    arraycopy(this.decorationIndexByTextIndex, 0, newDecorationIndexByTextIndex, 0, oldLength);
    fill(newDecorationIndexByTextIndex, oldLength, newLength, -1);
    return new DecoratedText(newText, this.decorations, newDecorationIndexByTextIndex);
  }

  private DecoratedText appendDecoratedText(String text, CharacterDecoration decoration) {
    Objects.requireNonNull(text);
    Objects.requireNonNull(decoration);
    var oldLength = this.text.length();
    var newText = this.text + text;
    var newLength = newText.length();
    var newDecorationsList = Stream.concat(this.decorations.stream(), Stream.of(decoration)).collect(toList());
    var newDecorationIndex = this.decorations.size();
    var newDecorationIndexByTextIndex = new int[newLength];
    arraycopy(this.decorationIndexByTextIndex, 0, newDecorationIndexByTextIndex, 0, oldLength);
    fill(newDecorationIndexByTextIndex, oldLength, newLength, newDecorationIndex);
    return new DecoratedText(newText, newDecorationsList, newDecorationIndexByTextIndex);
  }

  private DecoratedText(String text, List<CharacterDecoration> decorations, int[] decorationIndexByTextIndex) {
    this.text = requireNonNull(text);
    this.decorations = requireNonNull(decorations);
    this.decorationIndexByTextIndex = requireNonNull(decorationIndexByTextIndex);
    validateThat(text.length()).isEqualTo(decorationIndexByTextIndex.length);
  }
}
