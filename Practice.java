package CS.Version1_L;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Practice implements ActionListener {
    RandomProblem newProblem; // the current problem

    JFrame frame = new JFrame("CJ Physics Tutorial - Practice");
    JPanel megaPanel = new JPanel();
    JPanel panel = new JPanel(); // title panel
    JPanel panel2 = new JPanel(); // problem panel
    JPanel panel3 = new JPanel(); // diagram / hint panel
    JPanel panel4 = new JPanel(); // solution panel
    JPanel panel5 = new JPanel(); // answer panel
    JPanel space = new JPanel();

    JLabel title = new JLabel("Practice");
    JLabel problem = new JLabel();
    JLabel solution = new JLabel("");

    JLabel ansLabel = new JLabel("Answer:");
    JTextField answerBox = new JTextField();
    JButton check = new JButton("Check");
    JLabel note = new JLabel();
    JLabel correct = new JLabel();

    JButton hint = new JButton("View Hint");
    JButton solu = new JButton("View Solution");

    JButton menu = new JButton("Menu");

    JButton next = new JButton("Next Problem");

    Color buttonColor = Main.color2;
    Color hoverColor = Main.color3;

    Practice() {
        // Sets component layouts
        title.setFont(new Font("Helvetica Neue", Font.PLAIN, 36));
        problem.setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
        solution.setFont(new Font("Helvetica Neue", Font.PLAIN, 22));

        // Sets answer components
        ansLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 28));
        ansLabel.setPreferredSize(new Dimension(100, 40));

        answerBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        answerBox.setPreferredSize(new Dimension(160, 40));
        answerBox.setLocation(0, 120);

        check.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        check.setPreferredSize(new Dimension(120, 40));
        check.setFocusable(false);
        check.setBackground(buttonColor);
        check.addActionListener((ActionListener) this);
        check.addMouseListener(mouseHover());

        note.setFont(new Font("Helvetica Neue", Font.ITALIC, 16));
        note.setPreferredSize(new Dimension(450, 40));

        correct.setFont(new Font("Helvetica Neue", Font.PLAIN, 24));
        correct.setPreferredSize(new Dimension(450, 40));

        Border b = BorderFactory.createLineBorder(Color.black, 1);

        megaPanel.setBounds(Main.screenWidth / 2 - 700, 75, 1400, 800);
        megaPanel.setLayout(null);

        panel.setBounds(0, 0, 1400, 75);
        panel.setBorder(b);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panel.setBackground(Main.color1);
        panel.add(title);

        panel2.setBounds(0, 75, 900, 250);
        panel2.setBorder(b);
        panel2.setBackground(Main.color1);
        panel2.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 30));
        panel2.add(problem);

        panel5.setBounds(900, 75, 500, 250);
        panel5.setBorder(b);
        panel5.setBackground(Main.color1);
        panel5.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 30));
        panel5.add(ansLabel);
        panel5.add(answerBox);
        panel5.add(check);
        panel5.add(note);
        panel5.add(correct);

        panel3.setBounds(0, 325, 700, 475);
        panel3.setBorder(b);
        panel3.setBackground(Main.color1);
        panel3.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 15));
        panel3.add(hint);

        panel4.setBounds(700, 325, 700, 475);
        panel4.setBorder(b);
        panel4.setBorder(b);
        panel4.setBackground(Main.color1);
        panel4.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 15));
        panel4.add(solu);
        panel4.add(solution);

        space.setPreferredSize(new Dimension(100, 30));
        space.setBackground(Main.color1);

        // Sets buttons
        hint.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        hint.setPreferredSize(new Dimension(200, 30));
        hint.setFocusable(false);
        hint.setBackground(buttonColor);
        hint.addActionListener((ActionListener) this);
        hint.addMouseListener(mouseHover());

        solu.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        solu.setPreferredSize(new Dimension(200, 30));
        solu.setFocusable(false);
        solu.setBackground(buttonColor);
        solu.addActionListener((ActionListener) this);
        solu.addMouseListener(mouseHover());

        next.setBounds(Main.screenWidth / 2 + 400, 900, 300, 50);
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

        // Sets frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Main.color4);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        megaPanel.add(panel);
        megaPanel.add(panel2);
        megaPanel.add(panel3);
        megaPanel.add(panel4);
        megaPanel.add(panel5);

        frame.add(megaPanel);
        frame.add(next);
        frame.add(menu);

        setProblem();
    }

    public void setProblem() {
        Random rand = new Random();
        newProblem = new RandomProblem(rand.nextInt(4));
        problem.setText(
                "<html><body><div style = 'width: 650px'>"
                        + "Problem <br>"
                        + newProblem.getProblem()
                        + "</div></body></html>");

        answerBox.setText("");
        answerBox.setBorder(null);
        answerBox.setEditable(true);
        correct.setText("");

        if (newProblem.getType() == Problem.STARTING_FRICTION) {
            note.setText("*Enter yes (object will move) or no (object will not move)");
        } else {
            note.setText("*Marked as correct if within 0.5% of actual answer");
        }

        solution.setText("");
        panel3.removeAll();
        panel3.add(hint);
        panel3.add(space);
        panel3.repaint();

        next.setEnabled(false);
        hint.setEnabled(true);
        solu.setEnabled(false);
        check.setEnabled(true);
    }

    public void check() throws InterruptedException {
        // Checks if answer is correct
        String boxText = answerBox.getText();
        double userAnswer;

        Thread.sleep(200);

        // yes or no problem
        if (newProblem.getType() == Problem.STARTING_FRICTION) {
            if (boxText.equalsIgnoreCase("yes") || boxText.equalsIgnoreCase("no")) {
                if (newProblem.checkAns(boxText.equalsIgnoreCase("yes"))) {
                    answerBox.setBorder(BorderFactory.createLineBorder(Color.green, 2));
                    correct.setForeground(Color.green);
                    correct.setText("Correct!");
                    check.setEnabled(false);
                    answerBox.setEditable(false);
                } else {
                    answerBox.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                    correct.setForeground(new Color(255, 70, 0));
                    correct.setText("Try again, view solution, or move on");
                }
                next.setEnabled(true);
                if (solution.getText() == "") {
                    solu.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter yes or no", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else { // number answer problem
            try {
                userAnswer = Double.parseDouble(boxText);
                // System.out.println("User entered " + userAnswer);
                if (newProblem.checkAnswer(userAnswer)) {
                    answerBox.setBorder(BorderFactory.createLineBorder(Color.green, 2));
                    correct.setForeground(Color.green);
                    correct.setText("Correct!");
                    check.setEnabled(false);
                    answerBox.setEditable(false);
                } else {
                    answerBox.setBorder(BorderFactory.createLineBorder(Color.red, 2));
                    correct.setForeground(new Color(255, 70, 0));
                    correct.setText("Try again, view solution, or move on");
                }
                next.setEnabled(true);
                if (solution.getText() == "") {
                    solu.setEnabled(true);
                }
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(null, "Please enter a number", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void viewHint() {
        panel3.add(hint);
        panel3.add(space);
        panel3.add(newProblem.getFbd());
        panel3.revalidate();
        panel3.repaint();
        hint.setEnabled(false);
    }

    public void viewSolution() {
        viewHint();
        solution.setText("<html><body><div style = 'width: 525px'>"
                + "Solution <br>"
                + newProblem.getSolution()
                + "</div></body></html>");
        solu.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            setProblem();
        }

        else if (e.getSource() == check) {
            try {
                check();
            } catch (InterruptedException e1) {
                Thread.currentThread().interrupt();
            }
        }

        else if (e.getSource() == hint) {
            viewHint();
        }

        else if (e.getSource() == solu) {
            viewSolution();
        }

        else if (e.getSource() == menu) {
            frame.dispose();
            new Homepage();
        }

        ((JButton) e.getSource()).setBackground(buttonColor);
    }

    private MouseListener mouseHover() {
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                if (((JButton) e.getSource()).isEnabled()) {
                    ((JButton) e.getSource()).setBackground(hoverColor);
                }
            }

            public void mouseExited(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(buttonColor);
            }
        };
    }

    public static void main(String[] args) {
        Main.screenHeight = 1080;
        Main.screenWidth = 1920;
        new Practice();
    }
}
