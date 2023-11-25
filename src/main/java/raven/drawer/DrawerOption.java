package raven.drawer;

import raven.popup.DefaultOption;

import java.awt.*;

public class DrawerOption extends DefaultOption {

    private final int width;

    public DrawerOption() {
        this(275);
    }

    public DrawerOption(int width) {
        this.width = width;
    }

    @Override
    public String getLayout(Component parent, float animate) {
        String l;
        if (parent.getComponentOrientation().isLeftToRight()) {
            float x = (animate * width) - width;
            l = "pos " + x + " 0,height 100%,width " + width;
        } else {
            float x = (animate * width);
            l = "pos 100%-" + x + " 0,height 100%,width " + width;
        }
        return l;
    }

    @Override
    public boolean closeWhenClickOutside() {
        return true;
    }

    @Override
    public boolean closeWhenPressedEsc() {
        return true;
    }
}
