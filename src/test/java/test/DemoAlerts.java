package test;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import net.miginfocom.swing.MigLayout;
import raven.alerts.SimpleAlerts;
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
            String title = "Data Save Successful";
            String message = "Great news! Your data has been securely saved. It's all set and ready for your use whenever you need it. If you have any questions, just let us know!";
            GlassPanePopup.showPopup(new SimpleAlerts(SimpleAlerts.AlertsType.WARNING, title, message));
        });
        add(cmd);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        EventQueue.invokeLater(() -> new DemoAlerts().setVisible(true));
    }
}
