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

    public static final int DEFAULT_OPTION = -1;
    public static final int YES_NO_OPTION = 0;
    public static final int YES_NO_CANCEL_OPTION = 1;
    public static final int OK_CANCEL_OPTION = 2;

    // Return values
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;
    public static final int CANCEL_OPTION = 2;
    public static final int OK_OPTION = 0;
    public static final int CLOSED_OPTION = -1;

    public static MessageAlerts getInstance() {
        if (instance == null) {
            instance = new MessageAlerts();
        }
        return instance;
    }

    private MessageAlerts() {
    }

    public void showMessage(String title, String message) {
        showMessage(title, message, MessageType.DEFAULT, DEFAULT_OPTION);
    }

    public void showMessage(String title, String message, MessageType messageType) {
        showMessage(title, message, messageType, DEFAULT_OPTION);
    }

    public void showMessage(String title, String message, MessageType messageType, int option) {
        AlertsOption alertsOption = AlertsOption.getAlertsOption(messageType);
        GlassPanePopup.showPopup(new SimpleAlerts(createSimpleMessage(alertsOption, title, message), createActionButton(option, alertsOption.baseColor), alertsOption));
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
        if (option.baseColor != null) {
            labelTitle.setForeground(option.baseColor);
        }
        panel.add(labelTitle);
        JScrollPane scrollPane = new JScrollPane(textPane);
        if (option.effectOption != null) {
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
        }
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        applyScrollStyle(scrollPane.getVerticalScrollBar());
        applyScrollStyle(scrollPane.getHorizontalScrollBar());
        panel.add(scrollPane);
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
        return panel;
    }

    private JPanel createActionButton(int option, Color color) {
        JPanel panel = new JPanel(new MigLayout("insets 3,center,gapx 15", "center"));
        switch (option) {
            case DEFAULT_OPTION:
                panel.add(createButton("OK", color));
                break;
            case YES_NO_CANCEL_OPTION:
                panel.add(createButton("Cancel", null));
            case YES_NO_OPTION:
                panel.add(createButton("No", null), 0);
                panel.add(createButton("Yes", color), 0);
        }
        return panel;
    }

    private void applyScrollStyle(JScrollBar scrollBar) {
        scrollBar.setUnitIncrement(10);
        scrollBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "width:10;" +
                "trackArc:999;" +
                "thumbInsets:0,3,0,3;" +
                "trackInsets:0,3,0,3;");
    }

    private JButton createButton(String text, Color color) {
        JButton cmd = new JButton(text);
        cmd.addActionListener(e -> {

        });
        cmd.putClientProperty(FlatClientProperties.STYLE, "" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "arc:10;" +
                "font:+1;" +
                "margin:5,35,5,35;" +
                "foreground:" + (color == null ? "null" : "#F0F0F0") + ";" +
                "arc:999");
        if (color != null) {
            cmd.setBackground(color);
        }
        return cmd;
    }

    public enum MessageType {
        SUCCESS, ERROR, WARNING, DEFAULT
    }
}
