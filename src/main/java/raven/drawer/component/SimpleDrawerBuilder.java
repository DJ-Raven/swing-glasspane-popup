package raven.drawer.component;

import raven.drawer.component.footer.SimpleFooter;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.SimpleMenu;
import raven.drawer.component.menu.SimpleMenuOption;

import javax.swing.*;
import java.awt.*;

public abstract class SimpleDrawerBuilder implements DrawerBuilder {

    protected SimpleHeader header;
    protected JSeparator headerSeparator;
    protected SimpleMenu menu;
    protected SimpleFooter footer;


    public SimpleDrawerBuilder() {
        header = new SimpleHeader(getSimpleHeaderData());
        headerSeparator = new JSeparator();
        menu = new SimpleMenu(getSimpleMenuOption());
        footer = new SimpleFooter(getSimpleFooterData());
    }

    @Override
    public Component getHeader() {
        return header;
    }

    @Override
    public Component getHeaderSeparator() {
        return headerSeparator;
    }

    @Override
    public Component getMenu() {
        return menu;
    }

    @Override
    public Component getFooter() {
        return footer;
    }

    public void build(DrawerPanel drawerPanel) {

    }

    public void rebuildMenu() {
        if (menu != null) {
            menu.rebuildMenu();
        }
    }

    public abstract SimpleHeaderData getSimpleHeaderData();

    public abstract SimpleMenuOption getSimpleMenuOption();

    public abstract SimpleFooterData getSimpleFooterData();
}
