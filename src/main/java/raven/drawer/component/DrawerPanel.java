package raven.drawer.component;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.popup.component.GlassPaneChild;

import javax.swing.*;
import java.awt.*;

public class DrawerPanel extends GlassPaneChild {

    private DrawerBuilder drawerBuilder;


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
        return scroll;
    }
}
