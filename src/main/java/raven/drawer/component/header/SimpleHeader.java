package raven.drawer.component.header;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.swing.AvatarIcon;

import javax.swing.*;

public class SimpleHeader extends JPanel {

    public SimpleHeader() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,insets 3 10 3 10,fill,gap 3"));

        profile = new JLabel(new AvatarIcon("C:\\Users\\RAVEN\\Desktop\\download\\p2.png", 50, -1, 999));
        labelTitle = new JLabel("Header Name");
        labelDescription = new JLabel("header description");
        labelDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:-1;" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        add(profile);
        add(labelTitle);
        add(labelDescription);
    }

    private JLabel profile;
    private JLabel labelTitle;
    private JLabel labelDescription;
}
