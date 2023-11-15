package raven.popup;

import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import net.miginfocom.swing.MigLayout;
import raven.popup.component.ComponentLayer;
import raven.popup.component.GlassPaneChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class GlassPopup extends JComponent {
    private final GlassPanePopup parent;
    private final ComponentLayer componentLayer;
    private final Option option;
    private Animator animator;
    private MigLayout layout;
    private float animate;
    private boolean show;
    private boolean mouseHover;

    public GlassPopup(GlassPanePopup parent, GlassPaneChild component, Option option) {
        this.parent = parent;
        this.option = option;
        this.componentLayer = new ComponentLayer(component);
        init();
        initAnimator();
    }

    private void init() {
        layout = new MigLayout();
        initOption();
        setLayout(layout);
        add(componentLayer, option.getLayout(parent.layerPane, 0));
    }

    private void initOption() {
        if (option.closeWhenClickOutside()) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseHover = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseHover = false;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (mouseHover && SwingUtilities.isLeftMouseButton(e)) {
                        setShowPopup(false);
                    }
                }
            });
        } else if (option.blockBackground()) {
            addMouseListener(new MouseAdapter() {
            });
        }
        if (option.closeWhenPressedEsc()) {
            registerKeyboardAction(e -> setShowPopup(false), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        }
    }

    private void initAnimator() {
        animator = new Animator(option.duration(), new Animator.TimingTarget() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }
                float f = format(animate);
                option.setAnimate(f);
                String lc = option.getLayout(parent.layerPane, f);
                if (lc != null) {
                    layout.setComponentConstraints(componentLayer, lc);
                }
                repaint();
                revalidate();
            }

            @Override
            public void begin() {
                componentLayer.showSnapshot();
                if (option.useSnapshot()) {
                    parent.windowSnapshots.createSnapshot();
                    parent.contentPane.setVisible(false);
                }
            }

            @Override
            public void end() {
                componentLayer.hideSnapshot();
                if (show) {
                    componentLayer.getComponent().popupShow();
                } else {
                    parent.removePopup(GlassPopup.this);
                }
                if (option.useSnapshot()) {
                    parent.contentPane.setVisible(true);
                    parent.windowSnapshots.removeSnapshot();
                }
            }
        });
        animator.setInterpolator(CubicBezierEasing.EASE_IN_OUT);
    }

    public void setShowPopup(boolean show) {
        if (this.show != show) {
            if (!animator.isRunning()) {
                this.show = show;
                animator.start();
                if (show) {
                    setFocusCycleRoot(true);
                } else {
                    uninstallOption();
                }
            }
        }
    }

    private void uninstallOption() {
        if (getMouseListeners().length != 0) {
            removeMouseListener(getMouseListeners()[0]);
        }
        if (option.closeWhenPressedEsc()) {
            unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.SrcOver.derive(animate * option.opacity()));
        g2.setColor(option.background());
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.setComposite(AlphaComposite.SrcOver.derive(animate));
        super.paintComponent(g);
    }

    private float format(float v) {
        int a = Math.round(v * 100);
        return a / 100f;
    }

    protected Component getComponent() {
        return componentLayer.getComponent();
    }

    protected ComponentLayer getComponentLayer() {
        return componentLayer;
    }
}
