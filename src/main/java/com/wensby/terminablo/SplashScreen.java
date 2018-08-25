package com.wensby.terminablo;

import com.wensby.terminablo.userinterface.Key;
import com.wensby.userinterface.UserInput;
import com.wensby.terminablo.userinterface.terminal.LinuxTerminalVisualCanvas;

import java.time.Duration;

public class SplashScreen implements Scene {

    private Duration displayTime = Duration.ZERO;
    private final LinuxTerminalVisualCanvas visualCanvas;
    private int row = 0;
    private int column = 0;
    private int updates = 0;

    public SplashScreen(LinuxTerminalVisualCanvas visualCanvas) {
        this.visualCanvas = visualCanvas;
    }

    @Override
    public Scene update(Duration elapsedTime, UserInput input) {
        displayTime = displayTime.plus(elapsedTime);
        if (input.getKeyStrokes().contains(Key.ARROW_DOWN)) {
            row++;
        }
        if (input.getKeyStrokes().contains(Key.ARROW_UP)) {
            row--;
        }
        if (input.getKeyStrokes().contains(Key.ARROW_RIGHT)) {
            column++;
        }
        if (input.getKeyStrokes().contains(Key.ARROW_LEFT)) {
            column--;
        }
        row = Math.max(row, 0);
        row = Math.min(row, 18);
        column = Math.max(column, 0);
        column = Math.min(column, 38);
        updates++;
        return this;
    }

}
