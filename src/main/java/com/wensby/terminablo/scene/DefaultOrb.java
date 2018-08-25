package com.wensby.terminablo.scene;

import java.awt.*;
import java.math.BigDecimal;

public class DefaultOrb implements Orb {

    private final BigDecimal value;
    private final BigDecimal capacity;
    private final String label;
    private final Color color;

    public DefaultOrb(String label, Color color, BigDecimal capacity, BigDecimal value) {
        this.value = value;
        this.capacity = capacity;
        this.label = label;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public BigDecimal getCapacity() {
        return capacity;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }
}
