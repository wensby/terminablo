package com.wensby.terminablo.scene;

import com.wensby.userinterface.InterfacePosition;
import com.wensby.userinterface.InterfaceSize;
import com.wensby.userinterface.TerminalCharacter;
import com.wensby.userinterface.TerminalCharacterFactory;
import com.wensby.userinterface.TerminalLayer;
import com.wensby.userinterface.TerminalLayerImpl;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrbTerminalRepresentationFactory {

    private final TerminalCharacterFactory characterFactory;

    public OrbTerminalRepresentationFactory(TerminalCharacterFactory characterFactory) {
        this.characterFactory = characterFactory;
    }

    public TerminalLayer createRepresentation(Orb orb, InterfaceSize size) {
        if (isBorderless(size)) {
            return createScaledDownRepresentation(orb, size);
        }
        else {
            return createFullSizeRepresentation(orb, size);
        }
    }

    private TerminalLayer createFullSizeRepresentation(Orb orb, InterfaceSize size) {
        TerminalLayer layer = createLayer(size);
        TerminalLayer orbContent = createOrbContent(orb, size);
        layer.put(orbContent, InterfacePosition.atOrigin());
        return layer;
    }

    private TerminalLayer createScaledDownRepresentation(Orb orb, InterfaceSize size) {
        var layer = createLayer(size);
        fillInBorder(layer);
        var orbContent = createOrbContent(orb, new InterfaceSize(size.getWidth() - 2, size.getHeight() - 2));
        layer.put(orbContent, InterfacePosition.of(1, 1));
        fillInValue(layer, orb);
        return layer;
    }

    private TerminalLayer createLayer(InterfaceSize size) {
        var height = size.getHeight();
        var width = size.getWidth();
        var characters = new TerminalCharacter[height][width];
        return new TerminalLayerImpl(characters);
    }

    private TerminalLayer createOrbContent(Orb orb, InterfaceSize size) {
        var characters = new TerminalCharacter[size.getHeight()][size.getWidth()];
        var height = size.getHeight();
        var rowCapacity = orb.getCapacity().divide(new BigDecimal(height), RoundingMode.HALF_UP);
        var fullRowsAndRemainder = orb.getValue().divideAndRemainder(rowCapacity);
        var fullRows = fullRowsAndRemainder[0].intValue();
        for (int y = 0; y < fullRows; y++) {
            for (int x = 0; x < size.getWidth(); x++) {
                characters[height-1-y][x] = characterFactory.createCharacter(' ', null, orb.getColor());
            }
        }
        var remainder = fullRowsAndRemainder[1];
        if (fullRows != height) {
            var remainderRowCharacter = getRemainderRowCharacter(remainder, rowCapacity, orb.getColor());
            for (int x = 0; x < size.getWidth(); x++) {
                characters[height-1-fullRows][x] = remainderRowCharacter;
            }
        }
        return new TerminalLayerImpl(characters);
    }

    private void fillInBorder(TerminalLayer layer) {
        if (isBorderless(layer.getSize())) {
            var characters = layer.getCharacters();
            var height = layer.getSize().getHeight();
            var width = layer.getSize().getWidth();
            for (int i = 1; i < width - 1; i++) {
                characters[0][i] = characterFactory.createCharacter('─');
            }
            characters[0][0] = characterFactory.createCharacter('╭');
            characters[0][width - 1] = characterFactory.createCharacter('╮');
            for (int i = 1; i < height - 1; i++) {
                characters[i][0] = characterFactory.createCharacter('│');
                characters[i][width - 1] = characterFactory.createCharacter('│');
            }
            characters[height - 1][0] = characterFactory.createCharacter('╰');
            characters[height - 1][width - 1] = characterFactory.createCharacter('╯');
        }
    }

    private boolean isBorderless(InterfaceSize size) {
        return size.getWidth() > 2 && size.getHeight() > 2;
    }

    private TerminalCharacter getRemainderRowCharacter(BigDecimal remainder, BigDecimal rowCapacity, final Color color) {
        if (remainder.compareTo(BigDecimal.ZERO) == 0) {
            return characterFactory.createCharacter(' ');
        }
        var levels = BigDecimal.valueOf(9);
        var rowLevelCapacity = rowCapacity.divide(levels, 100, BigDecimal.ROUND_UP);
        var rowLevels = remainder.divide(rowLevelCapacity, BigDecimal.ROUND_CEILING).intValue();
        char c;
        if (rowLevels == 9) {
            return characterFactory.createCharacter(' ', null, color);
        }
        switch (rowLevels) {
            case 0: c = ' '; break;
            case 1: c = '▁'; break;
            case 2: c = '▂'; break;
            case 3: c = '▃'; break;
            case 4: c = '▄'; break;
            case 5: c = '▅'; break;
            case 6: c = '▆'; break;
            case 7: c = '▇'; break;
            case 8: c = '█'; break;
            default: throw new RuntimeException("wow");
        }
        return characterFactory.createCharacter(c, color, null);
    }


    private void fillInValue(TerminalLayer layer, Orb orb) {
        if (layer.getSize().getWidth() >= 6 && layer.getSize().getHeight() > 1) {
            var height = layer.getSize().getHeight();
            var width = layer.getSize().getWidth();
            var s = (orb.getValue() + " / " + orb.getCapacity()).substring(0, 6);
            var start = (width - s.length()) / 2;
            for (int i = 0; i < s.length(); i++) {
                layer.getCharacters()[height - 1][i + start] = characterFactory.createCharacter(s.charAt(i));
            }
        }
    }
}
