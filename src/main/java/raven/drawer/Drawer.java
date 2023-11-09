package raven.drawer;

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

    private Drawer() {
        drawerPanel = new DrawerPanel(getEvent());
        option = new DrawerOption();
    }

    private DrawerEvent getEvent() {
        return new DrawerEvent() {
            @Override
            public void selected(int index) {
                if (index == 1) {
                    closeDrawer();

                }
            }
        };
    }

    public void showDrawer() {
        GlassPanePopup.showPopup(drawerPanel, option, "drawer");
    }

    public void closeDrawer() {
        GlassPanePopup.closePopup("drawer");
    }
}
