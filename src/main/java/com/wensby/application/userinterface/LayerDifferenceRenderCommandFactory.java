package com.wensby.application.userinterface;

public interface LayerDifferenceRenderCommandFactory {
  TerminalRenderCommand createCommand(TerminalFrame previousFrame, TerminalLayer frame);
}
