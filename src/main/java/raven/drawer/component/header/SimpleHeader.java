package raven.drawer.component.header;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SimpleHeader extends JPanel {

    public SimpleHeader() {
        init();
    }


    private void init() {
        setLayout(new MigLayout("wrap,insets 3 10 3 10,fill,gap 3"));

        labelTitle = new JLabel("Header Name");
        labelDescription = new JLabel("header description");
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
