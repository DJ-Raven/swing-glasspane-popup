package test;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import raven.alerts.SimpleAlerts;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class DemoAlerts extends JFrame {

    public DemoAlerts() {
        GlassPanePopup.install(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setLayout(new MigLayout(""));
        JButton cmd = new JButton("Show Drawer");
        cmd.addActionListener(e -> {
            GlassPanePopup.showPopup(new SimpleAlerts());
        });
        add(cmd);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new DemoAlerts().setVisible(true));
    }
}
