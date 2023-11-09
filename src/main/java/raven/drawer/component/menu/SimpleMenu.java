package raven.drawer.component.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class SimpleMenu extends JPanel {

    private final SimpleMenuOption simpleMenuOption;

    private MigLayout menuLayout;

    public SimpleMenu(SimpleMenuOption simpleMenuOption) {
        this.simpleMenuOption = simpleMenuOption;
        System.out.println("Menu ++ "+simpleMenuOption.icons);
        init();
    }

    private void init() {
        menuLayout = new MigLayout("wrap,insets 0,fillx,gap 0", "[fill]");
        setLayout(menuLayout);
        String menus[][] = simpleMenuOption.menus;
        System.out.println(menus);
        if (menus != null) {
            for (int i = 0; i < menus.length; i++) {
                final int index = i;
                String menu[] = menus[index];
                if (menu.length > 0) {
                    String label = checkLabel(menu);
                    if (label != null) {
                        add(createLabel(label, index));
                    } else {
                        if (menu.length == 1) {
                            JButton button = createMenuItem(menu[0], index);
                            add(button);
                        } else {
                            add(createSubmenuItem(menu, index));
                        }
                    }
                }
            }
        }
    }

    protected JButton createMenuItem(String name, int index) {
        JButton button = new JButton(name);
        button.setHorizontalAlignment(JButton.LEADING);
        button.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:0;" +
                "margin:8,20,8,20;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;" +
                "iconTextGap:15");
        return button;
    }

    protected Component createSubmenuItem(String menu[], int index) {
        JPanel panelItem = new SubMenuItem(menu, index);
        return panelItem;
    }

    protected String checkLabel(String menu[]) {
        String label = menu[0];
        if (label.startsWith("~") && label.endsWith("~")) {
            return label.substring(1, label.length() - 1);
        } else {
            return null;
        }
    }

    protected Component createLabel(String name, int index) {
        JLabel label = new JLabel(name);
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,10,8,10;" +
                "[light]foreground:lighten($Label.foreground,30%);" +
                "[dark]foreground:darken($Label.foreground,30%)");
        return label;
    }

    protected class SubMenuItem extends JPanel {

        private SubmenuLayout menuLayout;
        private boolean menuShow;
        private final String menu[];
        private final int index;

        public void setAnimate(float animate) {
            menuLayout.setAnimate(animate);
        }

        public SubMenuItem(String menu[], int index) {
            this.menu = menu;
            this.index = index;
            init();
        }

        private void init() {
            menuLayout = new SubmenuLayout();
            setLayout(menuLayout);
            for (int i = 0; i < menu.length; i++) {
                final int index = i;
                if (i == 0) {
                    JButton button = createMenuItem(menu[index], index);
                    createMainMenuEvent(button);
                    add(button);
                } else {
                    JButton button = createSubMenuItem(menu[index], index);
                    add(button);
                }
            }
        }

        private void createMainMenuEvent(JButton button) {
            button.addActionListener(e -> {
                menuShow = !menuShow;
                new MenuAnimation(this).run(menuShow);
            });
        }


        protected JButton createSubMenuItem(String name, int index) {
            JButton button = new JButton(name);
            button.setHorizontalAlignment(JButton.LEADING);
            button.putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:0;" +
                    "margin:7,30,7,20;" +
                    "borderWidth:0;" +
                    "focusWidth:0;" +
                    "innerFocusWidth:0;" +
                    "background:null");
            return button;
        }

        @Override
        protected void paintChildren(Graphics g) {
            super.paintChildren(g);
            if (getComponentCount() > 0) {
                int menuHeight = getComponent(0).getHeight();
                int width = getWidth();
                int height = getHeight();
                Graphics2D g2 = (Graphics2D) g.create();
                FlatUIUtils.setRenderingHints(g2);
                //  Create submenu line
                int last = getLastLocation();
                int round = UIScale.scale(8);
                int gap = UIScale.scale(20);
                Path2D.Double p = new Path2D.Double();
                int x = gap;
                p.moveTo(x, menuHeight);
                p.lineTo(x, last - round);
                int count = getComponentCount();
                for (int i = 1; i < count; i++) {
                    Component com = getComponent(i);
                    int y = com.getY() + (com.getHeight() / 2);
                    p.append(createCurve(round, x, y, true), false);
                }
                g2.setColor(UIManager.getColor("Component.borderColor"));
                g2.setStroke(new BasicStroke(UIScale.scale(1f)));
                g2.draw(p);

                //  Create arrow
                paintArrow(g2, width, menuHeight, menuLayout.getAnimate());
                g2.dispose();
            }
        }

        private int getLastLocation() {
            Component com = getComponent(getComponentCount() - 1);
            return com.getY() + com.getHeight() / 2;
        }

        private Shape createCurve(int round, int x, int y, boolean ltr) {
            Path2D p2 = new Path2D.Double();
            p2.moveTo(x, y - round);
            p2.curveTo(x, y - round, x, y, x + (ltr ? round : -round), y);
            return p2;
        }

        private void paintArrow(Graphics2D g2, int width, int height, float animate) {
            int arrowWidth = UIScale.scale(10);
            int arrowHeight = UIScale.scale(4);
            int gap = UIScale.scale(15);
            int x = width - arrowWidth - gap;
            int y = (height - arrowHeight) / 2;
            Path2D p = new Path2D.Double();
            p.moveTo(0, animate * arrowHeight);
            p.lineTo(arrowWidth / 2, (1f - animate) * arrowHeight);
            p.lineTo(arrowWidth, animate * arrowHeight);
            g2.translate(x, y);
            g2.draw(p);
        }

        protected class SubmenuLayout implements LayoutManager {

            private float animate;

            public void setAnimate(float animate) {
                this.animate = animate;
                revalidate();
            }

            public float getAnimate() {
                return animate;
            }


            @Override
            public void addLayoutComponent(String name, Component comp) {

            }

            @Override
            public void removeLayoutComponent(Component comp) {

            }

            @Override
            public Dimension preferredLayoutSize(Container parent) {
                synchronized (parent.getTreeLock()) {
                    Insets insets = parent.getInsets();
                    int width = insets.left + insets.right;
                    int height = insets.top + insets.bottom;
                    int count = parent.getComponentCount();
                    int first = -1;
                    for (int i = 0; i < count; i++) {
                        Component com = parent.getComponent(i);
                        if (com.isVisible()) {
                            if (first == -1) {
                                first = com.getPreferredSize().height;
                            }
                            height += com.getPreferredSize().height;
                        }
                    }
                    int space = height - first;
                    height = (int) (first + space * animate);
                    return new Dimension(width, height);
                }
            }

            @Override
            public Dimension minimumLayoutSize(Container parent) {
                synchronized (parent.getTreeLock()) {
                    return new Dimension(0, 0);
                }
            }

            @Override
            public void layoutContainer(Container parent) {
                synchronized (parent.getTreeLock()) {
                    Insets insets = parent.getInsets();
                    int x = insets.left;
                    int y = insets.top;
                    int width = parent.getWidth() - (insets.left + insets.right);
                    int count = parent.getComponentCount();
                    for (int i = 0; i < count; i++) {
                        Component com = parent.getComponent(i);
                        if (com.isVisible()) {
                            int h = com.getPreferredSize().height;
                            com.setBounds(x, y, width, h);
                            y += h;
                        }
                    }
                }
            }
        }
    }
}
