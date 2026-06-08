//written by teagan donnelly

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class hw05_spring {
     public static void main(String[] args) {
        //Sets up window and adds the drawing panel
        JFrame frame = new JFrame("Homework 05");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SierpinskiPanel panel = new SierpinskiPanel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}

class SierpinskiPanel extends JPanel {

    public SierpinskiPanel() {
        //Sets the size of the window and background color
        this.setPreferredSize(new Dimension(800, 700));
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //error check
        if (g == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        //the 3 corners of the big starting triangle
        int x1 = width / 2;
        int y1 = 20;
        int x2 = 20;
        int y2 = height - 20;
        int x3 = width - 20;
        int y3 = height - 20;

        //Draws the first big triangle
        g.setColor(Color.BLUE);
        fillTriangle(g, x1, y1, x2, y2, x3, y3);

        //Start the recursive fractal
        drawSierpinski(g, x1, y1, x2, y2, x3, y3);
    }

    private void drawSierpinski(Graphics g,
                                int x1, int y1,
                                int x2, int y2,
                                int x3, int y3) {

        double sideLength = distance(x1, y1, x2, y2);

        //pixel limit of 4
        if (sideLength < 4) {
            return;
        }

        //If any two points are the same return
        if ((x1 == x2 && y1 == y2) ||
            (x2 == x3 && y2 == y3) ||
            (x1 == x3 && y1 == y3)) {
            return;
        }

        //Midpoints of each side
        int mx12 = (x1 + x2) / 2;
        int my12 = (y1 + y2) / 2;

        int mx23 = (x2 + x3) / 2;
        int my23 = (y2 + y3) / 2;

        int mx31 = (x3 + x1) / 2;
        int my31 = (y3 + y1) / 2;

        //Draws upside down triangle in the middle
        g.setColor(Color.WHITE);
        fillTriangle(g, mx12, my12, mx23, my23, mx31, my31);


        //Top triangle
        g.setColor(Color.BLUE);
        fillTriangle(g, x1, y1, mx12, my12, mx31, my31);
        drawSierpinski(g, x1, y1, mx12, my12, mx31, my31);

        //Bottom left triangle
        fillTriangle(g, mx12, my12, x2, y2, mx23, my23);
        drawSierpinski(g, mx12, my12, x2, y2, mx23, my23);

        //Bottom right triangle
        fillTriangle(g, mx31, my31, mx23, my23, x3, y3);
        drawSierpinski(g, mx31, my31, mx23, my23, x3, y3);
    }

    private void fillTriangle(Graphics g,
                              int x1, int y1,
                              int x2, int y2,
                              int x3, int y3) {
        int[] xPoints = { x1, x2, x3 };
        int[] yPoints = { y1, y2, y3 };
        g.fillPolygon(xPoints, yPoints, 3);
    }

    private double distance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
