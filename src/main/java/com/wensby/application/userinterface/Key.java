package com.wensby.application.userinterface;

import static java.util.Collections.unmodifiableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public enum Key {

  END_OF_FILE(List.of(3), null),
  TAB(List.of(9), null),
  CARRIAGE_RETURN(List.of(13), null),
  ESCAPE(List.of(27), null),
  ARROW_UP(List.of(27, 91, 65), null),
  ARROW_DOWN(List.of(27, 91, 66), null),
  ARROW_RIGHT(List.of(27, 91, 67), null),
  ARROW_LEFT(List.of(27, 91, 68), null),
  SPACE(List.of(32), ' '),
  SINGLE_QUOTE(List.of(39), '\''),
  COMMA(List.of(44), ','),
  DASH(List.of(45), '-'),
  PERIOD(List.of(46), '.'),
  SLASH_FORWARD(List.of(47), '/'),
  ZERO(List.of(48), '0'),
  ONE(List.of(49), '1'),
  TWO(List.of(50), '2'),
  THREE(List.of(51), '3'),
  FOUR(List.of(52), '4'),
  FIVE(List.of(53), '5'),
  SIX(List.of(54), '6'),
  SEVEN(List.of(55), '7'),
  EIGHT(List.of(56), '8'),
  NINE(List.of(57), '9'),
  SEMI_COLON(List.of(59), ';'),
  EQUALS(List.of(61), '='),
  BRACKET_SQUARE_LEFT(List.of(91), '['),
  SLASH_BACKWARD(List.of(92), '\\'),
  BRACKET_SQUARE_RIGHT(List.of(93), ']'),
  SMALL_BACKWARD_FNUTT(List.of(96), '`'), // TODO fix name
  A_SMALL(List.of(97), 'a'),
  B_SMALL(List.of(98), 'b'),
  C_SMALL(List.of(99), 'c'),
  D_SMALL(List.of(100), 'd'),
  E_SMALL(List.of(101), 'e'),
  F_SMALL(List.of(102), 'f'),
  G_SMALL(List.of(103), 'g'),
  H_SMALL(List.of(104), 'h'),
  I_SMALL(List.of(105), 'i'),
  J_SMALL(List.of(106), 'j'),
  K_SMALL(List.of(107), 'k'),
  L_SMALL(List.of(108), 'l'),
  M_SMALL(List.of(109), 'm'),
  N_SMALL(List.of(110), 'n'),
  O_SMALL(List.of(111), 'o'),
  P_SMALL(List.of(112), 'p'),
  Q_SMALL(List.of(113), 'q'),
  R_SMALL(List.of(114), 'r'),
  S_SMALL(List.of(115), 's'),
  T_SMALL(List.of(116), 't'),
  U_SMALL(List.of(117), 'u'),
  V_SMALL(List.of(118), 'v'),
  W_SMALL(List.of(119), 'w'),
  X_SMALL(List.of(120), 'x'),
  Y_SMALL(List.of(121), 'y'),
  Z_SMALL(List.of(122), 'z'),
  PARAGRAPH(List.of(194, 167), '§');

  private final List<Integer> bytes;
  private final Character character;

  Key(List<Integer> bytes, Character character) {
    this.bytes = Objects.requireNonNull(bytes);
    this.character = character;
  }

  public static Key ofBytes(List<Integer> bytes) {
    return new KeyParser(bytes).parseKey();
  }

  public List<Integer> getBytes() {
    return unmodifiableList(bytes);
  }

  public Optional<Character> getCharacter() {
    return Optional.ofNullable(character);
  }

}
