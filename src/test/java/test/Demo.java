package test;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import net.miginfocom.swing.MigLayout;
import raven.drawer.Drawer;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.menu.SimpleDrawerBuilder;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {
    public Demo() {
        GlassPanePopup.install(this);
        Drawer.getInstance().setDrawerBuilder(new MyDrawerBuilder());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setLayout(new MigLayout("center"));
        JButton cmd = new JButton("Show Drawer");
        cmd.addActionListener(e -> {
            Drawer.getInstance().showDrawer();
        });
        add(cmd);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        EventQueue.invokeLater(() -> new Demo().setVisible(true));
    }
}
