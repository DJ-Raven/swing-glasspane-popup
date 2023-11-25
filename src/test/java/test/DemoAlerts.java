package test;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class DemoAlerts extends JFrame {

    public DemoAlerts() {
        //applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        GlassPanePopup.install(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setLayout(new MigLayout(""));
        JButton cmd = new JButton("Show Simple Alerts");
        cmd.addActionListener(e -> {
            String title = "Data Save Successful";
            String message = getSample();
          //   MessageAlerts.getInstance().showMessage(title, message, MessageAlerts.MessageType.DEFAULT, MessageAlerts.YES_NO_CANCEL_OPTION, (controller, action) -> {
            //if(action==MessageAlerts.CLOSED_OPTION){
            //    controller.consume();
           //     System.out.println("User press yes");
          //  }
          //   });
            MessageAlerts.getInstance().showMessage("Data Saving Failure", "Oops! We encountered an issue while attempting to save your data. Please try again later or contact support for assistance. Apologies for any inconvenience caused.", MessageAlerts.MessageType.ERROR,MessageAlerts.OK_CANCEL_OPTION,null);
        });
        add(cmd);
    }

    public String getSample() {
        return "These terms and conditions outline the rules and regulations for the use of Raven Channel's Website.\n" +
                "\n" +
                "By accessing this website, we assume you accept these terms and conditions in full. Do not continue to use Raven Channel's website if you do not accept all of the terms and conditions stated on this page.\n" +
                "\n" +
                "The following terminology applies to these Terms and Conditions, Privacy Statement, and Disclaimer Notice and any or all Agreements: \"Client,\" \"You,\" and \"Your\" refers to you, the person accessing this website and accepting the Company's terms and conditions. \"The Company,\" \"Ourselves,\" \"We,\" \"Our,\" and \"Us,\" refers to Raven Channel. \"Party,\" \"Parties,\" or \"Us,\" refers to both the Client and ourselves, or either the Client or ourselves.\n" +
                "\n" +
                "Cookies\n" +
                "\n" +
                "We employ the use of cookies. By accessing Raven Channel's website, you agree to use cookies in agreement with Raven Channel's Privacy Policy.\n" +
                "\n" +
                "Most interactive websites use cookies to retrieve user details for each visit. Cookies are used by our website to enable the functionality of certain areas to make it easier for people visiting our website. Some of our affiliate/advertising partners may also use cookies.\n" +
                "\n" +
                "License\n" +
                "\n" +
                "Unless otherwise stated, Raven Channel and/or its licensors own the intellectual property rights for all material on Raven Channel. All intellectual property rights are reserved. You may access this from Raven Channel for your personal use subjected to restrictions set in these terms and conditions.\n" +
                "\n" +
                "You must not:\n" +
                "\n" +
                "Republish material from Raven Channel\n" +
                "Sell, rent, or sub-license material from Raven Channel\n" +
                "Reproduce, duplicate, or copy material from Raven Channel\n" +
                "Redistribute content from Raven Channel\n" +
                "This Agreement shall begin on the date hereof.\n" +
                "\n" +
                "Disclaimer\n" +
                "\n" +
                "To the maximum extent permitted by applicable law, we exclude all representations, warranties, and conditions relating to our website and the use of this website. Nothing in this disclaimer will:\n" +
                "\n" +
                "Limit or exclude our or your liability for death or personal injury.\n" +
                "Limit or exclude our or your liability for fraud or fraudulent misrepresentation.\n" +
                "Limit any of our or your liabilities in any way that is not permitted under applicable law.\n" +
                "The limitations and prohibitions of liability set in this Section and elsewhere in this disclaimer: (a) are subject to the preceding paragraph; and (b) govern all liabilities arising under the disclaimer, including liabilities arising in contract, in tort, and for breach of statutory duty.\n" +
                "\n" +
                "As long as the website and the information and services on the website are provided free of charge, we will not be liable for any loss or damage of any nature.\n" +
                "\n" +
                "Reservation of Rights\n" +
                "\n" +
                "We reserve the right to request that you remove all links or any particular link to our website. You approve to immediately remove all links to our website upon request. We also reserve the right to amend these terms and conditions and it’s linking policy at any time. By continuously linking to our website, you agree to be bound to and follow these linking terms and conditions.\n" +
                "\n" +
                "Variation of Terms\n" +
                "\n" +
                "Raven Channel is permitted to revise these terms at any time as it sees fit, and by using this website, you are expected to review these terms on a regular basis.\n" +
                "\n" +
                "Governing Law & Jurisdiction\n" +
                "\n" +
                "These terms will be governed by and interpreted in accordance with the laws of the jurisdiction of [Your Company's Jurisdiction], and you submit to the non-exclusive jurisdiction of the state and federal courts located in [Your Company's Jurisdiction] for the resolution of any disputes.\n" +
                "\n" +
                "These terms and conditions have been generated at [Terms And Conditions Generator], the technology law firm.";
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new DemoAlerts().setVisible(true));
    }
}
