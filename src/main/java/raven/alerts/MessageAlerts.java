package raven.alerts;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class MessageAlerts {

    private static MessageAlerts instance;

    public static MessageAlerts getInstance() {
        if (instance == null) {
            instance = new MessageAlerts();
        }
        return instance;
    }

    private MessageAlerts() {
    }

    public void showMessage(String title, String message) {
        showMessage(title, message, MessageType.DEFAULT);
    }

    public void showMessage(String title, String message, MessageType messageType) {
        AlertsOption option = AlertsOption.getAlertsOption(messageType);
        GlassPanePopup.showPopup(new SimpleAlerts(createSimpleMessage(option, title, message), option));
    }


    private Component createSimpleMessage(AlertsOption option, String title, String message) {
        JPanel panel = new JPanel(new MigLayout("wrap,insets 0,center", "center"));
        panel.setOpaque(false);
        JLabel labelTitle = new JLabel(title);
        JTextPane textPane = new JTextPane();
        textPane.setOpaque(false);
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textPane.setEditable(false);
        textPane.setText(message);

        textPane.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:5,25,5,25;" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        labelTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +5");
        labelTitle.setForeground(option.baseColor);

        panel.add(labelTitle);
        panel.add(textPane);
        return panel;
    }

    public enum MessageType {
        SUCCESS, ERROR, WARNING, DEFAULT
    }
}
