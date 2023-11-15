package raven.alerts;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import raven.swing.AnimateIcon;

import javax.swing.*;
import java.awt.*;

public class AlertsOption {

    protected Icon icon;
    protected Color baseColor;

    protected float effectAlpha = 1f;
    protected boolean effectFadeOut = false;
    protected boolean effectLoop;
    protected Icon[] randomEffect;

    public AlertsOption(Icon icon, Color baseColor) {
        this.icon = icon;
        this.baseColor = baseColor;
    }

    public AlertsOption setRandomEffect(Icon[] randomEffect) {
        this.randomEffect = randomEffect;
        return this;
    }

    public AlertsOption setEffectAlpha(float effectAlpha) {
        this.effectAlpha = effectAlpha;
        return this;
    }

    public AlertsOption setEffectFadeOut(boolean effectFadeOut) {
        this.effectFadeOut = effectFadeOut;
        return this;
    }

    public AlertsOption setEffectLoop(boolean effectLoop) {
        this.effectLoop = effectLoop;
        return this;
    }

    protected static AlertsOption getAlertsOption(SimpleAlerts.AlertsType type) {
        if (type == SimpleAlerts.AlertsType.SUCCESS) {
            Icon effects[] = new Icon[]{
                    new FlatSVGIcon("raven/alerts/effect/check.svg"),
                    new FlatSVGIcon("raven/alerts/effect/star.svg"),
                    new FlatSVGIcon("raven/alerts/effect/firework.svg"),
                    new FlatSVGIcon("raven/alerts/effect/balloon.svg")
            };
            return getDefaultOption("raven/alerts/icon/success.svg", "#10b981")
                    .setRandomEffect(effects);
        } else if (type == SimpleAlerts.AlertsType.WARNING) {
            Icon effects[] = new Icon[]{
                    new FlatSVGIcon("raven/alerts/effect/check.svg"),
                    new FlatSVGIcon("raven/alerts/effect/star.svg"),
                    new FlatSVGIcon("raven/alerts/effect/firework.svg"),
                    new FlatSVGIcon("raven/alerts/effect/balloon.svg")
            };
            return getDefaultOption("raven/alerts/icon/warning.svg", "#f59e0b")
                    .setRandomEffect(effects);
        } else {
            Icon effects[] = new Icon[]{
                    new FlatSVGIcon("raven/alerts/effect/error.svg"),
                    new FlatSVGIcon("raven/alerts/effect/sad.svg"),
                    new FlatSVGIcon("raven/alerts/effect/shield.svg"),
                    new FlatSVGIcon("raven/alerts/effect/nothing.svg")
            };
            return getDefaultOption("raven/alerts/icon/error.svg", "#ef4444")
                    .setRandomEffect(effects);
        }
    }

    protected static AlertsOption getDefaultOption(String icon, String color) {
        return new AlertsOption(new AnimateIcon(icon, 4f), Color.decode(color))
                .setEffectAlpha(0.9f).
                setEffectFadeOut(true)
                .setEffectLoop(true);
    }
}
