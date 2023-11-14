package raven.popup;

import com.formdev.flatlaf.FlatLaf;

import java.awt.*;

public class DefaultOption implements Option {

    private float animate;

    @Override
    public String getLayout(Component parent, float animate) {
        float an = 20f / parent.getHeight();
        float space = 0.5f + an - (animate * an);
        return "pos 0.5al " + space + "al,height ::100%-20";
    }

    @Override
    public boolean useSnapshot() {
        return true;
    }

    @Override
    public boolean closeWhenPressedEsc() {
        return true;
    }

    @Override
    public boolean closeWhenClickOutside() {
        return false;
    }

    @Override
    public boolean blockBackground() {
        return true;
    }

    @Override
    public Color background() {
        return FlatLaf.isLafDark()?new Color(50, 50, 50):new Color(20, 20, 20);
    }

    @Override
    public float opacity() {
        return 0.5f;
    }

    @Override
    public int duration() {
        return 300;
    }

    @Override
    public float getAnimate() {
        return animate;
    }

    @Override
    public void setAnimate(float animate) {
        this.animate = animate;
    }
}
