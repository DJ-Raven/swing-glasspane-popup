package raven.swing;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.Animator;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class AnimateIcon extends FlatSVGIcon {

    private Component component;

    private Animator animator;
    private float animate;

    public AnimateIcon(String name, float scale) {
        super(name, scale);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (c != component) {
            component = c;
        }
        if (animate > 0) {
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform tran = g2.getTransform();
            float scale = 1 + animate * 0.5f;
            float rotate = animate * -0.5f;
            int centerX = x + getIconWidth() / 2;
            int centerY = y + getIconHeight() / 2;
            g2.translate(centerX, centerY);
            g2.rotate(rotate);
            g2.scale(scale, scale);
            g2.translate(-centerX, -centerY);
            super.paintIcon(c, g, x, y);
            g2.setTransform(tran);
        } else {
            super.paintIcon(c, g, x, y);
        }
    }

    public void setAnimate(float f) {
        float v;
        if (f < 0.5f) {
            v = f * 2;
        } else {
            v = 2 - (f * 2);
        }
        this.animate = v;
        if (component != null) {
            component.repaint();
        }
    }

    public boolean animate() {
        boolean act = false;
        if (component != null) {
            if (animator == null) {
                animator = new Animator(350, (float f) -> {
                    setAnimate(f);
                });
            }
            if (!animator.isRunning()) {
                animator.start();
                act = true;
            }
        }
        return act;
    }
}
