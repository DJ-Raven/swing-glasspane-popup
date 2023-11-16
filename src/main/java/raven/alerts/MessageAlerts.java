package raven.alerts;

import raven.popup.GlassPanePopup;

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

    }

    public void showMessage(String title, String message, MessageType messageType) {

        GlassPanePopup.showPopup(new SimpleAlerts(AlertsOption.getAlertsOption(messageType), title, message));
    }


    public enum MessageType {
        SUCCESS, ERROR, WARNING
    }
}
