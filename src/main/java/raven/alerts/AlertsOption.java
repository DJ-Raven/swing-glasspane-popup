package raven.alerts;

import raven.swing.AnimateIcon;

import javax.swing.*;
import java.awt.*;

public class AlertsOption {

    protected Icon icon;
    protected Color baseColor;

    public AlertsOption(Icon icon, Color baseColor) {
        this.icon = icon;
        this.baseColor = baseColor;
    }

    protected static AlertsOption getAlertsOption(SimpleAlerts.AlertsType type) {
        if (type == SimpleAlerts.AlertsType.SUCCESS) {
            return new AlertsOption(new AnimateIcon("raven/alerts/icon/success.svg", 4f), Color.decode("#10b981"));
        } else if (type == SimpleAlerts.AlertsType.WARNING) {
            return new AlertsOption(new AnimateIcon("raven/alerts/icon/warning.svg", 4f), Color.decode("#f59e0b"));
        } else {
            return new AlertsOption(new AnimateIcon("raven/alerts/icon/error.svg", 4f), Color.decode("#ef4444"));
        }
    }
}
