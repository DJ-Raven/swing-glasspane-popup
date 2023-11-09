package raven.drawer.component.header;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SimpleHeader extends JPanel {

    private SimpleHeaderData simpleHeaderData;

    public SimpleHeader(SimpleHeaderData simpleHeaderData) {
        this.simpleHeaderData = simpleHeaderData;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,insets 10 20 5 20,fill,gap 3"));
        putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        profile = new JLabel(simpleHeaderData.icon);
        profile.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:$Component.borderColor");
        labelTitle = new JLabel(simpleHeaderData.title);
        labelDescription = new JLabel(simpleHeaderData.description);
        labelDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:-1;" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        add(profile);
        add(labelTitle);
        add(labelDescription);
    }

    public SimpleHeaderData getSimpleHeaderData() {
        return simpleHeaderData;
    }

    public void setSimpleHeaderData(SimpleHeaderData simpleHeaderData) {
        this.simpleHeaderData = simpleHeaderData;
        profile.setIcon(simpleHeaderData.icon);
        labelTitle.setText(simpleHeaderData.title);
        labelDescription.setText(simpleHeaderData.description);
    }

    private JLabel profile;
    private JLabel labelTitle;
    private JLabel labelDescription;
}
