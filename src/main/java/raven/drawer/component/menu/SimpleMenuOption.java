package raven.drawer.component.menu;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import raven.drawer.component.menu.data.MenuItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleMenuOption {

    protected List<MenuEvent> events = new ArrayList<>();
    protected MenuValidation menuValidation = new MenuValidation();
    protected SimpleMenuStyle simpleMenuStyle;
    protected MenuItem menus[];
    protected float iconScale[] = {1f};

    protected String baseIconPath;
    protected boolean menuItemAutoSelect = true;

    public SimpleMenuOption setMenus(MenuItem menus[]) {
        this.menus = menus;
        return this;
    }

    public SimpleMenuOption setIconScale(float... iconScale) {
        this.iconScale = iconScale;
        return this;
    }

    public SimpleMenuOption setBaseIconPath(String baseIconPath) {
        this.baseIconPath = baseIconPath;
        return this;
    }

    public SimpleMenuOption setMenuItemAutoSelect(boolean menuItemAutoSelect) {
        this.menuItemAutoSelect = menuItemAutoSelect;
        return this;
    }

    public SimpleMenuOption setMenuValidation(MenuValidation menuValidation) {
        this.menuValidation = menuValidation;
        return this;
    }

    public SimpleMenuOption setMenuStyle(SimpleMenuStyle simpleMenuStyle) {
        this.simpleMenuStyle = simpleMenuStyle;
        return this;
    }

    public SimpleMenuOption addMenuEvent(MenuEvent event) {
        events.add(event);
        return this;
    }

    public Icon buildMenuIcon(String path, float scale) {
        FlatSVGIcon icon = new FlatSVGIcon(path, scale);
        return icon;
    }
}
