package raven.drawer.component.menu;

import raven.drawer.component.DefaultHeader;
import raven.drawer.component.DrawerBuilder;

import javax.swing.*;
import java.awt.*;

public abstract class SimpleDrawerBuilder implements DrawerBuilder {

    private DefaultHeader defaultHeader;
    private JSeparator headerSeparator;
    private SimpleMenu menu;
    private JLabel footer;


    public SimpleDrawerBuilder() {
        defaultHeader = new DefaultHeader();
        headerSeparator = new JSeparator();
        menu = new SimpleMenu(getSimpleMenuOption());
        footer = new JLabel("Test Version");
    }

    @Override
    public Component getHeader() {
        return defaultHeader;
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
