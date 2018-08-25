package com.wensby.terminablo.userinterface.component;

import com.wensby.userinterface.InterfaceSize;

public class BorderDecorator extends ComponentDecorator {

    BorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void render(char[][] canvas) {
        renderTopBorder(canvas);
        renderBottomBorder(canvas);
        renderLeftBorder(canvas);
        renderRightBorder(canvas);
        super.render(canvas);
    }

    private void renderTopBorder(char[][] canvas) {
        InterfaceLocation location = getLocation();
        int row = location.getRow() - 1;
        int columnStart = location.getColumn();
        int columnEnd = location.getColumn() + getSize().getWidth() - 1;
        renderHorizontalLine(canvas, row, columnStart, columnEnd, '_');
    }

    private void renderBottomBorder(char[][] canvas) {
        InterfaceLocation location = getLocation();
        int row = location.getRow() + getSize().getHeight();
        int columnStart = location.getColumn();
        int columnEnd = location.getColumn() + getSize().getWidth() - 1;
        renderHorizontalLine(canvas, row, columnStart, columnEnd, '⎺');
    }

    private void renderLeftBorder(char[][] canvas) {
        InterfaceLocation location = getLocation();
        InterfaceSize size = getSize();
        int column = location.getColumn() - 1;
        int rowStart = location.getRow();
        int rowEnd = rowStart + size.getHeight() - 1;
        renderVerticalLine(canvas, column, rowStart, rowEnd, '⎟');
    }

    private void renderRightBorder(char[][] canvas) {
        InterfaceLocation location = getLocation();
        InterfaceSize size = getSize();
        int column = location.getColumn() + size.getWidth();
        int rowStart = location.getRow();
        int rowEnd = rowStart + size.getHeight() - 1;
        renderVerticalLine(canvas, column, rowStart, rowEnd, '⎜');
    }

    private void renderHorizontalLine(char[][] canvas, int row, int columnStart, int columnEnd, char character) {
        for (int column = columnStart; column <= columnEnd; column++) {
            canvas[row][column] = character;
        }
    }

    private void renderVerticalLine(char[][] canvas, final int column, final int rowStart, final int rowEnd, char character) {
        for (int row = rowStart; row <= rowEnd; row++) {
            canvas[row][column] = character;
        }
    }
}
