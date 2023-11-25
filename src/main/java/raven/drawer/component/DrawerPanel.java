package raven.drawer.component;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.popup.component.GlassPaneChild;

import javax.swing.*;
import java.awt.*;

public class DrawerPanel extends GlassPaneChild {

    private final DrawerBuilder drawerBuilder;

    public DrawerPanel(DrawerBuilder drawerBuilder) {
        this.drawerBuilder = drawerBuilder;
        init();
    }

    private void init() {
        String layoutRow = "";
        if (drawerBuilder.getHeader() != null) {
            layoutRow = "[grow 0]";
            add(drawerBuilder.getHeader());
        }
        if (drawerBuilder.getHeaderSeparator() != null) {
            layoutRow += "[grow 0,2::]";
            add(drawerBuilder.getHeaderSeparator());
        }
        if (drawerBuilder.getMenu() != null) {
            layoutRow += "[fill]";
            add(createScroll(drawerBuilder.getMenu()));
        }
        if (drawerBuilder.getFooter() != null) {
            layoutRow += "[grow 0]";
            add(drawerBuilder.getFooter());
        }
        setLayout(new MigLayout("wrap,insets 0,fill", "fill", layoutRow));
    }

    protected JScrollPane createScroll(Component component) {
        JScrollPane scroll = new JScrollPane(component);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "width:9;" +
                "trackArc:999;" +
                "thumbInsets:0,3,0,3;" +
                "trackInsets:0,3,0,3;" +
                "background:null");
        scroll.setBorder(BorderFactory.createEmptyBorder());
        return scroll;
    }

    public DrawerBuilder getDrawerBuilder() {
        return drawerBuilder;
    }
}
