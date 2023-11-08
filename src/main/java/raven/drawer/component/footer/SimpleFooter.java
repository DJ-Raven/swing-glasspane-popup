package raven.drawer.component.footer;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SimpleFooter extends JPanel {

    public SimpleFooter() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,insets 10,fill,gap 3"));

        labelTitle = new JLabel("Java Swing Drawer");
        labelDescription = new JLabel("Version 1.1.0");
        labelDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:-1;" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        add(labelTitle);
        add(labelDescription);
    }

    private JLabel labelTitle;
    private JLabel labelDescription;
}
