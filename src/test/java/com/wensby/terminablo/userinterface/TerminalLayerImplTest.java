package com.wensby.terminablo.userinterface;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wensby.application.userinterface.PositionedTerminalCharacter;
import com.wensby.application.userinterface.TerminalCharacter;
import com.wensby.application.userinterface.TwoDimensionalCharacterArrayLayer;
import com.wensby.terminablo.userinterface.component.InterfaceLocation;
import org.junit.Test;

import java.util.List;

public class TerminalLayerImplTest {

  @Test
  public void put_true_whenCharacterPutOnLocation() {
    var character = mock(TerminalCharacter.class);
    var location = mock(InterfaceLocation.class);
    var layer = new TwoDimensionalCharacterArrayLayer(new TerminalCharacter[1][1]);
    when(location.getRow()).thenReturn(0);
    when(location.getColumn()).thenReturn(0);

    var put = layer.put(character, location);

    assertThat(put, is(true));
    assertThat(layer.getPositionedCharacters(), is(List.of(
        new PositionedTerminalCharacter(InterfaceLocation.at(0, 0), character))));
  }

  @Test
  public void put_false_whenLocationOutOfBounds() {
    var character = mock(TerminalCharacter.class);
    var location = mock(InterfaceLocation.class);
    var layer = new TwoDimensionalCharacterArrayLayer(new TerminalCharacter[1][1]);
    when(location.getRow()).thenReturn(0);
    when(location.getColumn()).thenReturn(1);
    when(character.getRenderLength()).thenReturn(1);

    var put = layer.put(character, location);

    assertThat(put, is(false));
  }
}