package CS.version1;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Friction extends JPanel {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenHeight = (int) screenSize.getHeight();
    public static int screenWidth = (int) screenSize.getWidth();
    private static int WIDTH; // Updated dynamically
    private static int HEIGHT = 300;
    private static int BOX_SIZE = 150;
    private static int ACCELERATION = 5;
    private static double MASS = 100;
    private static double Coefficientoffriction = 0.1;
    private static double FRICTION = Coefficientoffriction * MASS * 9.8; // Friction force
    private static double Fa = 0; // Applied force
    private static double Fnet = Fa - FRICTION; // Net force
    private int x; // Current x-coordinate of the box
    private int y; // Current y-coordinate of the box
    private static final int MAX_DISTANCE = WIDTH - BOX_SIZE; // Maximum allowed distance
    private static double velocity; // Current velocity of the box
    private Timer timer;
    JButton button;
    JFrame frame = new JFrame();
    JSlider accelerationField;
    JSlider frictionField;
    JSlider forceField;
    JSlider massField;
    JLabel frictionLabel;
    JLabel velocityLabel;
    JLabel forceLabel;
    JLabel distanceLabel;
    JButton submitButton;
    JLabel accelerationValueLabel;
    JLabel frictionValueLabel;
    JLabel forceValueLabel;
    JLabel massValueLabel;

    public Friction() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, screenHeight));
        frame.setVisible(true);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        controlPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 100));

        accelerationField = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (ACCELERATION));
        accelerationField.setMajorTickSpacing(10);
        accelerationField.setPaintTicks(true);
        accelerationField.setPaintLabels(true);
        accelerationValueLabel = new JLabel(String.valueOf(ACCELERATION));
        frictionField = new JSlider(JSlider.HORIZONTAL, 10, 90, (int) (Coefficientoffriction * 100));
        frictionField.setMajorTickSpacing(10);
        frictionField.setPaintTicks(true);
        frictionField.setPaintLabels(true);
        frictionValueLabel = new JLabel(String.valueOf(Coefficientoffriction));
        forceField = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (Fa * 10));
        forceField.setMajorTickSpacing(10);
        forceField.setPaintTicks(true);
        forceField.setPaintLabels(true);
        forceValueLabel = new JLabel(String.valueOf(Fa));
        massField = new JSlider(JSlider.HORIZONTAL, 5, 200, (int) (MASS));
        massField.setMajorTickSpacing(45);
        massField.setPaintTicks(true);
        massField.setPaintLabels(true);
        massValueLabel = new JLabel(String.valueOf(MASS));
        // ...
        JButton submitButton = new JButton("Run");
        submitButton.setBackground(new Color(102, 153, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        controlPanel.add(new JLabel("Velocity(m/s):"));
        accelerationValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15)); // Set font size
        controlPanel.add(accelerationValueLabel);
        accelerationField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(accelerationField);
        controlPanel.add(new JLabel("Coefficient Of Friction(0.1-0.9):"));
        frictionValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15)); // Set font size
        controlPanel.add(frictionValueLabel);
        frictionField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(frictionField);
        controlPanel.add(new JLabel("Applied Force:"));
        forceValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15)); // Set font size
        controlPanel.add(forceValueLabel);
        forceField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(forceField);
        controlPanel.add(new JLabel("               Mass:"));
        massValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15)); // Set font size
        controlPanel.add(massValueLabel);
        massField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(massField);
        controlPanel.add(submitButton);

        frictionLabel = new JLabel("Friction(N): " + FRICTION);
        velocityLabel = new JLabel("Velocity(m/s): " + velocity);
        forceLabel = new JLabel("Applied Force(N): " + Fa);
        distanceLabel = new JLabel("Distance(km): " + 0);

        JPanel valuePanel = new JPanel();
        valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
        valuePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add empty borders

        frictionLabel = new JLabel("Friction(N): " + FRICTION);
        frictionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        frictionLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));

        velocityLabel = new JLabel("Velocity(m/s): " + velocity);
        velocityLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        velocityLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));

        forceLabel = new JLabel("Applied Force(N): " + Fa);
        forceLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        forceLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));

        distanceLabel = new JLabel("Distance(m): " + x);
        distanceLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        distanceLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));

        // Add empty space at the top
        valuePanel.add(frictionLabel);
        valuePanel.add(velocityLabel);
        valuePanel.add(forceLabel);
        valuePanel.add(distanceLabel);
        valuePanel.add(Box.createVerticalStrut(-10));

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateParameters();
            }
        });

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        x = 0;
        y = (HEIGHT - BOX_SIZE) / 2;
        velocity = ACCELERATION;

        button = new JButton("Click to restart");
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalAlignment(JButton.CENTER);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set border color to black
        submitButton.setPreferredSize(new Dimension(80, 40));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        button.setBounds(50, 100, 95, 30);
        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update acceleration, mass, friction, and applied force values
                ACCELERATION = accelerationField.getValue();
                Coefficientoffriction = frictionField.getValue() / 100.0;
                FRICTION = Coefficientoffriction * MASS * 9.8;
                Fa = forceField.getValue();
                Fnet = Fa - FRICTION;

                // Update velocity based on net force and mass
                double acceleration = Fnet / MASS;
                velocity += acceleration;

                // Limit the velocity to zero when it becomes negative
                if (velocity < 0) {
                    velocity = 0;
                }

                if (x + BOX_SIZE < WIDTH) {
                    // Update position
                    x += (int) velocity;

                    frictionLabel.setText("Friction(N): " + FRICTION);
                    velocityLabel.setText("Acceleration(m/s²): " + velocity);
                    forceLabel.setText("Fnet(N): " + Fnet);
                    distanceLabel.setText("Distance(m): " + x);

                    repaint();
                } else {
                    // Block has touched the border, stop the timer
                    timer.stop();
                }
            }
        });

        accelerationField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = accelerationField.getValue();
                accelerationValueLabel.setText(String.valueOf(value));
            }
        });
        frictionField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = frictionField.getValue();
                frictionValueLabel.setText(String.valueOf(value));
            }
        });
        forceField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = forceField.getValue();
                forceValueLabel.setText(String.valueOf(value));
            }
        });
        massField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = massField.getValue();
                MASS = value;
                massValueLabel.setText(String.valueOf(MASS));
            }
        });

        contentPanel.add(this, BorderLayout.CENTER);
        this.add(button);
        frame.setSize(screenWidth, screenHeight);

        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(contentPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        bottomPanel.add(valuePanel, BorderLayout.CENTER);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    public void restart() {
        timer.stop();
        x = 0;
        velocity = ACCELERATION;
        repaint();
        timer.start();
    }

    public void updateParameters() {
        try {
            ACCELERATION = accelerationField.getValue();
            Coefficientoffriction = frictionField.getValue() / 100.0;
            FRICTION = Coefficientoffriction * MASS * 9.8;
            Fa = forceField.getValue() / 10.0;
            Fnet = Fa - FRICTION;
            restart();

            frictionLabel.setText("Friction: " + FRICTION);
            velocityLabel.setText("Velocity: " + velocity);
            forceLabel.setText("Fnet: " + Fnet);
            distanceLabel.setText("Distance: " + x);

            frictionValueLabel.setText(String.valueOf(Coefficientoffriction));
            forceValueLabel.setText(String.valueOf(Fa));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please enter numeric values.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int boxY = getHeight() - BOX_SIZE - 100; // Adjusted y coordinate for the square
        int rectHeight = getHeight() - 200; // Adjusted height for the drawRect

        g.setColor(Color.BLACK);
        g.drawRect(0, 100, getWidth() - 1, rectHeight);

        if (x + BOX_SIZE > WIDTH) {
            // Calculate the maximum allowed distance based on the factors
            double maxDistance = (WIDTH - BOX_SIZE) - (ACCELERATION * ACCELERATION)
                    - (Coefficientoffriction * Coefficientoffriction)
                    - (Fa * Fa) - (velocity * velocity);

            String tip = "The box has moved out of the frame. Reduce the value of: ";
            if (ACCELERATION > maxDistance) {
                tip += "Acceleration";
            } else if (Coefficientoffriction > maxDistance) {
                tip += "Coefficient of Friction";
            } else if (Fa > maxDistance) {
                tip += "Applied Force";
            } else if (velocity > maxDistance) {
                tip += "Velocity";
            }
            JOptionPane.showMessageDialog(this, tip, "Tip", JOptionPane.INFORMATION_MESSAGE);
        }

        g.setColor(Color.BLUE);
        g.fillRect(x, boxY, BOX_SIZE, BOX_SIZE);
    }

    public static void main(String[] args) {
        System.out.println("Screen size: " + screenHeight + " " + screenWidth);
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the Nimbus Look and Feel
                UIManager.setLookAndFeel(new NimbusLookAndFeel());

                // Create and show the GUI
                new Friction();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }
}