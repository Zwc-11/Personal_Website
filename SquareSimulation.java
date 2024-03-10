package CS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareSimulation extends JPanel {
    private static final int WIDTH = 10000;
    private static final int HEIGHT = 300;
    private static final int BOX_SIZE = 100;
    private static final int Accerlation = 50;
    private static double MASS = 100;
    private static double FRICTION = 0.15 * MASS * 9.8; // Friction coefficient
    private static double Fa = 0;
    private static double Fnet = Fa - FRICTION;
    private int x; // Current x-coordinate of the box
    private int y; // Current y-coordinate of the box
    private int velocity; // Current velocity of the box
    private Timer timer;

    public SquareSimulation() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        x = 0;
        y = (HEIGHT - BOX_SIZE) / 2;
        velocity = Accerlation;
        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x + BOX_SIZE < WIDTH) {
                    x += velocity;
                    velocity += (double) Fnet / MASS / 60;
                    repaint();
                }
            }
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x, y, BOX_SIZE, BOX_SIZE);

        int arrowLength = 30;
        int arrowWidth = 10;
        int arrowX = x + BOX_SIZE + 10;
        int arrowY = y + (BOX_SIZE / 2) - (arrowWidth / 2);

        g.setColor(Color.BLACK);
        g.drawLine(arrowX, arrowY + (arrowWidth / 2), arrowX + arrowLength, arrowY + (arrowWidth / 2));
        g.drawLine(arrowX + arrowLength, arrowY + (arrowWidth / 2), arrowX + arrowLength - arrowWidth, arrowY);
        g.drawLine(arrowX + arrowLength, arrowY + (arrowWidth / 2), arrowX + arrowLength - arrowWidth,
                arrowY + arrowWidth);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SquareSimulation());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}