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
    private Icon icon;
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

    public AvatarIcon(Icon icon, int width, int height, int round) {
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.round = round;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        updateImage();
        Graphics2D g2 = (Graphics2D) g.create();
        FlatUIUtils.setRenderingHints(g2);
        g2.setColor(c.getBackground());
        FlatUIUtils.paintComponentBackground(g2, x, y, imageWidth, imageHeight, 0, UIScale.scale(round));
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    private void updateImage() {
        if ((filename != null || location != null || icon != null) && (image == null || border != oldBorder)) {
            imageWidth = UIScale.scale(width);
            imageHeight = UIScale.scale(height);
            oldBorder = border;
            int b = UIScale.scale(border);
            ImageIcon icon;
            if (filename != null) {
                icon = new ImageIcon(filename);
            } else if (location != null) {
                icon = new ImageIcon(location);
            } else {
                icon = (ImageIcon) this.icon;
            }
            image = resizeImage(icon.getImage(), imageWidth, imageHeight, b);
        }
    }


    private Image resizeImage(Image icon, int width, int height, int border) {
        width -= border * 2;
        height -= border * 2;
        int sw = width - icon.getWidth(null);
        int sh = height - icon.getHeight(null);
        Image img;
        if (sw > sh) {
            //  Resize width
            img = new ImageIcon(icon.getScaledInstance(width, -1, Image.SCALE_SMOOTH)).getImage();
        } else {
            //  Resize height
            img = new ImageIcon(icon.getScaledInstance(-1, height, Image.SCALE_SMOOTH)).getImage();
        }
        return round > 0 ? roundImage(img, width, height, border, round) : img;
    }

    private Image roundImage(Image image, int width, int height, int border, int round) {
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int x = border + (width - w) / 2;
        int y = border + (height - h) / 2;
        BufferedImage buff = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buff.createGraphics();
        FlatUIUtils.setRenderingHints(g);
        if (round == 999) {
            g.fill(new Ellipse2D.Double(border, border, width, height));
        } else {
            int r = UIScale.scale(round);
            g.fill(new RoundRectangle2D.Double(border, border, width, height, r, r));
        }
        g.setComposite(AlphaComposite.SrcIn);
        g.drawImage(image, x, y, null);
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
