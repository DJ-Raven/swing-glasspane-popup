package raven.popup.component;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class ComponentLayer extends JPanel {

    private GlassPaneChild component;
    private GlassPaneChild nextComponent;
    private Animator animator;
    private float animate;
    private boolean showSnapshot;
    private boolean simpleSnapshot;
    private Image image;

    private Image nextImage;
    private Stack<GlassPaneChild> componentStack;
    private boolean push;

    private void pushStack(GlassPaneChild component) {
        if (componentStack == null) {
            componentStack = new Stack<>();
        }
        componentStack.push(component);
    }

    public ComponentLayer(GlassPaneChild component) {
        this.component = component;
        init();
    }

    private void init() {
        MigLayout layout = new MigLayout("insets 0,fill", "fill", "fill");
        setLayout(layout);
        if (component.getRoundBorder() > 0) {
            setOpaque(false);
            component.setOpaque(false);
        }
        add(component);
        component.setVisible(false);
    }

    private void initAnimator() {
        animator = new Animator(350, new Animator.TimingTarget() {

            @Override
            public void timingEvent(float v) {
                animate = v;
                repaint();
            }

            @Override
            public void begin() {
                nextImage = ComponentImageUtils.createImage(nextComponent);
            }

            @Override
            public void end() {
                showSnapshot = false;
                component = nextComponent;
                component.popupShow();
                nextComponent = null;
                animate = 0;
                component.setVisible(true);
                if (nextImage != null) {
                    nextImage.flush();
                }
            }
        });
        animator.setInterpolator(CubicBezierEasing.EASE);
    }

    private void startAnimate() {
        if (animator == null) {
            initAnimator();
        }
        if (animator.isRunning()) {
            animator.stop();
        }
        animate = 0;
        animator.start();
    }

    public void pushComponent(GlassPaneChild component) {
        component.onPush();
        pushStack(this.component);
        push = true;
        showComponent(component);
    }


    public void popComponent() {
        if (!componentStack.isEmpty()) {
            GlassPaneChild component = componentStack.pop();
            component.onPop();
            push = false;
            showComponent(component);
        }
    }

    private void showComponent(GlassPaneChild component) {
        showSnapshot = true;
        this.nextComponent = component;
        image = ComponentImageUtils.createImage(this.component);
        if (component.getRoundBorder() > 0) {
            setOpaque(false);
            component.setOpaque(false);
        }
        component.setVisible(false);
        remove(this.component);
        add(component);
        revalidate();
        startAnimate();
    }

    public void showSnapshot() {
        if (animator != null && animator.isRunning()) {
            animator.stop();
        }
        simpleSnapshot = true;
        doLayout();
        image = ComponentImageUtils.createImage(component, component.getRoundBorder());
        component.setVisible(false);
    }

    public void hideSnapshot() {
        showSnapshot = false;
        simpleSnapshot = false;
        component.setVisible(true);
        if (image != null) {
            image.flush();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque() == false && component.getRoundBorder() > 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(getBackground());
            int arc = UIScale.scale(component.getRoundBorder());
            FlatUIUtils.paintComponentBackground(g2, 0, 0, getWidth(), getHeight(), 0, arc);
            g2.dispose();
        }
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        if (simpleSnapshot) {
            g.drawImage(image, 0, 0, null);
        } else if (showSnapshot) {
            int width = getWidth();
            int height = getHeight();
            if (width > 0 && height > 0) {
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = bufferedImage.createGraphics();
                FlatUIUtils.setRenderingHints(g2);
                int arc = UIScale.scale(component.getRoundBorder());
                g2.setColor(getBackground());
                FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, 0, arc);
                if (image != null) {
                    int w = image.getWidth(null);
                    double x;
                    if (push) {
                        x = -w * animate;
                    } else {
                        x = w * animate;
                    }
                    g2.setComposite(AlphaComposite.SrcAtop.derive(1f - animate));
                    g2.drawImage(image, (int) x, 0, null);
                }
                if (nextImage != null) {
                    int w = nextImage.getWidth(null);
                    double x;
                    if (push) {
                        x = getWidth() - (w * animate);
                    } else {
                        x = -getWidth() + (w * animate);
                    }
                    g2.setComposite(AlphaComposite.SrcAtop.derive(animate));
                    g2.drawImage(nextImage, (int) x, 0, null);
                }
                g2.dispose();
                g.drawImage(bufferedImage, 0, 0, null);
            }
        } else {
            super.paint(g);
        }
    }

    public GlassPaneChild getComponent() {
        return component;
    }
}
