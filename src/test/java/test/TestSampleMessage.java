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
            String actions[] = new String[]{"Cancel", "Next"};
            GlassPanePopup.showPopup(new SimplePopupBorder(txt, "Sample Message", new SimplePopupBorderOption().useScroll(), actions, (controller, action) -> {
                if (action == 1) {
                    String actions1[] = new String[]{"Back", "OK"};
                    GlassPanePopup.push(new SimplePopupBorder(createSample(), "Test", new SimplePopupBorderOption().useScroll(),actions1, (controller1, action1) -> {
                        if (action1 == 0) {
                            GlassPanePopup.pop("pop");
                        }
                    }), "pop");
                } else {
                    controller.closePopup();
                }
            }), "pop");
        });
        add(cmd);
    }

    private Component createSample() {
        JPanel panel = new JPanel(new MigLayout("fill", "fill", ""));
        JTextPane txt = new JTextPane();
        txt.setText("Apple\n" +
                "Banana\n" +
                "Orange\n" +
                "Grapes\n" +
                "Watermelon\n" +
                "Pineapple\n" +
                "Strawberry\n" +
                "Blueberry\n" +
                "Raspberry\n" +
                "Mango\n" +
                "Kiwi\n" +
                "Papaya\n" +
                "Peach\n" +
                "Pear\n" +
                "Plum\n" +
                "Cherry\n" +
                "Apricot\n" +
                "Coconut\n" +
                "Avocado\n" +
                "Lemon\n" +
                "Lime\n" +
                "Grapefruit\n" +
                "Cantaloupe\n" +
                "Honeydew\n" +
                "Fig\n" +
                "Dates\n" +
                "Pomegranate\n" +
                "Cranberry\n" +
                "Blackberry\n" +
                "Goji berries\n" +
                "Passionfruit\n" +
                "Dragonfruit\n" +
                "Starfruit\n" +
                "Lychee\n" +
                "Guava\n" +
                "Persimmon\n" +
                "Jackfruit\n" +
                "Ackee\n" +
                "Breadfruit\n" +
                "Plantain\n" +
                "Pine nuts\n" +
                "Almonds\n" +
                "Cashews\n" +
                "Walnuts\n" +
                "Pistachios\n" +
                "Pecans\n" +
                "Hazelnuts\n" +
                "Macadamia nuts\n" +
                "Chestnuts\n" +
                "Brazil nuts\n" +
                "Peanuts\n" +
                "Sunflower seeds\n" +
                "Pumpkin seeds\n" +
                "Chia seeds\n" +
                "Flaxseeds\n" +
                "Sesame seeds\n" +
                "Quinoa\n" +
                "Bulgur\n" +
                "Farro\n" +
                "Barley\n" +
                "Millet\n" +
                "Buckwheat\n" +
                "Oats\n" +
                "Rice\n" +
                "Couscous\n" +
                "Wheatberries\n" +
                "Spelt\n" +
                "Teff\n" +
                "Rye\n" +
                "Corn\n" +
                "Lentils\n" +
                "Chickpeas\n" +
                "Black beans\n" +
                "Kidney beans\n" +
                "Navy beans");
        txt.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:10,25,10,25;" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        txt.setEditable(false);
        panel.add(txt);
        return panel;
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new TestSampleMessage().setVisible(true));
    }
}
