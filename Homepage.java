package CS.version1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage implements ActionListener {
    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();
    JLabel label = new JLabel();

    JButton myButton = new JButton("Resources");
    JButton myButton2 = new JButton("Pratice");
    JButton myButton3 = new JButton("Quiz");

    Homepage() {
        label.setText("Welcome to CJ Physics Tutorial");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 40));
        label.setBackground(Color.BLACK);
        label.setOpaque(true);

        myButton.setBounds(100, 750, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener((ActionListener) this);

        myButton2.setBounds(400, 750, 200, 40);
        myButton2.setFocusable(false);
        myButton2.addActionListener((ActionListener) this);

        myButton3.setBounds(700, 750, 200, 40);
        myButton3.setFocusable(false);
        myButton3.addActionListener((ActionListener) this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        // frame.add(myButton, BorderLayout.SOUTH);
        // frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.add(myButton);
        frame.add(myButton2);
        frame.add(myButton3);
        frame.add(label);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            frame.dispose();
            Resources resources = new Resources();
        }
    }

}