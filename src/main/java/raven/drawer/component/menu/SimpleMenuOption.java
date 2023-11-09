package raven.drawer.component.menu;

import java.util.ArrayList;
import java.util.List;

public class SimpleMenuOption {

    protected List<MenuEvent> events = new ArrayList<>();
    protected String menus[][];
    protected String icons[];
    protected float iconScale = 1f;
    protected String baseIconPath;
    protected boolean menuItemAutoSelect = true;

    public SimpleMenuOption setMenus(String menus[][]) {
        this.menus = menus;
        return this;
    }

    public SimpleMenuOption setIcons(String icons[]) {
        this.icons = icons;
        return this;
    }

    public SimpleMenuOption setIconScale(float iconScale) {
        this.iconScale = iconScale;
        return this;
    }

    public SimpleMenuOption setMenuItemAutoSelect(String baseIconPath) {
        this.baseIconPath = baseIconPath;
        return this;
    }

    public SimpleMenuOption setBaseIconPath(boolean menuItemAutoSelect) {
        this.menuItemAutoSelect = menuItemAutoSelect;
        return this;
    }

    public SimpleMenuOption addMenuEvent(MenuEvent event) {
        events.add(event);
        return this;
    }
}
