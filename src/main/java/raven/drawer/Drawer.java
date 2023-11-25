package raven.drawer;

import raven.drawer.component.DrawerBuilder;
import raven.drawer.component.DrawerPanel;
import raven.popup.GlassPanePopup;

public class Drawer {

    private static Drawer instance;
    private DrawerPanel drawerPanel;
    private DrawerOption option;

    public static Drawer getInstance() {
        if (instance == null) {
            instance = new Drawer();
        }
        return instance;
    }

    public static Drawer newInstance() {
        return new Drawer();
    }

    private Drawer() {
    }

    public void setDrawerBuilder(DrawerBuilder drawerBuilder) {
        drawerPanel = new DrawerPanel(drawerBuilder);
        option = new DrawerOption(drawerBuilder.getDrawerWidth());
        drawerBuilder.build(drawerPanel);
    }

    public void showDrawer() {
        if (drawerPanel == null) {
            throw new NullPointerException("Drawer builder has not initialize");
        }
        if (!isShowing()) {
            GlassPanePopup.showPopup(drawerPanel, option, "drawer");
        }
    }

    public void closeDrawer() {
        GlassPanePopup.closePopup("drawer");
    }

    public boolean isShowing() {
        return GlassPanePopup.isShowing(drawerPanel);
    }

    public DrawerPanel getDrawerPanel() {
        return drawerPanel;
    }
}
