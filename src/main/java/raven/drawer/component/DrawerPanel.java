package raven.drawer.component;

import net.miginfocom.swing.MigLayout;
import raven.popup.component.GlassPaneChild;

public class DrawerPanel extends GlassPaneChild {

    private final DrawerBuilder drawerBuilder;

    public DrawerPanel(DrawerBuilder drawerBuilder) {
        this.drawerBuilder = drawerBuilder;
        init();
    }

    private void init() {
        String layoutRow = "";
        if (drawerBuilder.getHeader() != null) {
            layoutRow = "[grow 0]";
            add(drawerBuilder.getHeader());
        }
        if (drawerBuilder.getHeaderSeparator() != null) {
            layoutRow += "[grow 0,2::]";
            add(drawerBuilder.getHeaderSeparator());
        }
        if (drawerBuilder.getMenu() != null) {
            layoutRow += "[fill]";
            add(drawerBuilder.getMenu());
        }
        if (drawerBuilder.getFooter() != null) {
            layoutRow += "[grow 0]";
            add(drawerBuilder.getFooter());
        }
        setLayout(new MigLayout("wrap,insets 0,fill", "fill", layoutRow));
    }

    public DrawerBuilder getDrawerBuilder() {
        return drawerBuilder;
    }
}
