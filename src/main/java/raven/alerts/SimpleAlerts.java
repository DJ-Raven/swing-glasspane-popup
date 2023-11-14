package raven.alerts;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import raven.popup.component.GlassPaneChild;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class SimpleAlerts extends GlassPaneChild {

    public SimpleAlerts() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fillx,insets 15 0 15 0", "[fill,400]"));
        add(new PanelEffect());
    }

    @Override
    public int getRoundBorder() {
        return 20;
    }

    protected class PanelEffect extends JPanel {

        private Effect effects[];
        private float animate;
        private Animator animator;

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


        public PanelEffect() {
            setLayout(new MigLayout("height 300"));
            JButton cmd = new JButton("Test");
            cmd.addActionListener(e -> {
                createEffect();
            });
            add(cmd);
        }

        @Override
        protected void paintChildren(Graphics g) {
            if (effects != null && effects.length > 0) {
                Graphics2D g2 = (Graphics2D) g.create();
                float s = UIScale.getUserScaleFactor();

                FlatUIUtils.setRenderingHints(g2);
                int width = getWidth();
                int height = getHeight();
                int x = width / 2;
                int y = height / 2;
                float size = Math.min(width, height) / 2;

                float l = size * animate;
                float remove = 0.7f;
                g2.translate(x, y);   g2.scale(s, s);
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
            speed = random.nextInt(25)+5;
        }
    }

    protected static final Color effectColors[] = {Color.decode("#8DBF13"), Color.decode("#13BF86"), Color.decode("#134FBF"), Color.decode("#C144D5"), Color.decode("#D58844"), Color.decode("#D54B44")};
}
