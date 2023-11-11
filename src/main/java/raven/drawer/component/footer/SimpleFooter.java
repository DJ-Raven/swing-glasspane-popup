package raven.drawer.component.footer;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SimpleFooter extends JPanel {

    private SimpleFooterData simpleFooterData;

    public SimpleFooter(SimpleFooterData simpleFooterData) {
        this.simpleFooterData = simpleFooterData;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,insets 5 20 10 20,fill,gap 3"));

        labelTitle = new JLabel(simpleFooterData.title);
        labelDescription = new JLabel(simpleFooterData.description);
        labelDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:-1;" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        add(labelTitle);
        add(labelDescription);
    }

    public SimpleFooterData getSimpleFooterData() {
        return simpleFooterData;
    }

    public void setSimpleFooterData(SimpleFooterData simpleFooterData) {
        this.simpleFooterData = simpleFooterData;
        labelTitle.setText(simpleFooterData.title);
        labelDescription.setText(simpleFooterData.description);
    }

    private JLabel labelTitle;
    private JLabel labelDescription;
}
