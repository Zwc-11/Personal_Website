package CS.Version1_L;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation extends JPanel implements ActionListener {
    // Set all the varibles I need
    private static int WIDTH = 1600;
    private static int HEIGHT = 200;
    private static int BOX_SIZE = 100;
    private static int ACCELERATION;
    private static double MASS = 100;
    private static double Coefficientoffriction = 0.1;
    private static double FRICTION = Coefficientoffriction * MASS * 9.8; // Friction force
    private static double Fa = 0; // Applied force
    private static double Fnet = Fa - FRICTION; // Net force
    private int x; // Current x-coordinate of the box
    private int y; // Current y-coordinate of the box
    private static double velocity = 5; // Current velocity of the box
    private Timer timer;
    private boolean tipShown;
    // Set all the components I need
    JButton button;
    JFrame frame = new JFrame("Simulator");
    JButton menuButton = new JButton("Menu");
    JSlider velocityField;
    JSlider frictionField;
    JSlider forceField;
    JSlider massField;
    JLabel massValueLabel;
    JLabel frictionLabel;
    JLabel velocityLabel;
    JLabel forceLabel;
    JLabel distanceLabel;
    JButton submitButton;
    JLabel velocityValueLabel;
    JLabel frictionValueLabel;
    JLabel forceValueLabel;

    public Simulation() {
        // Frame size to the largest,
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        // frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setVisible(true);
        frame.getContentPane().setLayout(new BorderLayout());

        // create a container for All the JSlider
        JPanel controlPanel = new JPanel();
        // Make a space between each JSlider
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        // Set the height and width for the container
        controlPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Create a container that is add the top right corner of the frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // Set the size
        menuButton.setPreferredSize(new Dimension(100, 30));
        // add the menuButton to the menuPanel
        menuPanel.add(menuButton);
        // Add the ACtionListener so user can press the button
        menuButton.addActionListener(this);
        frame.add(menuPanel, BorderLayout.NORTH);

        // Create the JSlider for velocity, friction Force and Mass.
        // Tick marks and their labels are turned ON for each one
        velocityField = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (ACCELERATION));
        // The number increase by 10 each time for the label
        velocityField.setMajorTickSpacing(10);
        velocityField.setPaintTicks(true);
        velocityField.setPaintLabels(true);
        velocityValueLabel = new JLabel(String.valueOf(velocity));
        frictionField = new JSlider(JSlider.HORIZONTAL, 10, 90, (int) (Coefficientoffriction * 100));
        // The number increase by 10 each time for the label
        frictionField.setMajorTickSpacing(10);
        frictionField.setPaintTicks(true);
        frictionField.setPaintLabels(true);
        frictionValueLabel = new JLabel(String.valueOf(Coefficientoffriction));
        forceField = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (Fa * 10));
        // The number increase by 10 each time for the label
        forceField.setMajorTickSpacing(10);
        forceField.setPaintTicks(true);
        forceField.setPaintLabels(true);
        forceValueLabel = new JLabel(String.valueOf(Fa));
        massField = new JSlider(JSlider.HORIZONTAL, 50, 200, (int) MASS);
        // The number increase by 50 each time for the label
        massField.setMajorTickSpacing(50);
        massField.setPaintTicks(true);
        massField.setPaintLabels(true);
        massValueLabel = new JLabel(String.valueOf(MASS));

        // Create a button after user finish setting up the Value for JSlider
        JButton submitButton = new JButton("Run");
        // make the background colour
        submitButton.setBackground(new Color(102, 153, 255));
        // Make the foregounrd colour
        submitButton.setForeground(Color.WHITE);
        // Set the font size of the text in the button
        submitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        // Create a border colour for the button
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // set the size
        submitButton.setPreferredSize(new Dimension(100, 60));

        // add a text of each JLabel in front of the JSlider. Set the font style, and
        // add ValueLabel to the control Panel Container
        controlPanel.add(new JLabel("Initial Velocity:"));
        velocityValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20)); // Set font size
        controlPanel.add(velocityValueLabel);
        velocityField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(velocityField);
        controlPanel.add(new JLabel("Coefficient of Friction(0.1-0.9):"));
        frictionValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20)); // Set font size
        controlPanel.add(frictionValueLabel);
        frictionField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(frictionField);
        controlPanel.add(new JLabel("Applied Force:"));
        forceValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20)); // Set font size
        controlPanel.add(forceValueLabel);
        forceField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(forceField);
        controlPanel.add(new JLabel("Mass:"));
        massValueLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20)); // Set font size
        controlPanel.add(massValueLabel);
        massField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); // Set font size
        controlPanel.add(massField);
        // Add the submitButton in the controlPanel Container
        controlPanel.add(submitButton);

        // Create four dynamic value and show it to the user how these values changed
        // over time
        frictionLabel = new JLabel("Friction(N): " + FRICTION);
        velocityLabel = new JLabel("Velocity(m/s): " + velocity);
        forceLabel = new JLabel("Applied Force(N): " + Fa);
        distanceLabel = new JLabel("Distance(km): " + 0);

        // Set a valuePanel Container for the four dynamic value
        JPanel valuePanel = new JPanel();
        // arrange the components either vertically
        valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
        // create some emptyBorder
        valuePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add empty borders

        // Set frictionLabel style, create border color, and some emptyspace between
        // other Label
        frictionLabel = new JLabel("Friction(N): " + FRICTION);
        frictionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        frictionLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));
        // Set velocityLabel style, create border color, and some emptyspace between
        // other Label
        velocityLabel = new JLabel("Velocity(m/s): " + velocity);
        velocityLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        velocityLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));
        // Set forceLabel style, create border color, and some emptyspace between other
        // Label
        forceLabel = new JLabel("Applied Force(N): " + Fa);
        forceLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        forceLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));
        // Set distanceLabel style, create border color, and some emptyspace between
        // other Label
        distanceLabel = new JLabel("Distance(m): " + x);
        distanceLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        distanceLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK), // Outer black border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Inner empty border for padding
        ));

        // Add them to the valuePanel Container
        valuePanel.add(frictionLabel);
        valuePanel.add(velocityLabel);
        valuePanel.add(forceLabel);
        valuePanel.add(distanceLabel);
        // Add empty space at the top
        valuePanel.add(Box.createVerticalStrut(10));

        // After user click run button, it will run the updataParameters() class
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateParameters();
            }
        });

        // Create a contentPanel container for the moving box
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // set the x and y for the box
        x = 0;
        y = (HEIGHT - BOX_SIZE) / 2;
        // set velocity for future usage
        velocity = ACCELERATION;

        // create a button for restart the simulator
        button = new JButton("Click to restart");
        // set the text in the center of the Horizontal
        button.setHorizontalTextPosition(JButton.CENTER);
        // set the position in the center of the vertical
        button.setVerticalAlignment(JButton.CENTER);
        // Set the size and font style
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        // Create a border color to black
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // If the user press the button, it will restart the program
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        // set the button size and position
        button.setBounds(50, 100, 95, 30);
        // initializes both the initial delay and between-event delay to delay
        // milliseconds
        timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            // if the user click the run, it will start the actionPerformed
            public void actionPerformed(ActionEvent e) {
                // Update acceleration, mass, friction, and applied force values
                MASS = massField.getValue();
                ACCELERATION = velocityField.getValue();
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
                    // update the newest value for each one
                    frictionLabel.setText("Friction(N): " + FRICTION);
                    velocityLabel.setText("Velocity(m/s): " + velocity);
                    forceLabel.setText("Net Force(N): " + Fnet);
                    distanceLabel.setText("Distance(m): " + x);
                    // show it out
                    repaint();
                }
            }
        });
        // adds a change listener to the velocityField slider. Whenever the value of the
        // velocityField slider changes, the stateChanged method of the change listener
        // will be called. Inside the stateChanged method, the current value of the
        // velocityField slider is retrieved using getValue(). The retrieved value is
        // then set as the text of the velocityValueLabel JLabel using setText()
        // Same for rest of three
        velocityField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = velocityField.getValue();
                velocityValueLabel.setText(String.valueOf(value));
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
                massValueLabel.setText(String.valueOf(value));
            }
        });

        // add the paint or the box on the center of the frame
        contentPanel.add(this, BorderLayout.CENTER);
        // add the button to the contentPanel container
        this.add(button);
        // add contentPanel to the frame and set it center
        frame.add(contentPanel, BorderLayout.CENTER);

        // Set a bottomPanel Container and set it on the top center
        JPanel bottomPanel = new JPanel(new BorderLayout());
        // add the controlPanel at the Top of the container
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        // add the valuePanel at the Center of the bottomPanel
        bottomPanel.add(valuePanel, BorderLayout.CENTER);

        // add bottomPanel to the bottom of the frame
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // timer will continue firing events repeatedly at the specified interval until
        // it is stopped.
        timer.setRepeats(true);
        // how the timer handles multiple events if the previous event has not yet
        // finished processing when the next event fires.
        timer.setCoalesce(true);
        // start
        timer.start();
    }

    // a restart class, if restart, timer stop, the speed of box will = 0, velocity
    // will change it to acceleration, repaint the box, and start the timer again
    public void restart() {
        timer.stop();
        x = 0;
        velocity = ACCELERATION;
        velocityField.setValue(0);
        frictionField.setValue(10);
        forceField.setValue(0);
        massField.setValue(100);
        repaint();

    }

    // a restart_run is for restart for the run button.
    public void restart_run() {
        timer.stop();
        x = 0;
        velocity = ACCELERATION;
        repaint();
        timer.start();
    }

    // a update class, if the user click the submit button, it will start update the
    // value to these following variables
    public void updateParameters() {

        ACCELERATION = velocityField.getValue();
        MASS = massField.getValue();
        Coefficientoffriction = frictionField.getValue() / 100.0;
        System.out.println(Coefficientoffriction);
        FRICTION = Coefficientoffriction * MASS * 9.8;
        Fa = forceField.getValue() / 10.0;
        Fnet = Fa - FRICTION;
        restart_run();

        frictionLabel.setText("Friction: " + FRICTION);
        velocityLabel.setText("Velocity: " + velocity);
        forceLabel.setText("Applied Force: " + Fa);
        distanceLabel.setText("Distance: " + x);
        // frictionValueLabel.setText(String.valueOf(Coefficientoffriction));
        // forceValueLabel.setText(String.valueOf(Fa));
    }

    // Paint the box and the container
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Adjusted y coordinate for the square
        int boxY = getHeight() - BOX_SIZE;
        // Adjusted height for the drawRect
        int rectHeight = getHeight() - 200;
        // set the border of the container to black
        g.setColor(Color.BLACK);
        // draw the container out
        g.drawRect(0, 200, getWidth() - 1, rectHeight - 1);

        // check if the box reach the end of the frame, if yes, then reset everything,
        // set the velocity = 0
        if (x + BOX_SIZE > WIDTH) {
            x = WIDTH - BOX_SIZE; // Set the box position to the end of the frame
            velocity = 0; // Stop the box by setting velocity to 0
        }

        // Set a color for the box
        g.setColor(Color.BLUE);
        // paint the box out
        g.fillRect(x, boxY, BOX_SIZE, BOX_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if the button click on the Source in same as the myButton
        if (e.getSource() == menuButton) {
            // close the original frame and open Resources.java
            frame.dispose();
            new Homepage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the Nimbus Look and Feel
                UIManager.setLookAndFeel(new NimbusLookAndFeel());

                // Create and show the GUI
                new Simulation();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }
}
