package raven.drawer.component.header;

import net.miginfocom.swing.MigLayout;
import raven.utils.FlatLafStyleUtils;

import javax.swing.*;

public class SimpleHeader extends JPanel {

    private SimpleHeaderData simpleHeaderData;

    public SimpleHeader(SimpleHeaderData simpleHeaderData) {
        this.simpleHeaderData = simpleHeaderData;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,insets 10 20 5 20,fill,gap 3"));
        profile = new JLabel(simpleHeaderData.icon);
        labelTitle = new JLabel(simpleHeaderData.title);
        labelDescription = new JLabel(simpleHeaderData.description);

        if (simpleHeaderData.simpleHeaderStyle != null) {
            simpleHeaderData.simpleHeaderStyle.styleHeader(this);
            simpleHeaderData.simpleHeaderStyle.styleProfile(profile);
            simpleHeaderData.simpleHeaderStyle.styleTitle(labelTitle);
            simpleHeaderData.simpleHeaderStyle.styleDescription(labelDescription);
        }
        FlatLafStyleUtils.appendStyleIfAbsent(this, "" +
                "background:null");
        FlatLafStyleUtils.appendStyleIfAbsent(profile, "" +
                "background:$Component.borderColor");
        FlatLafStyleUtils.appendStyleIfAbsent(labelDescription, "" +
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
