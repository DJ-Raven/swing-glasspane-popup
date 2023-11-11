package test;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import raven.drawer.Drawer;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {

    public Demo() {
        GlassPanePopup.install(this);
        Drawer.getInstance().setDrawerBuilder(new MyDrawerBuilder());
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
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
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new Demo().setVisible(true));
    }
}
