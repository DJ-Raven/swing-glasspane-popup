package test;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.footer.SimpleFooterStyle;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.*;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.swing.AvatarIcon;

import javax.swing.*;
import java.awt.*;

public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/image/profile.png"), 60, 60, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Ra Ven")
                .setDescription("raven@gmail.com")
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, "" +
                                "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, "" +
                                "[light]foreground:#E1E1E1");
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Java Swing Drawer")
                .setDescription("Version 1.1.0")
                .setFooterStyle(new SimpleFooterStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, "" +
                                "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, "" +
                                "[light]foreground:#E1E1E1");
                    }
                });
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
        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int index, int subIndex) {
                System.out.println("Drawer menu selected " + index + " " + subIndex);
            }
        });
        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int index) {
                menu.putClientProperty(FlatClientProperties.STYLE, "" +
                        "[light]foreground:#FAFAFA;" +
                        "arc:10");
            }

            @Override
            public void styleSubMenuItem(JButton subMenu, int index, int subIndex) {
                subMenu.putClientProperty(FlatClientProperties.STYLE, "" +
                        "[light]foreground:#FAFAFA;" +
                        "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, "" +
                        "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, "" +
                        "[light]foreground:darken(#FAFAFA,15%);" +
                        "[dark]foreground:darken($Label.foreground,30%)");
            }
        });

        simpleMenuOption.setMenuValidation(new MenuValidation() {
            @Override
            public boolean menuValidation(int index, int subIndex) {
                if (index == 3) {
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

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:$Drawer.background");
    }

    @Override
    public int getDrawerWidth() {
        return 275;
    }
}
