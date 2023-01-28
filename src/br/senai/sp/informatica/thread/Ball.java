package br.senai.sp.informatica.thread;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("ALL")
public class Ball {
    protected Container box;
    protected int x = 0;
    protected int y = 0;
    private int dx = 2;
    private int dy = 2;
    protected Color color = Color.black;
    protected boolean finaliza = false;

    public Ball(Container b) {
        box = b;
    }

    public void move() {
        Graphics g = box.getGraphics();

        BufferedImage newImg =
                new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics gImg = newImg.getGraphics();
        gImg.setColor(box.getBackground());
        gImg.fillOval(0, 0, 10, 10);
        g.drawImage(newImg, x, y, null);

        x += dx;
        y += dy;
        Dimension d = box.getSize();
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + 10 >= d.width) {
            x = d.width - 10;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + 10 >= d.height) {
            y = d.height - 10;
            dy = -dy;
        }

        gImg.setColor(color);
        gImg.fillOval(0, 0, 10, 10);
        g.drawImage(newImg, x, y, null);

        gImg.dispose();
        g.dispose();
    }

    public void fim() {
        finaliza = true;
    }

    public void run() {
        try {
            while (!finaliza) {
                move();
                Thread.sleep(15);
            }
        } catch (InterruptedException ex) {
        }
    }
}
