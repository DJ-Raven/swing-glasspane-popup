package raven.drawer.component;

import java.awt.*;

public interface DrawerBuilder {

    public Component getHeader();

    public Component getHeaderSeparator();

    public Component getMenu();

    public Component getFooter();
}
