package raven.alerts;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ShapeGenerator {

    protected static Shape randomShape(int v) {
        int width = 10;
        int height = 10;
        switch (v) {
            case 0:
                return createRandomCircle(width, height);
            case 1:
                return createRandomRectangle(width, height);
            default:
                return createRandomPolygon(width, height);
        }
    }

    private static Shape createRandomCircle(int width, int height) {
        int diameter = Math.min(width, height);
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;
        return new Ellipse2D.Double(x, y, diameter, diameter);
    }

    private static Shape createRandomRectangle(int width, int height) {
        int rectWidth = width / 2;
        int rectHeight = height / 2;
        int x = (width - rectWidth) / 2;
        int y = (height - rectHeight) / 2;
        return new Rectangle(x, y, rectWidth, rectHeight);
    }

    private static Shape createRandomPolygon(int width, int height) {
        int[] xPoints = {width / 4, width / 2, (3 * width) / 4};
        int[] yPoints = {height / 4, (3 * height) / 4, height / 2};
        return new Polygon(xPoints, yPoints, 3);
    }
}