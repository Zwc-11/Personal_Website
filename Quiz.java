package CS.Version1_L;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class Quiz extends JFrame implements ActionListener {
    Random rand = new Random();

    // set up all the components I need
    JFrame frame = new JFrame();
    JButton menuButton = new JButton("Menu");
    private JPanel quizPanel;
    private JLabel questionNumberLabel;
    private JTextArea questionTextArea = new JTextArea();
    private JTextField answerTextField;
    private JButton prevButton;
    private JButton nextButton;
    private JButton submitButton;
    private JLabel scoreLabel;
    private JTable resultsTable;
    private DefaultTableModel tableModel;

    private int currentQuestionIndex;
    private int score;
    private RandomProblem[] questionList;

    public Quiz() {
        // Clear text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("answers.txt"));
            writer.write("");
            writer.close();
        } catch (Exception e) {

        }

        questionList = new RandomProblem[10];
        for (int i = 0; i < questionList.length; i++) {
            questionList[i] = new RandomProblem(rand.nextInt(5));
        }

        // Set up the JFrame
        frame.setTitle("Quiz");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set up the JPanel for all the slider
        JPanel controlPanel = new JPanel();
        // make some gap between each sliders
        controlPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        controlPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Set up a Panel for the menuButton, put it on the right side of the frame
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // set the size
        menuButton.setPreferredSize(new Dimension(100, 30));
        // add menuButton into menuPanel
        menuPanel.add(menuButton);
        // make menuButton actionlister for user to press
        menuButton.addActionListener(this);
        // add menuPanel to the frame and it's on Top right
        frame.add(menuPanel, BorderLayout.NORTH);

        // Set up the quiz panel container for the entire quiz section
        quizPanel = new JPanel();
        // A GridBagLayout places components in a grid of rows and columns, allowing
        // specified components to span multiple rows or columns.
        quizPanel.setLayout(new GridBagLayout());
        // GridBagConstraints class specifies constraints for components that are laid
        // out using the GridBagLayout class.
        GridBagConstraints gbc = new GridBagConstraints();
        // the component in the quizPanel will be horionztal form
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // inserts some padding
        gbc.insets = new Insets(10, 5, 5, 5);

        // Create a new panel with FlowLayout for the navigation buttons and label
        JPanel navigationPanel = new JPanel(new FlowLayout());

        // Set up the previous button
        // \u25C0 is a triangle code
        prevButton = new JButton("\u25C0");
        prevButton.setPreferredSize(new Dimension(50, 30));
        prevButton.setVisible(false);
        // if the user press on the prevButton, it will go the the prev question
        prevButton.addActionListener(e -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                updateQuestion();
            }
        });
        // add the prevButton
        navigationPanel.add(prevButton);

        // Set up the question number label
        questionNumberLabel = new JLabel("Question 1", SwingConstants.CENTER); // Set the label alignment to center
        questionNumberLabel.setPreferredSize(new Dimension(250, 50));
        questionNumberLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        navigationPanel.add(questionNumberLabel);

        // Set up the next button
        nextButton = new JButton("\u25B6");
        nextButton.setPreferredSize(new Dimension(50, 30));
        nextButton.setVisible(false);
        // if the user press on the nextButton, it will go the the next question
        nextButton.addActionListener(e -> {
            // check if the page is not the maximum page
            if (currentQuestionIndex < questionList.length - 1) {
                currentQuestionIndex++;
                updateQuestion();
            }
        });
        // add the nextButton
        navigationPanel.add(nextButton);

        // Add the navigation panel to the quiz panel
        // The leftmost column has address gridx=0 and the top row has address gridy=0.
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Span across 3 columns
        gbc.gridwidth = 3;
        // add navigationPanel
        quizPanel.add(navigationPanel, gbc);

        // Set up the question text area
        // the question should not be editable
        questionTextArea.setEditable(false);
        // if the sentence is too long, go to next line
        questionTextArea.setLineWrap(true);
        // If set to true the lines will be wrapped at word boundaries
        questionTextArea.setWrapStyleWord(true);
        // text font and Margin and position
        questionTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
        questionTextArea.setMargin(new Insets(10, 10, 10, 10));
        questionTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane questionScrollPane = new JScrollPane(questionTextArea);
        // set the container size
        questionScrollPane.setPreferredSize(new Dimension(600, 200));
        // same x-axis as button, so they all in the same column
        gbc.gridx = 0;
        // second layer of the grid, lower than the button
        gbc.gridy = 1;
        // Span across 3 columns
        gbc.gridwidth = 3;
        // add questionScrollPane
        quizPanel.add(questionScrollPane, gbc);

        // Set up the answer text field
        answerTextField = new JTextField();
        answerTextField.setPreferredSize(new Dimension(140, 35));
        answerTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        // same column as the previous components
        gbc.gridx = 0;
        // third layer of the grid, lower than the questionTextArea
        gbc.gridy = 2;
        // Span across 1 columns
        gbc.gridwidth = 1;
        quizPanel.add(answerTextField, gbc);

        // Create a new panel with FlowLayout for the submit button
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Set up the submit button
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(120, 30)); // Set a smaller size for the button
        submitButton.addActionListener(this);
        submitPanel.add(submitButton);

        // Add the submit panel to the quiz panel
        gbc.gridx = 0;
        // same column with the answerTextField
        gbc.gridy = 2;
        // span across 3 column
        gbc.gridwidth = 3;
        // Add some space around the button
        gbc.insets = new Insets(10, 5, 5, 5);
        // add the submitPanel
        quizPanel.add(submitPanel, gbc);

        // Set up the score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        // same column as the previous components
        gbc.gridx = 0;
        // at the third layer
        gbc.gridy = 3;
        // span scross 2 column
        gbc.gridwidth = 2;
        // add the scoreLabel to the quizPanel
        quizPanel.add(scoreLabel, gbc);

        // Set up the results table
        String[] columnNames = { "Question", "Your Answer", "Correct Answer", "Correct?" };

        // create a table with initial row = 0
        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(24);
        resultsTable.setFont(new Font(null, Font.PLAIN, 14));
        resultsTable.setDefaultEditor(Object.class, null);

        // Centers text in each cell of table
        ((DefaultTableCellRenderer) resultsTable.getDefaultRenderer(String.class))
                .setHorizontalAlignment(SwingConstants.CENTER);

        // set a scrollbar
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        // set the size
        scrollPane.setPreferredSize(new Dimension(100, 264));
        // same column as the prev components position
        gbc.gridx = 0;
        // at the fourth layer, lower than the answerTextField
        gbc.gridy = 4;
        // span 3 column
        gbc.gridwidth = 3;
        // add the scrollPane
        quizPanel.add(scrollPane, gbc);

        // Add the quiz panel to the JFrame
        frame.add(quizPanel, BorderLayout.CENTER);

        // Set the current question index to 0
        currentQuestionIndex = 0;

        // Show the JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        updateQuestion();
    }

    // make a updateQuestion label class, it will change if the user press the next
    // or prev button for the questionNumberLabel
    // If the user go to the next page, it will get the question from the
    // currentQuestionIndex and go search which question is that
    private void updateQuestion() {
        questionNumberLabel.setText("Question " + (currentQuestionIndex + 1));
        questionTextArea.setText(questionList[currentQuestionIndex].getProblem());
        answerTextField.setText("");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("answers.txt"));
            for (int i = 0; i < currentQuestionIndex; i++) {
                reader.readLine();
            }
            String line = reader.readLine();
            answerTextField.setText(line);
            reader.close();
        } catch (Exception e) {

        }
    }

    // when user click the submit button
    private void submitAnswer() {
        // Set the currentQuestion from the quizQuestions array
        RandomProblem currentQuestion = questionList[currentQuestionIndex];

        // get the userAnswer
        String boxText = answerTextField.getText();
        double userAnswer;

        // set the boolean
        boolean isCorrect = false;

        // yes or no problem
        if (currentQuestion.getType() == Problem.STARTING_FRICTION) {
            if (boxText.equalsIgnoreCase("yes") || boxText.equalsIgnoreCase("no")) {
                if (currentQuestion.checkAns(boxText.equalsIgnoreCase("yes"))) {
                    isCorrect = true;
                }
                Object[] rowData = { currentQuestionIndex + 1, boxText, currentQuestion.getStringAns(),
                        isCorrect ? "✓" : "X" };
                tableModel.addRow(rowData);
                answerTextField.setText("");

                currentQuestionIndex++;

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("answers.txt", true));
                    writer.write(currentQuestionIndex == 1 ? boxText : "\n" + boxText);
                    writer.close();
                } catch (Exception e) {

                }

            } else {
                JOptionPane.showMessageDialog(null, "Please enter yes or no", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else { // number answer problem
            try {
                userAnswer = Double.parseDouble(boxText);
                if (currentQuestion.checkAnswer(userAnswer)) {
                    isCorrect = true;
                }
                Object[] rowData = { currentQuestionIndex + 1, userAnswer, currentQuestion.getAnswer(),
                        isCorrect ? "✓" : "X" };
                tableModel.addRow(rowData);
                answerTextField.setText("");
                currentQuestionIndex++;
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("answers.txt", true));
                    writer.write(currentQuestionIndex == 1 ? boxText : "\n" + boxText);
                    writer.close();
                } catch (Exception e) {

                }
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(null, "Please enter a number", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // Update the score and results table
        if (isCorrect) {
            score++;
            scoreLabel.setText("Score: " + score);
        }

        // check if the page is smaller than the maximum page number
        if (currentQuestionIndex < questionList.length) {
            updateQuestion();
        } else {
            currentQuestionIndex--;
            // Show the final score
            JOptionPane.showMessageDialog(this, "Quiz complete! Final score: " + score + " / 10");
            answerTextField.setEditable(false);
            submitButton.setEnabled(false);

            // Set next and previous button visible so user can review questions
            prevButton.setVisible(true);
            nextButton.setVisible(true);
            updateQuestion();
        }
    }

    // buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        // if the user press the submit Button, it will run the submitAnswer function
        if (e.getSource() == submitButton) {
            submitAnswer();
            // if the user press the menuButton, it will go back to the Homepage.java
        } else if (e.getSource() == menuButton) {
            frame.dispose();
            new Homepage();
        }
    }

    public static void main(String[] args) {
        new Quiz();
    }
}
