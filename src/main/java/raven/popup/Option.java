package raven.popup;

import java.awt.*;

public interface Option {

    public String getLayout(Component parent, float animate);

    public boolean useSnapshot();

    public boolean closeWhenPressedEsc();

    public boolean closeWhenClickOutside();

    public boolean blockBackground();

    public Color background();

    public float opacity();

    public int duration();

    public float getAnimate();

    public void setAnimate(float animate);
}
