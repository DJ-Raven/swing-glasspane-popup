package test;

import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon("C:\\Users\\Raven\\Desktop\\image\\p3.png", 60, 60, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Ra Ven")
                .setDescription("raven@gmail.com");
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Java Swing Drawer")
                .setDescription("Version 1.1.0");
    }

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
        };
        String icons[] = {
                "dashboard.svg",
                "email.svg",
                "chat.svg",
                "calendar.svg",
                "ui.svg",
                "forms.svg",
                "chart.svg",
                "icon.svg",
                "page.svg",
        };
        SimpleMenuOption simpleMenuOption = new SimpleMenuOption();
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int index, int subIndex) {
                System.out.println("Drawer menu selected " + index + " " + subIndex);
            }
        });
        simpleMenuOption.setMenuValidation(new MenuValidation() {
            @Override
            public boolean menuValidation(int index, int subIndex) {
                if (index == 1 || index == 3 || index == 2) {
                    return false;
                } else if (index == 4 || index == 5) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        simpleMenuOption.setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("icon")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }
}
