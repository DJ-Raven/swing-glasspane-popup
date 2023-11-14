package raven.alerts;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import raven.popup.GlassPanePopup;
import raven.popup.component.GlassPaneChild;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class SimpleAlerts extends GlassPaneChild {

    public SimpleAlerts() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fillx,insets 15 0 15 0", "[fill,400]"));
        String title = "Data Save Successful";
        String message = "Great news! Your data has been securely saved. It's all set and ready for your use whenever you need it. If you have any questions, just let us know!";
        add(new PanelEffect(title, message));
    }

    @Override
    public int getRoundBorder() {
        return 20;
    }

    protected class PanelEffect extends JPanel {

        private Effect effects[];
        private float animate;
        private Animator animator;

        private JLabel labelIcon;
        private JLabel labelTitle;
        private JTextPane textPane;

        protected void start() {
            if (animator == null) {
                animator = new Animator(2000, new Animator.TimingTarget() {
                    @Override
                    public void timingEvent(float v) {
                        animate = v;
                        repaint();
                    }
                });
                animator.setInterpolator(CubicBezierEasing.EASE);
            }
            if (animator.isRunning()) {
                animator.stop();
            }
            animator.start();
        }

        private void createEffect() {
            effects = new Effect[30];
            for (int i = 0; i < effects.length; i++) {
                effects[i] = new Effect();
            }
            start();
        }


        public PanelEffect(String title, String message) {
            setLayout(new MigLayout("fillx,wrap,insets 0,center", "[fill,center]", "[]10[][][]20[]20"));
            labelIcon = new JLabel(new FlatSVGIcon("raven/alerts/icon/success.svg", 4f));
            labelTitle = new JLabel(title, JLabel.CENTER);
            textPane = new JTextPane();
            textPane.setOpaque(false);
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            textPane.setEditable(false);
            textPane.setText(message);
            textPane.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:5,25,5,25");
            labelTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                    "font:bold +5;" +
                    "foreground:#61BE79");
            JPanel panelTitle = new JPanel(new MigLayout("insets 2 25 2 25,fill"));
            panelTitle.setOpaque(false);
            panelTitle.add(createCloseButton(), "trailing");
            add(panelTitle);
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
            cmd.addActionListener(e -> {
                createEffect();
            });
            cmd.putClientProperty(FlatClientProperties.STYLE, "" +
                    "borderWidth:0;" +
                    "focusWidth:0;" +
                    "innerFocusWidth:0;" +
                    "arc:10;" +
                    "font:bold +2;" +
                    "margin:5,50,5,50;" +
                    "background:#61BE79;" +
                    "foreground:#F0F0F0;" +
                    "arc:999");
            add(cmd, "grow 0");
        }

        @Override
        protected void paintChildren(Graphics g) {
            if (effects != null && effects.length > 0) {
                Graphics2D g2 = (Graphics2D) g.create();
                float s = UIScale.getUserScaleFactor();

                FlatUIUtils.setRenderingHints(g2);
                int width = getWidth();
                int height = getHeight();
                int x = labelIcon.getX() + labelIcon.getWidth() / 2;
                int y = labelIcon.getY() + labelIcon.getHeight() / 2;
                float size = Math.min(width, height) / 2;

                float l = size * animate;
                float remove = 0.7f;
                g2.translate(x, y);
                g2.scale(s, s);
                for (int i = 0; i < effects.length; i++) {
                    Effect effect = effects[i];
                    double sp = effect.speed * 0.05f;
                    double xx = Math.cos(Math.toRadians(effect.direction)) * l * sp;
                    double yy = Math.sin(Math.toRadians(effect.direction)) * l * sp;
                    AffineTransform oldTran = g2.getTransform();
                    g2.translate(xx, yy);
                    g2.rotate(Math.toRadians(animate * 360), 5, 5);
                    g2.setColor(effect.color);
                    if (animate >= remove) {
                        float f = (animate - remove) / (1f - remove);
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - f));
                    }
                    g2.fill(effect.shape);
                    g2.setTransform(oldTran);
                }
                g2.dispose();
            }
            super.paintChildren(g);
        }
    }

    protected class Effect {

        protected Shape shape;
        protected Color color;
        protected float direction;
        protected float speed;

        public Effect() {
            Random random = new Random();
            color = effectColors[random.nextInt(effectColors.length)];
            shape = ShapeGenerator.randomShape(random.nextInt(3));
            direction = random.nextInt(360);
            speed = random.nextInt(25) + 5;
        }
    }

    protected static final Color effectColors[] = {
            Color.decode("#8DBF13"), Color.decode("#13BF86"),
            Color.decode("#FF5733"), Color.decode("#E6A801"),
            Color.decode("#FFC300"), Color.decode("#FF5733"),
            Color.decode("#C70039"), Color.decode("#900C3F"),
            Color.decode("#581845"), Color.decode("#4A235A")
    };
}
