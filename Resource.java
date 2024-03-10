package CS.version1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class Resource implements ActionListener {
    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();
    JLabel label = new JLabel();
    JLabel gif = new JLabel(new ImageIcon("cs_Culminating/Version_1/BackgroundGif.gif"));
    JButton myButton = new JButton("Material");
    JButton myButton2 = new JButton("Samples");
    JButton myButton3 = new JButton("Simulation");

    Resources() {
        label.setText("Resources");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Helvetica Neue", Font.PLAIN, 40));
        label.setOpaque(true);

        gif.setBounds(0, 100, 200, 200);

        myButton.setBounds(100, 750, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener((ActionListener) this);
        myButton.setBackground(new Color(255, 102, 25));

        myButton2.setBounds(400, 750, 200, 40);
        myButton2.setFocusable(false);
        myButton2.addActionListener((ActionListener) this);
        myButton2.setBackground(new Color(255, 102, 25));

        myButton3.setBounds(700, 750, 200, 40);
        myButton3.setFocusable(false);
        myButton3.addActionListener((ActionListener) this);
        myButton3.setBackground(new Color(255, 102, 25));

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
        frame.add(gif);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {

            Material material = new Material();
            frame.dispose();
        }
    }

}