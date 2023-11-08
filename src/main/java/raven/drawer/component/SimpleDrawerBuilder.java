package raven.drawer.component;

import raven.drawer.component.footer.SimpleFooter;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.DrawerBuilder;
import raven.drawer.component.menu.SimpleMenu;
import raven.drawer.component.menu.SimpleMenuOption;

import javax.swing.*;
import java.awt.*;

public abstract class SimpleDrawerBuilder implements DrawerBuilder {

    private SimpleHeader simpleHeader;
    private JSeparator headerSeparator;
    private SimpleMenu menu;
    private SimpleFooter footer;


    public SimpleDrawerBuilder() {
        simpleHeader = new SimpleHeader();
        headerSeparator = new JSeparator();
        menu = new SimpleMenu(getSimpleMenuOption());
        footer = new SimpleFooter();
    }

    @Override
    public Component getHeader() {
        return simpleHeader;
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

    public abstract SimpleMenuOption getSimpleMenuOption();
}
