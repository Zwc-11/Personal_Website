package CS.Version1_L;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Material implements ActionListener {
        JFrame frame = new JFrame("CJ Physics Tutorial - Material");
        JPanel bigBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 160, 40));

        JPanel box1 = new JPanel();
        JPanel box2 = new JPanel();
        JPanel box3 = new JPanel();
        JPanel boxD1 = new JPanel();
        JPanel boxD2 = new JPanel();
        JPanel boxD3 = new JPanel();

        JLabel box1Header = new JLabel();
        JLabel box1Text = new JLabel();
        JLabel box1Diagram = new JLabel();

        JLabel box2Header = new JLabel();
        JLabel box2Text = new JLabel();
        JLabel box2Diagram = new JLabel();

        JLabel box3Header = new JLabel();
        JLabel box3Text = new JLabel();
        JLabel box3Diagram = new JLabel();

        JButton menu = new JButton("Menu");
        JButton resources = new JButton("Resources");

        Color buttonColor = Main.color2;
        Color hoverColor = Main.color3;

        Font headerFont = new Font("Helvetica Neue", Font.BOLD, 20);
        Font textFont = new Font("Helvetica Neue", Font.PLAIN, 14);

        Material() {
                bigBox.setPreferredSize(new Dimension(1200, 800));
                bigBox.setBackground(Color.BLACK);
                bigBox.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

                // Sets buttons
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

                // Sets big box
                bigBox.setBackground(Color.BLACK);
                bigBox.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

                // box1 and boxD1
                box1.setPreferredSize(new Dimension(700, 220));
                box1.setBackground(Color.BLACK);
                box1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                box1.setLayout(new BorderLayout());

                boxD1.setPreferredSize(new Dimension(550, 220));
                boxD1.setBackground(Color.BLACK);
                boxD1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                boxD1.setLayout(new BorderLayout());

                // box2 and boxD2
                box2.setPreferredSize(new Dimension(700, 220));
                box2.setBackground(Color.BLACK);
                box2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                box2.setLayout(new BorderLayout());

                boxD2.setPreferredSize(new Dimension(550, 220));
                boxD2.setBackground(Color.BLACK);
                boxD2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                boxD2.setLayout(new BorderLayout());

                // box3 and boxD3
                box3.setPreferredSize(new Dimension(700, 220));
                box3.setBackground(Color.BLACK);
                box3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                box3.setLayout(new BorderLayout());

                boxD3.setPreferredSize(new Dimension(550, 220));
                boxD3.setBackground(Color.BLACK);
                boxD3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                boxD3.setLayout(new BorderLayout());

                // Text for box1
                box1Header.setText("<html><body style='width: 200px'>Static Friction (Fs)</body></html>");
                box1Header.setForeground(Main.color2);
                box1Header.setFont(headerFont);
                box1Header.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 0));
                box1Header.setOpaque(true);
                box1Header.setBackground(Color.BLACK);
                box1.add(box1Header, BorderLayout.NORTH);

                box1Text.setText("<html><body style='width: 450px'><ul>"
                                + "<li style='padding-left: 20px'>  Force exerted by a surface on a stationary object</li>"
                                + "<li style='padding-left: 20px'>  Prevents the object from beginning to move.</li>"
                                + "<li style='padding-left: 20px'>  Increases as applied force increases.</li>"
                                + "<li style='padding-left: 20px'>  Acts in the opposite direction as attempted motion.</li>"
                                + "<li style='padding-left: 20px'>  Object experiences no motion (as static friction force equals applied force so they cancel each other out).</li>"
                                + "<li style='padding-left: 20px'>  Occurs when the applied force is less than or equal to the starting friction / maximum static friction.</li>"
                                + "<li style='padding-left: 20px'>  Depends on 2 factors: coefficient of friction and normal force</li>"
                                + "</ul></body></html>");

                box1Text.setForeground(Color.WHITE);
                box1Text.setFont(textFont);
                box1Text.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                box1Text.setOpaque(true);
                box1Text.setBackground(Color.BLACK); // Set background color
                box1.add(box1Text, BorderLayout.CENTER);

                // Text for box2
                box2Header.setText("<html><body style='width: 200px'>Kinetic Friction (Fk)</body></html>");
                box2Header.setForeground(Main.color2);
                box2Header.setFont(headerFont);
                box2Header.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 0));
                box2Header.setOpaque(true);
                box2Header.setBackground(Color.BLACK);
                box2.add(box2Header, BorderLayout.NORTH);

                box2Text.setText("<html><body style='width: 450px'><ul>"
                                + "<li style='padding-left: 20px'>  Force exerted by a surface on a moving object</li>"
                                + "<li style='padding-left: 20px'>  Constant (as long as surfaces don't change), regardless of applied force</li>"
                                + "<li style='padding-left: 20px'>  Acts in the opposite direction of the motion</li>"
                                + "<li style='padding-left: 20px'>  Occurs when the applied force is greater than the starting friction / maximum static friction</li>"
                                + "<li style='padding-left: 20px'>  Depends on 2 factors: coefficient of friction and normal force</li>"
                                + "</ul></body></html>");

                box2Text.setForeground(Color.WHITE);
                box2Text.setFont(textFont);
                box2Text.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                box2Text.setOpaque(true);
                box2Text.setBackground(Color.BLACK);
                box2.add(box2Text, BorderLayout.CENTER);

                // Text for box3
                box3Header.setText("<html><body style='width: 200px'>Coefficient of Friction</body></html>");
                box3Header.setForeground(Main.color2);
                box3Header.setFont(headerFont);
                box3Header.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 0));
                box3Header.setOpaque(true);
                box3Header.setBackground(Color.BLACK);
                box3.add(box3Header, BorderLayout.NORTH);

                box3Text.setText("<html><body style='width: 450px'><ul>"
                                + "<li style='padding-left: 20px'>  Experientially determined constant</li>"
                                + "<li style='padding-left: 20px'>  Depends on the nature of the two contacting surfaces</li>"
                                + "<li style='padding-left: 20px'>  Affects the amount of friction an object experiences</li>"
                                + "<li style='padding-left: 20px'>  Two types: coefficient of static friction (μs) and coefficient of kinetic friction (μk)</li>"
                                + "<li style='padding-left: 20px'>  Equations: Fₖ = μₖFₙ or  Fₛ = μₛFₙ</li>"
                                + "</ul></body></html>");
                box3Text.setForeground(Color.WHITE);
                box3Text.setFont(textFont);
                box3Text.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                box3Text.setOpaque(true);
                box3Text.setBackground(Color.BLACK);
                box3.add(box3Text, BorderLayout.CENTER);

                // Image for boxD1
                ImageIcon imageIcon1 = new ImageIcon("Static_Friction.png");
                Image image1 = imageIcon1.getImage().getScaledInstance(550, 250, Image.SCALE_SMOOTH);
                box1Diagram.setIcon(new ImageIcon(image1));
                boxD1.add(box1Diagram);

                // Image for boxD2
                ImageIcon imageIcon2 = new ImageIcon("Kinetic_Friction.png");
                Image image2 = imageIcon2.getImage().getScaledInstance(550, 250, Image.SCALE_SMOOTH);
                box2Diagram.setIcon(new ImageIcon(image2));
                boxD2.add(box2Diagram);

                // Image for boxD3
                ImageIcon imageIcon3 = new ImageIcon("Coefficient_of_friction.png");
                Image image3 = imageIcon3.getImage().getScaledInstance(550, 250, Image.SCALE_SMOOTH);
                box3Diagram.setIcon(new ImageIcon(image3));
                boxD3.add(box3Diagram);

                // Add components to bigbox
                bigBox.add(box1);
                bigBox.add(boxD1);
                bigBox.add(box2);
                bigBox.add(boxD2);
                bigBox.add(box3);
                bigBox.add(boxD3);
                bigBox.setBounds(Main.screenWidth / 2 - 800, Main.screenHeight / 2 - 460, 1600, 900);
                bigBox.setBackground(Color.BLACK);

                // Add components to frame
                frame.add(bigBox);
                frame.add(menu);
                frame.add(resources);

                // Sets frame
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.getContentPane().setBackground(Color.BLACK);
                frame.setVisible(true);
                frame.setResizable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menu) {
                        frame.dispose();
                        new Homepage();
                } else if (e.getSource() == resources) {
                        frame.dispose();
                        new Resources();
                }
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
                                ((JButton) e.getSource()).setBackground(hoverColor);
                        }

                        public void mouseExited(MouseEvent e) {
                                ((JButton) e.getSource()).setBackground(buttonColor);
                        }
                };
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new Material());
        }
}