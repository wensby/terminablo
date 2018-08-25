package com.wensby.terminablo.userinterface.terminal;

import com.wensby.userinterface.LinuxTerminalCharacterFactory;
import com.wensby.userinterface.TerminalCharacter;

public class LinuxTerminalRenderCommandFactory implements TerminalRenderCommandFactory {

    private final LinuxTerminalCharacterFactory terminalCharacterFactory;

    public LinuxTerminalRenderCommandFactory(LinuxTerminalCharacterFactory terminalCharacterFactory) {
        this.terminalCharacterFactory = terminalCharacterFactory;
    }

    @Override
    public TerminalRenderCommand createCommand(TerminalCharacter[][] characters) {
        TerminalRenderCommandBuilder commandBuilder = new TerminalRenderCommandBuilder();
        for (int y = 0; y < characters.length; y++) {
            TerminalRenderCommand moveCursorCommand = createMoveCursorCommand(y, 0);
            commandBuilder.addCommand(moveCursorCommand);
            int rowLength = characters[y].length;
            for (int x = 0; x < rowLength; x++) {
                TerminalCharacter character = characters[y][x];
                if (character != null) {
                    commandBuilder.addTerminalCharacter(character);
                }
                else {
                    commandBuilder.addTerminalCharacter(terminalCharacterFactory.createCharacter(' '));
                }
            }
        }
        return commandBuilder.build();
    }

    @Override
    public TerminalRenderCommand createMoveCursorCommand(int row, int column) {
        return new LinuxMoveCursorCommand(TerminalCoordinates.of(row, column));
    }
}
