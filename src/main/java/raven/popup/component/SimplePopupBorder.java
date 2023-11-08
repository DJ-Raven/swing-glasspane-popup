package raven.popup.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class SimplePopupBorder extends GlassPaneChild {

    private final Component component;
    private JPanel panelTitle;
    private final String title;
    private final String[] action;
    private SimplePopupBorderOption option;

    public SimplePopupBorder(Component component, String title, SimplePopupBorderOption option) {
        this(component, title, option, null, null);
    }

    public SimplePopupBorder(Component component, String title) {
        this(component, title, null, null);
    }

    public SimplePopupBorder(Component component, String title, String[] action, PopupCallbackAction callbackAction) {
        this(component, title, new SimplePopupBorderOption(), action, callbackAction);
    }

    public SimplePopupBorder(Component component, String title, SimplePopupBorderOption option, String[] action, PopupCallbackAction callbackAction) {
        this.component = component;
        this.title = title;
        this.option = option;
        this.action = action;
        this.callbackAction = callbackAction;
        if (component instanceof GlassPaneChild) {
            ((GlassPaneChild) component).callbackAction = callbackAction;
        }
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fillx,insets 15 0 15 0", "[fill," + option.width + "]"));
        panelTitle = new JPanel(new MigLayout("insets 2 25 2 25,fill"));
        if (title != null) {
            panelTitle.add(createTitle(title), "push");
        }
        panelTitle.add(createCloseButton(), "trailing");
        add(panelTitle);
        if (option.useScroll) {
            JScrollPane scrollPane = new JScrollPane(component);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            applyScrollStyle(scrollPane.getVerticalScrollBar());
            applyScrollStyle(scrollPane.getHorizontalScrollBar());
            add(scrollPane);
        } else {
            add(component);
        }
        if (action != null) {
            add(createActionButton(action, callbackAction));
        }
    }

    private void applyScrollStyle(JScrollBar scrollBar) {
        scrollBar.setUnitIncrement(10);
        scrollBar.putClientProperty(FlatClientProperties.STYLE, "" +
                "thumbInsets:0,0,0,3;" +
                "trackInsets:0,0,0,3;");
    }

    protected Component createTitle(String title) {
        JLabel lbTitle = new JLabel(title);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:+4");
        return lbTitle;
    }

    protected void addBackButton() {
        panelTitle.add(createBackButton(), 0);
    }

    protected Component createCloseButton() {
        JButton cmdClose = new JButton(new FlatSVGIcon("raven/popup/icon/close.svg", 0.8f));
        cmdClose.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null");
        applyCloseButtonEvent(cmdClose);
        return cmdClose;
    }

    protected Component createBackButton() {
        JButton cmdBack = new JButton(new FlatSVGIcon("raven/popup/icon/back.svg", 0.8f));
        cmdBack.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null");
        applyBackButtonEvent(cmdBack);
        return cmdBack;
    }

    protected void applyCloseButtonEvent(JButton button) {
        button.addActionListener(e -> {
            if (callbackAction == null) {
                GlassPanePopup.closePopup(this);
                return;
            }
            PopupController action = createController();
            callbackAction.action(action, PopupCallbackAction.CLOSE);
            if (!action.getConsume()) {
                GlassPanePopup.closePopup(this);
            }
        });
    }

    protected void applyBackButtonEvent(JButton button) {
        button.addActionListener(e -> {
            GlassPanePopup.pop(this);
        });
    }

    protected Component createActionButton(String[] action, PopupCallbackAction callbackAction) {
        JPanel panel = new JPanel(new MigLayout("insets 2 25 2 25,trailing"));
        for (int i = 0; i < action.length; i++) {
            panel.add(getActionButton(action[i], i, callbackAction));
        }
        return panel;
    }

    private JButton getActionButton(String name, int index, PopupCallbackAction callbackAction) {
        JButton button = new JButton(name);
        button.setFocusable(false);
        button.addActionListener(e -> {
            callbackAction.action(createController(), index);
        });
        button.putClientProperty(FlatClientProperties.STYLE, "" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;" +
                "font:+1");
        return button;
    }

    @Override
    public void onPush() {
        addBackButton();
    }

    @Override
    public int getRoundBorder() {
        return option.roundBorder;
    }
}
