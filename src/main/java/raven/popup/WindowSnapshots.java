package raven.popup;

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;

public class WindowSnapshots {

    private JFrame frame;
    private JDialog dialog;

    private JComponent snapshotLayer;
    private boolean inShowSnapshot;


    public WindowSnapshots(JFrame frame) {
        this.frame = frame;
    }

    public WindowSnapshots(JDialog dialog) {
        this.dialog = dialog;
    }

    public void createSnapshot() {
        if (inShowSnapshot) {
            return;
        }
        inShowSnapshot = true;
        if ((frame != null && frame.isShowing()) || (dialog != null && dialog.isShowing())) {
            VolatileImage snapshot = frame != null ? frame.createVolatileImage(frame.getWidth(), frame.getHeight()) : dialog.createVolatileImage(dialog.getWidth(), dialog.getHeight());
            if (snapshot != null) {
                JLayeredPane layeredPane = frame != null ? frame.getLayeredPane() : dialog.getLayeredPane();
                layeredPane.paint(snapshot.getGraphics());
                snapshotLayer = new JComponent() {
                    @Override
                    public void paint(Graphics g) {
                        if (snapshot.contentsLost()) {
                            return;
                        }
                        g.drawImage(snapshot, 0, 0, null);
                    }

                    @Override
                    public void removeNotify() {
                        super.removeNotify();
                        snapshot.flush();
                    }
                };
                snapshotLayer.setSize(layeredPane.getSize());
                layeredPane.add(snapshotLayer, Integer.valueOf(JLayeredPane.DRAG_LAYER + 1));
            }
        }
    }

    public void removeSnapshot() {
        if (frame != null) {
            frame.getLayeredPane().remove(snapshotLayer);
        } else {
            dialog.getLayeredPane().remove(snapshotLayer);
        }
        inShowSnapshot = false;
    }
}
