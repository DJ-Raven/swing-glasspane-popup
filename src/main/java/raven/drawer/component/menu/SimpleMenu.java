package raven.drawer.component.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.UIScale;
import raven.utils.FlatLafStyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class SimpleMenu extends JPanel {

    private final SimpleMenuOption simpleMenuOption;

    public SimpleMenu(SimpleMenuOption simpleMenuOption) {
        this.simpleMenuOption = simpleMenuOption;
        init();
    }

    public void rebuildMenu() {
        removeAll();
        buildMenu();
    }

    private void init() {
        setLayout(new MenuLayout());
        if (simpleMenuOption.simpleMenuStyle != null) {
            simpleMenuOption.simpleMenuStyle.styleMenu(this);
        }
        buildMenu();
    }

    private void buildMenu() {
        String menus[][] = simpleMenuOption.menus;
        if (menus != null) {
            int index = 0;
            int validationIndex = -1;
            for (int i = 0; i < menus.length; i++) {
                String menu[] = menus[i];
                if (menu.length > 0) {
                    String label = checkLabel(menu);
                    if (label != null) {
                        if (checkLabelValidation(i, index)) {
                            add(createLabel(label));
                        }
                    } else {
                        boolean validation = simpleMenuOption.menuValidation.menuValidation(++validationIndex, 0);
                        if (validation) {
                            if (menu.length == 1) {
                                JButton button = createMenuItem(menu[0], index);
                                applyMenuEvent(button, index, 0);
                                add(button);
                            } else {
                                add(createSubmenuItem(menu, index, validationIndex));
                            }
                        }
                        if (validation || simpleMenuOption.menuValidation.keepMenuValidationIndex) {
                            index++;
                        }
                    }
                }
            }
        }
    }

    private String getBasePath() {
        if (simpleMenuOption.baseIconPath == null) {
            return "";
        }
        if (simpleMenuOption.baseIconPath.endsWith("/")) {
            return simpleMenuOption.baseIconPath;
        } else {
            return simpleMenuOption.baseIconPath + "/";
        }
    }

    protected JButton createMenuItem(String name, int index) {
        JButton button = new JButton(name);
        if (simpleMenuOption.icons != null && index < simpleMenuOption.icons.length) {
            String path = getBasePath();
            Icon icon = simpleMenuOption.buildMenuIcon(path + simpleMenuOption.icons[index], simpleMenuOption.iconScale);
            if (icon != null) {
                button.setIcon(icon);
            }
        }
        button.setHorizontalAlignment(JButton.LEADING);
        if (simpleMenuOption.simpleMenuStyle != null) {
            simpleMenuOption.simpleMenuStyle.styleMenuItem(button, index);
        }
        FlatLafStyleUtils.appendStyleIfAbsent(button, "" +
                "arc:0;" +
                "margin:6,20,6,20;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;" +
                "iconTextGap:5;");
        return button;
    }

    protected void applyMenuEvent(JButton button, int index, int subIndex) {
        button.addActionListener(e -> {
            MenuAction action = runEvent(index, subIndex);
            if (action != null) {
                //  Next update on action menu selected
            }
        });
    }

    private MenuAction runEvent(int index, int subIndex) {
        if (!simpleMenuOption.events.isEmpty()) {
            MenuAction action = new MenuAction();
            if (simpleMenuOption.menuItemAutoSelect) {
                action.selected();
            }
            for (MenuEvent event : simpleMenuOption.events) {
                event.selected(action, index, subIndex);
            }
            return action;
        }
        return null;
    }

    protected Component createSubmenuItem(String menu[], int index, int validationIndex) {
        JPanel panelItem = new SubMenuItem(menu, index, validationIndex);
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

    protected boolean checkLabelValidation(int labelIndex, int menuIndex) {
        if (simpleMenuOption.menuValidation.labelValidation(labelIndex)) {
            if (simpleMenuOption.menuValidation.removeLabelWhenEmptyMenu) {
                boolean fondMenu = false;
                for (int i = labelIndex + 1; i < simpleMenuOption.menus.length; i++) {
                    String label = checkLabel(simpleMenuOption.menus[i]);
                    if (label == null) {
                        if (simpleMenuOption.menuValidation.menuValidation(menuIndex, 0)) {
                            fondMenu = true;
                            break;
                        }
                    } else {
                        break;
                    }
                    menuIndex++;
                }
                return fondMenu;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    protected Component createLabel(String name) {
        JLabel label = new JLabel(name);
        if (simpleMenuOption.simpleMenuStyle != null) {
            simpleMenuOption.simpleMenuStyle.styleLabel(label);
        }
        FlatLafStyleUtils.appendStyleIfAbsent(label, "" +
                "border:8,10,8,10;" +
                "[light]foreground:lighten($Label.foreground,30%);" +
                "[dark]foreground:darken($Label.foreground,30%)");
        return label;
    }

    public SimpleMenuOption getSimpleMenuOption() {
        return simpleMenuOption;
    }

    protected class SubMenuItem extends JPanel {

        private SubmenuLayout menuLayout;
        private boolean menuShow;
        private final String menu[];
        private final int index;
        private final int validationIndex;
        private int iconWidth;

        public void setAnimate(float animate) {
            menuLayout.setAnimate(animate);
        }

        public SubMenuItem(String menu[], int index, int validationIndex) {
            this.menu = menu;
            this.index = index;
            this.validationIndex = validationIndex;
            init();
        }

        private void init() {
            menuLayout = new SubmenuLayout();
            setLayout(menuLayout);
            putClientProperty(FlatClientProperties.STYLE, "" +
                    "background:null");
            iconWidth = 22;
            int index = 0;
            int validationIndex = -1;
            for (int i = 0; i < menu.length; i++) {
                boolean validation = simpleMenuOption.menuValidation.menuValidation(this.validationIndex, ++validationIndex);
                if (validation) {
                    if (i == 0) {
                        JButton button = createMenuItem(menu[i], this.index);
                        if (button.getIcon() != null) {
                            iconWidth = UIScale.unscale(button.getIcon().getIconWidth());
                        }
                        createMainMenuEvent(button);
                        applyMenuEvent(button, this.index, index);
                        add(button);
                    } else {
                        JButton button = createSubMenuItem(menu[i], index, iconWidth);
                        applyMenuEvent(button, this.index, index);
                        add(button);
                    }
                }
                if (validation || simpleMenuOption.menuValidation.keepMenuValidationIndex) {
                    index++;
                }
            }
        }

        private void createMainMenuEvent(JButton button) {
            button.addActionListener(e -> {
                menuShow = !menuShow;
                new MenuAnimation(this).run(menuShow);
            });
        }


        protected JButton createSubMenuItem(String name, int index, int gap) {
            JButton button = new JButton(name);
            button.setHorizontalAlignment(JButton.LEADING);
            if (simpleMenuOption.simpleMenuStyle != null) {
                simpleMenuOption.simpleMenuStyle.styleSubMenuItem(button, this.index, index);
            }
            FlatLafStyleUtils.appendStyleIfAbsent(button, "" +
                    "arc:0;" +
                    "margin:7," + (gap + 25) + ",7," + (gap + 25) + ";" +
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
                boolean ltr = getComponentOrientation().isLeftToRight();
                Color foreground = getComponent(0).getForeground();
                int menuHeight = getComponent(0).getHeight();
                int width = getWidth();
                int height = getHeight();
                Graphics2D g2 = (Graphics2D) g.create();
                FlatUIUtils.setRenderingHints(g2);
                //  Create submenu line
                int last = getLastLocation();
                int round = UIScale.scale(8);
                int gap = UIScale.scale(20 + (iconWidth / 2));
                Path2D.Double p = new Path2D.Double();
                int x = ltr ? gap : width - gap;
                p.moveTo(x, menuHeight);
                p.lineTo(x, last - round);
                int count = getComponentCount();
                for (int i = 1; i < count; i++) {
                    Component com = getComponent(i);
                    int y = com.getY() + (com.getHeight() / 2);
                    p.append(createCurve(round, x, y, ltr), false);
                }
                Color color = ColorFunctions.mix(getBackground(), foreground, 0.7f);
                g2.setColor(color);
                g2.setStroke(new BasicStroke(UIScale.scale(1f)));
                g2.draw(p);
                //  Create arrow
                paintArrow(g2, width, menuHeight, menuLayout.getAnimate(), ltr);
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

        private void paintArrow(Graphics2D g2, int width, int height, float animate, boolean ltr) {
            int arrowWidth = UIScale.scale(10);
            int arrowHeight = UIScale.scale(4);
            int gap = UIScale.scale(15);
            int x = ltr ? (width - arrowWidth - gap) : gap;
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
