package com.wensby.terminablo.userinterface.component;

import com.wensby.userinterface.InterfaceSize;

public abstract class ComponentDecorator implements Component {

    final Component component;

    ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void render(char[][] canvas) {
        component.render(canvas);
    }

    @Override
    public InterfaceLocation getLocation() {
        return component.getLocation();
    }

    @Override
    public InterfaceSize getSize() {
        return component.getSize();
    }
}
