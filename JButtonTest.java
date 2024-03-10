
package CS.sus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonTest extends JPanel {
    private static final int WIDTH = 10000;
    private static final int HEIGHT = 300;
    private static final int BOX_SIZE = 100;
    private static int Accerlation = 25;
    private static double MASS = 100;
    private static double FRICTION = 0.15 * MASS * 9.8; // Friction coefficient
    private static double Fa = 0;
    private static double Fnet = Fa - FRICTION;
    private int x; // Current x-coordinate of the box
    private int y; // Current y-coordinate of the box
    private int velocity; // Current velocity of the box
    private Timer timer;
    private static int boost = 15;
    private static int booster = 0; // keep 0
    private static int dirF = 1;

    public JButtonTest() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton b = new JButton("Click Here");
        b.setBounds(50, 100, 65, 30);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                booster += boost;
            }
        });
        add(b);

        JButton c = new JButton("reverse");
        c.setBounds(200, 100, 100, 30);
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                booster -= boost;
            }
        });
        add(c);
        x = 0;
        y = (HEIGHT - BOX_SIZE) / 2;
        velocity = Accerlation;
        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x + BOX_SIZE < WIDTH) {
                    x += velocity;
                    if (velocity > 0) {
                        velocity += (double) Fnet / MASS / 60;
                    }
                    if (velocity < 0) {
                        velocity -= (double) Fnet / MASS / 60;
                    }
                    velocity += booster;
                    booster = 0;
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
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JButtonTest());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}