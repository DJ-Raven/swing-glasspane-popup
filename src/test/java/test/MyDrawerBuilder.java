package test;

import raven.drawer.component.menu.SimpleDrawerBuilder;
import raven.drawer.component.menu.SimpleMenuOption;

public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        String menus[][] = {
                {"~MAIN~"},
                {"Dashboard"},
                {"~WEB APP~"},
                {"Email", "Inbox", "Read", "Compost"},
                {"Chat"},
                {"Calendar"},
                {"~COMPONENT~"},
                {"Advanced UI", "Cropper", "Owl Carousel", "Sweet Alert"},
                {"Forms", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
                {"~OTHER~"},
                {"Charts", "Apex", "Flot", "Sparkline"},
                {"Icons", "Feather Icons", "Flag Icons", "Mdi Icons"},
                {"Special Pages", "Blank page", "Faq", "Invoice", "Profile", "Pricing", "Timeline"},
                {"Logout"}
        };
        String icons[] = {
                "1.svg",
                ""
        };
        return new SimpleMenuOption().setMenus(menus).setIcons(icons);
    }
}
