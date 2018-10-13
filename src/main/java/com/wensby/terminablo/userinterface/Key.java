package com.wensby.terminablo.userinterface;

import static java.util.Collections.unmodifiableList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Key {

  END_OF_FILE(Arrays.asList(3), null),
  TAB(Arrays.asList(9), null),
  CARRIAGE_RETURN(Arrays.asList(13), null),
  ESCAPE(Arrays.asList(27), null),
  ARROW_UP(Arrays.asList(27, 91, 65), null),
  ARROW_DOWN(Arrays.asList(27, 91, 66), null),
  ARROW_RIGHT(Arrays.asList(27, 91, 67), null),
  ARROW_LEFT(Arrays.asList(27, 91, 68), null),
  SPACE(Arrays.asList(32), ' '),
  SINGLE_QUOTE(Arrays.asList(39), '\''),
  COMMA(Arrays.asList(44), ','),
  DASH(Arrays.asList(45), '-'),
  PERIOD(Arrays.asList(46), '.'),
  SLASH_FORWARD(Arrays.asList(47), '/'),
  ZERO(Arrays.asList(48), '0'),
  ONE(Arrays.asList(49), '1'),
  TWO(Arrays.asList(50), '2'),
  THREE(Arrays.asList(51), '3'),
  FOUR(Arrays.asList(52), '4'),
  FIVE(Arrays.asList(53), '5'),
  SIX(Arrays.asList(54), '6'),
  SEVEN(Arrays.asList(55), '7'),
  EIGHT(Arrays.asList(56), '8'),
  NINE(Arrays.asList(57), '9'),
  SEMI_COLON(Arrays.asList(59), ';'),
  EQUALS(Arrays.asList(61), '='),
  BRACKET_SQUARE_LEFT(Arrays.asList(91), '['),
  SLASH_BACKWARD(Arrays.asList(92), '\\'),
  BRACKET_SQUARE_RIGHT(Arrays.asList(93), ']'),
  SMALL_BACKWARD_FNUTT(Arrays.asList(96), '`'), // TODO fix name
  A_SMALL(Arrays.asList(97), 'a'),
  B_SMALL(Arrays.asList(98), 'b'),
  C_SMALL(Arrays.asList(99), 'c'),
  D_SMALL(Arrays.asList(100), 'd'),
  E_SMALL(Arrays.asList(101), 'e'),
  F_SMALL(Arrays.asList(102), 'f'),
  G_SMALL(Arrays.asList(103), 'g'),
  H_SMALL(Arrays.asList(104), 'h'),
  I_SMALL(Arrays.asList(105), 'i'),
  J_SMALL(Arrays.asList(106), 'j'),
  K_SMALL(Arrays.asList(107), 'k'),
  L_SMALL(Arrays.asList(108), 'l'),
  M_SMALL(Arrays.asList(109), 'm'),
  N_SMALL(Arrays.asList(110), 'n'),
  O_SMALL(Arrays.asList(111), 'o'),
  P_SMALL(Arrays.asList(112), 'p'),
  Q_SMALL(Arrays.asList(113), 'q'),
  R_SMALL(Arrays.asList(114), 'r'),
  S_SMALL(Arrays.asList(115), 's'),
  T_SMALL(Arrays.asList(116), 't'),
  U_SMALL(Arrays.asList(117), 'u'),
  V_SMALL(Arrays.asList(118), 'v'),
  W_SMALL(Arrays.asList(119), 'w'),
  X_SMALL(Arrays.asList(120), 'x'),
  Y_SMALL(Arrays.asList(121), 'y'),
  Z_SMALL(Arrays.asList(122), 'z'),
  PARAGRAPH(Arrays.asList(194, 167), '§');

  private final List<Integer> bytes;
  private final Character character;

  Key(List<Integer> bytes, Character character) {
    this.bytes = bytes;
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
