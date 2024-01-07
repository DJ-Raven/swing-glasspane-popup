package test;

import raven.drawer.component.menu.data.Item;
import raven.drawer.component.menu.data.MenuItem;

public class Test {

    public static void main(String[] args) {

        MenuItem items[] = new MenuItem[]{
                new Item.Label("MAIN"),
                new Item("Dashboard"),
                new Item.Label("WEB APP"),
                new Item("Email")
                        .subMenu("Inbox")
                        .subMenu(
                                new Item("Read")
                                        .subMenu("Read 1")
                                        .subMenu("Read 2")
                                        .subMenu(
                                                new Item("Item")
                                                        .subMenu("Item 1")
                                                        .subMenu("Item 2")
                                        )
                        )
                        .subMenu("Compost"),
                new Item("Chat"),
                new Item("Calendar"),
                new Item.Label("COMPONENT"),
                new Item("Advanced UI")
                        .subMenu("Cropper")
                        .subMenu("Owl Carousel")
                        .subMenu("Sweet Alert"),
                new Item("Forms")
                        .subMenu("Basic Elements")
                        .subMenu("Advanced Elements")
                        .subMenu("SEditors")
                        .subMenu("Wizard"),
                new Item.Label("OTHER"),
                new Item("Charts")
                        .subMenu("Apex")
                        .subMenu("Flot")
                        .subMenu("Sparkline"),
                new Item("Icons")
                        .subMenu("Feather Icons")
                        .subMenu("Flag Icons")
                        .subMenu("Mdi Icons"),
                new Item("Special Pages")
                        .subMenu("Blank page")
                        .subMenu("Faq")
                        .subMenu("Invoice")
                        .subMenu("Profile")
                        .subMenu("Pricing")
                        .subMenu("Timeline")
        };
    }
}
