package raven.swing;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

public class AvatarIcon implements Icon {

    private String filename;
    private URL location;
    private Image image;
    private int round;
    private int border;
    private int width;
    private int height;

    private int imageWidth;
    private int imageHeight;

    private int oldBorder;

    public AvatarIcon(String filename, int width, int height, int round) {
        this.filename = filename;
        this.width = width;
        this.height = height;
        this.round = round;
    }

    public AvatarIcon(URL location, int width, int height, int round) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.round = round;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        updateImage();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    private void updateImage() {
        if ((filename != null || location != null) && (image == null || border != oldBorder)) {
            System.out.println("Create Image");
            image = resizeImage(new ImageIcon(filename).getImage(), width, height);
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);
        }
    }


    private Image resizeImage(Image icon, int width, int height) {
        int w = width > -1 ? Math.min(width, icon.getWidth(null)) : -1;
        int h = height > -1 ? Math.min(height, icon.getHeight(null)) : -1;
        Image img = new ImageIcon(icon.getScaledInstance(w, h, Image.SCALE_SMOOTH)).getImage();
        return round > 0 ? roundImage(img, round) : img;
    }

    private Image roundImage(Image image, int round) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buff.createGraphics();
        FlatUIUtils.setRenderingHints(g);
        if (round == 999) {
            g.fill(new Ellipse2D.Double(0, 0, width, height));
        } else {
            int r = UIScale.scale(round);
            g.fill(new RoundRectangle2D.Double(0, 0, width, height, r, r));
        }
        g.setComposite(AlphaComposite.SrcIn);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return buff;
    }

    @Override
    public int getIconWidth() {
        updateImage();
        return imageWidth;
    }

    @Override
    public int getIconHeight() {
        updateImage();
        return imageHeight;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
}
