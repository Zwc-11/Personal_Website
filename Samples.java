package CS.Version1_L;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Samples implements ActionListener {
    // Creates the list of problems (5 in total)
    Problem[] sampleList = {
            new KineticFrictionProblem("box", 20, 0.5),
            new SlowingDownProblem("toy car", 15, 9, 0, 9),
            new SingleObjectProblem("wagon", 24, 1.2, 0.1),
            new StartingFrictionProblem("table", 15, 60, 0.4),
            new WallFrictionProblem("book", 5.4, 0.4)
    };

    // Creates the list of titles (5 in total)
    String[] titleList = {
            "Sample Problem #1 - Kinetic Friction",
            "Sample Problem #2 - Object Slowing Down",
            "Sample Problem #3 - Applied Force & Friction",
            "Sample Problem #4 - Starting Friction",
            "Sample Problem #5 - Wall Friction"
    };

    // problemNumber is the sample problem the user is currently on (starts at 1;
    // goes up to 5)
    int problemNumber = 1;

    JFrame frame = new JFrame("CJ Physics Tutorial - Samples");
    JPanel megaPanel = new JPanel(); // large panel that holds the other panels
    JPanel panel = new JPanel(); // title panel (displays title)
    JPanel panel2 = new JPanel(); // problem panel (displays problem)
    JPanel panel3 = new JPanel(); // diagram panel (displays FBD diagram)
    JPanel panel4 = new JPanel(); // solution panel (displays solution to problem)

    // Sets labels
    JLabel title = new JLabel();
    JLabel problem = new JLabel();
    JLabel solution = new JLabel();

    // Sets previous & next buttons to go between questions
    JButton previous = new JButton("Previous");
    JButton next = new JButton("Next");

    // Sets buttons to go back to other pages
    JButton menu = new JButton("Menu");
    JButton resources = new JButton("Resources");

    // Sets button colors (normal + hover)
    Color buttonColor = Main.color2;
    Color hoverColor = Main.color3;

    Samples() {
        // Sets the font of each label
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 36));
        problem.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
        solution.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));

        // Creates border (b) that will be added to each panel
        Border b = BorderFactory.createLineBorder(Color.black, 1);

        // Set the panels (location, size, layout, etc)
        megaPanel.setBounds(Main.screenWidth / 2 - 600, Main.screenHeight / 2 - 440, 1200, 780);
        megaPanel.setLayout(null);

        panel.setBounds(0, 0, 1200, 75);
        panel.setBorder(b);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panel.setBackground(Main.color1);
        panel.add(title);

        panel2.setBounds(0, 75, 1200, 300);
        panel2.setBorder(b);
        panel2.setBackground(Main.color1);
        panel2.add(problem);

        panel3.setBounds(0, 375, 600, 405);
        panel3.setBorder(b);
        panel3.setBackground(Main.color1);
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        panel4.setBounds(600, 375, 600, 405);
        panel4.setBorder(b);
        panel4.setBackground(Main.color1);
        panel4.add(solution);

        // Sets buttons
        previous.setBounds(Main.screenWidth / 2 - 600, Main.screenHeight / 2 + 360, 200, 50);
        previous.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        previous.setFocusable(false);
        previous.setBackground(buttonColor);
        previous.addActionListener((ActionListener) this);
        previous.addMouseListener(mouseHover());
        previous.setEnabled(false);

        next.setBounds(Main.screenWidth / 2 + 400, Main.screenHeight / 2 + 360, 200, 50);
        next.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        next.setFocusable(false);
        next.setBackground(buttonColor);
        next.addActionListener((ActionListener) this);
        next.addMouseListener(mouseHover());

        menu.setBounds(Main.screenWidth - 140, 30, 110, 30);
        menu.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        menu.setFocusable(false);
        menu.setBackground(buttonColor);
        menu.addActionListener((ActionListener) this);
        menu.addMouseListener(mouseHover());

        resources.setBounds(Main.screenWidth - 140, 70, 110, 30);
        resources.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        resources.setFocusable(false);
        resources.setBackground(buttonColor);
        resources.addActionListener((ActionListener) this);
        resources.addMouseListener(mouseHover());

        // Sets frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Main.color4);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        // Adds panels to megaPanel
        megaPanel.add(panel);
        megaPanel.add(panel2);
        megaPanel.add(panel3);
        megaPanel.add(panel4);
        // Adds megaPanel and buttons to frame
        frame.add(megaPanel);
        frame.add(previous);
        frame.add(next);
        frame.add(menu);
        frame.add(resources);

        // Sets problem to problem #1
        setProblem(problemNumber);
    }

    public void setProblem(int x) {
        /*
         * Function is called at the start, and when user switches problems (by clicking
         * next/previous)
         * Changes title, problem text, solution text accordingly
         * Changes the FBD diagram
         */
        title.setText(titleList[x - 1]);
        problem.setText(
                "<html><body><div style = 'width: 900px'>"
                        + "Problem <br>"
                        + sampleList[x - 1].getProblem()
                        + "</div></body></html>");
        solution.setText("<html><body><div style = 'width: 450px'>"
                + "Solution <br>"
                + sampleList[x - 1].getSolution()
                + "</div></body></html>");

        panel3.removeAll();
        panel3.add(sampleList[x - 1].getFbd());
        panel3.revalidate();
        panel3.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Whenever previous/next is clicked, it changes the current problem number
        if (e.getSource() == previous) {
            problemNumber--;
        } else if (e.getSource() == next) {
            problemNumber++;
            // Menu and resources button take user to other pages
        } else if (e.getSource() == menu) {
            frame.dispose();
            new Homepage();
        } else if (e.getSource() == resources) {
            frame.dispose();
            new Resources();
        }
        // Disables certain buttons depending on current problem number
        // Ex: when problem number is 5 (last question), next is disabled
        switch (problemNumber) {
            case 1:
                previous.setEnabled(false);
                previous.setBackground(buttonColor);
                break;
            case 5: // 5 problems in total
                next.setEnabled(false);
                next.setBackground(buttonColor);
                break;
            default:
                previous.setEnabled(true);
                next.setEnabled(true);
        }

        // Calls the setProblem function with the next problem number
        setProblem(problemNumber);
    }

    private MouseListener mouseHover() {
        // Mouse listener added to the buttons
        // Mouse listener detects when mouse enters/exits a component
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == previous && previous.isEnabled() || e.getSource() == next && next.isEnabled()) {
                    ((JButton) e.getSource()).setBackground(hoverColor);
                } else if (e.getSource() == resources || e.getSource() == menu) {
                    ((JButton) e.getSource()).setBackground(hoverColor);
                }
            }

            public void mouseExited(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(buttonColor);
            }
        };
    }

    public static void main(String[] args) {
        new Samples();
    }
}