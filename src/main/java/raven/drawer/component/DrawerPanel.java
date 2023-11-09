package raven.drawer.component;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.drawer.DrawerEvent;
import raven.popup.component.GlassPaneChild;

import javax.swing.*;
import java.awt.*;

public class DrawerPanel extends GlassPaneChild {

    private Component drawerHeader;
    private Component headerSeparator;
    private Component drawerMenu;
    private DrawerEvent event;

    public DrawerPanel(DrawerEvent event) {
        this.event = event;
        init();
    }

    private void init() {
        drawerHeader = new DefaultHeader();
        drawerMenu = new DefaultMenu();
        headerSeparator = new JSeparator();
        setLayout(new MigLayout("wrap,insets 0,fill", "fill","[grow 0][grow 0][fill][grow 0]"));

        add(drawerHeader);
        add(headerSeparator);
        JScrollPane scroll = new JScrollPane(drawerMenu);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "width:6;" +
                "trackArc:999;" +
                "thumbInsets:0,0,0,3;" +
                "trackInsets:0,0,0,3;");
        scroll.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "width:6;" +
                "trackArc:999;" +
                "thumbInsets:0,0,0,3;" +
                "trackInsets:0,0,0,3;");
        scroll.setBorder(BorderFactory.createEmptyBorder());
        add(scroll);
    }
}
