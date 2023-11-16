package raven.alerts;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import com.formdev.flatlaf.util.Graphics2DProxy;
import net.miginfocom.swing.MigLayout;
import raven.popup.GlassPanePopup;
import raven.popup.component.GlassPaneChild;
import raven.swing.AnimateIcon;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class SimpleAlerts extends GlassPaneChild {

    private final String title;
    private final String message;
    private PanelEffect panelEffect;

    public SimpleAlerts(AlertsOption option, String title, String message) {
        this.title = title;
        this.message = message;
        init(option);
    }

    private void init(AlertsOption option) {
        setLayout(new MigLayout("wrap,fillx,insets 15 0 15 0", "[fill,400]"));
        panelEffect = new PanelEffect(option, title, message);
        add(panelEffect);
    }

    @Override
    public void popupShow() {
        panelEffect.createEffect();
    }

    @Override
    public int getRoundBorder() {
        return 20;
    }

    protected class PanelEffect extends JPanel {

        private AlertsOption option;
        private Effect effects[];
        private float animate;
        private Animator animator;

        private JLabel labelIcon;
        private JLabel labelTitle;
        private JTextPane textPane;
        private AnimateIcon animateIcon;

        protected void start() {
            if (animator == null) {
                animator = new Animator(2000, new Animator.TimingTarget() {
                    @Override
                    public void timingEvent(float v) {
                        animate = v;
                        if (animateIcon != null) {
                            animateIcon.setAnimate(easeOutBounce(v));
                        }
                        repaint();
                    }

                    @Override
                    public void end() {
                        if (option.effectLoop && isShowing()) {
                            SwingUtilities.invokeLater(() -> {
                                createEffect();
                            });
                        }
                    }
                });
                animator.setInterpolator(CubicBezierEasing.EASE);
            }
            if (animator.isRunning()) {
                animator.stop();
            }
            animator.start();
        }

        private float easeOutBounce(float x) {
            double n1 = 7.5625f;
            double d1 = 2.75f;
            double v;
            if (x < 1 / d1) {
                v = n1 * x * x;
            } else if (x < 2 / d1) {
                v = n1 * (x -= 1.5 / d1) * x + 0.75f;
            } else if (x < 2.5 / d1) {
                v = n1 * (x -= 2.25 / d1) * x + 0.9375f;
            } else {
                v = n1 * (x -= 2.625 / d1) * x + 0.984375f;
            }
            return (float) v;
        }

        private void createEffect() {
            effects = new Effect[30];
            for (int i = 0; i < effects.length; i++) {
                effects[i] = new Effect(option.randomEffect);
            }
            start();
        }


        public PanelEffect(AlertsOption option, String title, String message) {
            this.option = option;
            setLayout(new MigLayout("fillx,wrap,insets 0", "[fill,center]", "0[]3[]10[]20[]20"));
            if (option.icon instanceof AnimateIcon) {
                animateIcon = (AnimateIcon) option.icon;
            }
            labelIcon = new JLabel(option.icon);
            labelTitle = new JLabel(title, JLabel.CENTER);
            textPane = new JTextPane();
            textPane.setOpaque(false);
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            textPane.setEditable(false);
            textPane.setText(message);
            labelIcon.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:25,5,10,5");
            textPane.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:5,25,5,25;" +
                    "[light]foreground:lighten(@foreground,30%);" +
                    "[dark]foreground:darken(@foreground,30%)");
            labelTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                    "font:bold +5");
            labelTitle.setForeground(option.baseColor);
            add(createCloseButton(), "pos 100%-pref-25 2");
            add(labelIcon);
            add(labelTitle);
            add(textPane);
            createButton();
        }

        protected Component createCloseButton() {
            JButton cmdClose = new JButton(new FlatSVGIcon("raven/popup/icon/close.svg", 0.8f));
            cmdClose.putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:999;" +
                    "borderWidth:0;" +
                    "focusWidth:0;" +
                    "innerFocusWidth:0;" +
                    "background:null");
            cmdClose.addActionListener(e -> GlassPanePopup.closePopup(SimpleAlerts.this));
            return cmdClose;
        }

        protected void createButton() {
            JButton cmd = new JButton("OK");
            cmd.setBackground(option.baseColor);
            cmd.addActionListener(e -> {

            });
            cmd.putClientProperty(FlatClientProperties.STYLE, "" +
                    "borderWidth:0;" +
                    "focusWidth:0;" +
                    "innerFocusWidth:0;" +
                    "arc:10;" +
                    "font:+1;" +
                    "margin:5,50,5,50;" +
                    "foreground:#F0F0F0;" +
                    "arc:999");
            add(cmd, "grow 0");
        }

        @Override
        protected void paintChildren(Graphics g) {
            if (effects != null && effects.length > 0) {
                Graphics2D g2 = (Graphics2D) g.create();
                FlatUIUtils.setRenderingHints(g2);
                int width = getWidth();
                int height = getHeight();
                int x = labelIcon.getX() + labelIcon.getWidth() / 2;
                int y = labelIcon.getY() + labelIcon.getHeight() / 2;
                float size = Math.min(width, height) / 2;

                float l = size * animate;

                g2.translate(x, y);

                for (int i = 0; i < effects.length; i++) {
                    Effect effect = effects[i];
                    double sp = effect.speed * 0.05f;
                    double xx = Math.cos(Math.toRadians(effect.direction)) * l * sp;
                    double yy = Math.sin(Math.toRadians(effect.direction)) * l * sp;
                    AffineTransform oldTran = g2.getTransform();
                    Icon icon = option.randomEffect[effect.effectIndex];
                    int iw = icon.getIconWidth() / 2;
                    int ih = icon.getIconHeight() / 2;
                    xx -= iw;
                    yy -= ih;
                    g2.translate(xx, yy);
                    g2.rotate(Math.toRadians(animate * 360), iw, ih);
                    if (option.effectAlpha < 1f) {
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, option.effectAlpha));
                    }
                    if (option.effectFadeOut) {
                        float remove = 0.7f;
                        if (animate >= remove) {
                            float f = ((animate - remove) / (1f - remove)) * option.effectAlpha;
                            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, option.effectAlpha - f));
                        }
                    }

                    icon.paintIcon(null, new GraphicsColorFilter(g2, effect.color), 0, 0);
                    g2.setTransform(oldTran);
                }
                g2.dispose();
            }
            super.paintChildren(g);
        }
    }

    protected class Effect {

        protected int effectIndex;
        protected Color color;
        protected float direction;
        protected float speed;

        public Effect(Icon[] randomEffect) {
            Random random = new Random();
            color = effectColors[random.nextInt(effectColors.length)];
            if (randomEffect != null && randomEffect.length > 0) {
                effectIndex = random.nextInt(randomEffect.length);
            }
            direction = random.nextInt(360);
            speed = random.nextInt(25) + 5;
        }
    }

    protected static final Color effectColors[] = {
            Color.decode("#f43f5e"), Color.decode("#6366f1"),
            Color.decode("#ec4899"), Color.decode("#3b82f6"),
            Color.decode("#d946ef"), Color.decode("#0ea5e9"),
            Color.decode("#a855f7"), Color.decode("#06b6d4"),
            Color.decode("#8b5cf6"), Color.decode("#14b8a6"),
            Color.decode("#10b981"), Color.decode("#22c55e"),
            Color.decode("#84cc16"), Color.decode("#eab308"),
            Color.decode("#f59e0b"), Color.decode("#f97316"),
            Color.decode("#ef4444"), Color.decode("#78716c"),
            Color.decode("#737373"), Color.decode("#71717a"),
            Color.decode("#6b7280"), Color.decode("#64748b")
    };

    private static class GraphicsColorFilter extends Graphics2DProxy {

        protected Color color;

        public GraphicsColorFilter(Graphics2D delegate, Color color) {
            super(delegate);
            this.color = color;
        }

        @Override
        public Graphics create() {
            return new GraphicsColorFilter((Graphics2D) super.create(), this.color);
        }

        @Override
        public void setPaint(Paint paint) {
            super.setPaint(color);
        }
    }
}
