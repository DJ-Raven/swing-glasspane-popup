package test;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.ColorFunctions;
import com.github.weisj.jsvg.util.ColorUtil;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class DemoAlerts extends JFrame {

    public DemoAlerts() {
        applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GlassPanePopup.install(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setLayout(new MigLayout(""));
        JButton cmd = new JButton("Show Simple Alerts");
        cmd.addActionListener(e -> {
            String title = "Data Save Successful";
            String message = "A bound size is a size that optionally has a lower and/or upper bound. Practically it is a minimum/preferred/maximum size combination but none of the sizes are actually mandatory. If a size is missing (e.g. the preferred) it is null and will be replaced by the most appropriate value. For components this value is the corresponding size (E.g. Component.getPreferredSize() on Swing) and for columns/rows it is the size of the components in the row (see min/pref/max in UnitValue above).\n" +
                    "\n" +
                    "The format is \"min:preferred:max\", however there are shorter versions since for instance it is seldom needed to specify the maximum size.\n" +
                    "\n" +
                    "A single value (E.g. \"10\") sets only the preferred size and is exactly the same as \"null:10:null\" and \":10:\" and \"n:10:n\".\n" +
                    "Two values (E.g. \"10:20\") means minimum and preferred size and is exactly the same as \"10:20:null\" and \"10:20:\" and \"10:20:n\"\n" +
                    "The use a of an exclamation mark (E.g. \"20!\") means that the value should be used for all size types and no colon may then be used in the string. It is the same as \"20:20:20\".\n" +
                    "\n" +
                    "push can be appended to a gap to make that gap \"greedy\" and take any left over space. This means that a gap that has \"push\" will be pushing the components/rows/columns apart, taking as much space as possible for the gap. The gap push is always an addition to a BoundSize. E.g. \"gap rel:push\", \"[][]push[][]\", \"10cm!:push\" or \"10:10:10:push\".\n" +
                    "\n" +
                    "Note! For row/column constraints the minimum, preferred and maximum keywords can be used. A null value is the same thing as any of these constraints, for the indicated position, but they can for instance be used to set the minimum size to the preferred one or the other way around. E.g. \"pref:pref\" or \"min:min:pref\".\n" +
                    "\n" +
                    "AlignKeyword\n" +
                    "For alignment purposes these keywords can be used: t/top, l/left, b/bottom, r/right, lead/leading, trail/trailing and base/baseline. Leading/trailing is dependant on if component orientation is \"left-to-right\" or \"right-to-left\". There is also a keyword \"align label\" or for columns/rows one need only to use \"label\". It will align the component(s), which is normally labels, left, center or right depending on the style guides for the platform. This currently means left justfied on all platforms except OS X which has right justified labels.";
            MessageAlerts.getInstance().showMessage(title, message, MessageAlerts.MessageType.DEFAULT);
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
