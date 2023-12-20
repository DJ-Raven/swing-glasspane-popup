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
                    GlassPanePopup.push(new SimplePopupBorder(createSample(), "Test", actions1, (controller1, action1) -> {
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
        txt.setText("al/align alignx [aligny] or aligny/ay aligny or aligny/ax alignx\n" +
                "Specifies the alignment for the laid out components as a group. If the total bounds of all laid out components does not fill the entire container the align value is used to position the components within the container without changing their relative positions. The alignment can be specified as a UnitValue or AlignKeyword. See above. If an AlignKeyword is used the \"align\" keyword can be omitted.\n" +
                "Example: \"align 50% 50%\" or \"aligny top\" or \"alignx leading\" or \"align 100px\" or \"top, left\" or \"aligny baseline\"\n" +
                "\n" +
                "ltr/lefttoright or rtl/righttoleft\n" +
                "Overrides the container's ComponentOrientation property for this layout. Normally this value is dependant on the Locale that the application is running. This constraint overrides that value.\n" +
                "Example: \"ltr\" or \"lefttoright\" or \"rtl\"\n" +
                "\n" +
                "ttb/toptobottom or btt/bottomtotop\n" +
                "Specifies if the components should be added in the grid bottom-to-top or top-to-bottom. This value is not picked up from the container and is top-to-bottom by default.\n" +
                "Example: \"ttb\" or \"toptobottom\" or \"btt\"\n" +
                "\n" +
                "hidemode\n" +
                "Sets the default hide mode for the layout. This hide mode can be overridden by the component constraint. The hide mode specified how the layout manager should handle a component that isn't visible. The modes are:\n" +
                "0 - Default. Means that invisible components will be handled exactly as if they were visible.\n" +
                "1 - The size of an invisible component will be set to 0, 0.\n" +
                "2 - The size of an invisible component will be set to 0, 0 and the gaps will also be set to 0 around it.\n" +
                "3 - Invisible components will not participate in the layout at all and it will for instance not take up a grid cell.\n" +
                "Example: \"hidemode 1\"");
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
