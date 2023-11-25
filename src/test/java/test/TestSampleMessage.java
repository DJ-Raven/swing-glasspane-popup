package test;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.popup.component.SimplePopupBorderOption;

import javax.swing.*;
import java.awt.*;

public class TestSampleMessage extends JFrame {

    public TestSampleMessage() {
        GlassPanePopup.install(this);
        MyDrawerBuilder myDrawerBuilder = new MyDrawerBuilder();
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setLayout(new MigLayout(""));
        JButton cmd = new JButton("Show Message");
        cmd.addActionListener(e -> {
            JTextPane txt = new JTextPane();
            txt.setText("Your account has been logged into from a new device. If this was not you, please secure your account immediately by changing your password and enabling two-factor authentication. Thank you.");
            txt.putClientProperty(FlatClientProperties.STYLE, "" +
                    "border:10,25,10,25;" +
                    "[light]foreground:lighten(@foreground,30%);" +
                    "[dark]foreground:darken(@foreground,30%)");
            txt.setEditable(false);
            String actions[] = new String[]{"Cancel", "Ok"};
            GlassPanePopup.showPopup(new SimplePopupBorder(txt, "Sample Message",new SimplePopupBorderOption().useScroll(), actions, (controller, action) -> {
                controller.closePopup();
            }));
        });
        add(cmd);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new TestSampleMessage().setVisible(true));
    }
}
