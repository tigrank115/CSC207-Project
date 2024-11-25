package app.gui;

import javax.swing.*;
import java.awt.*;

/**
 * GUI class to run the GUI for the Survey App.
 */
public class SurveyGUITest {
    static final int ROWS = 4;
    static final int COLS = 2;
    static final int WIDTH = 850;
    static final int HEIGHT = 300;

    /**
     * Main method to run the GUI.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        // Initial setup for the program.
        // The config hides the details of which implementation of GradeDB
        // we are using in the program. If we were to use a different implementation
        // of SurveyDB, this config is what we would change.

        // this is the code that runs to set up our GUI
        SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame("Survey GUI App");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);

            final CardLayout cardLayout = new CardLayout();
            final JPanel cardPanel = new JPanel(cardLayout);

            final JPanel defaultCard = createDefaultCard();

            cardPanel.add(defaultCard, "DefaultCard");

            final JButton getGradeButton = new JButton("Log In");
            getGradeButton.addActionListener(event -> cardLayout.show(cardPanel, "GetGradeCard"));

            final JButton createNewAccountButton = new JButton("Create New Account");
            createNewAccountButton.addActionListener(event -> cardLayout.show(cardPanel, "CreateNewAccountCard"));

            final JPanel buttonPanel = new JPanel();
            buttonPanel.add(getGradeButton);
            buttonPanel.add(createNewAccountButton);

            frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    // utility methods that take care of setting up each JPanel to be displayed
    // in our GUI
    private static JPanel createDefaultCard() {
        final JPanel defaultCard = new JPanel();
        defaultCard.setLayout(new GridBagLayout());

        final JLabel infoLabel = new JLabel("Welcome to QuikSurvey!");

        defaultCard.add(infoLabel);

        return defaultCard;
    }

    private static JPanel createLogInCard(JFrame jFrame) {
        final JPanel logInCard = new JPanel();
        logInCard.setLayout(new GridLayout(ROWS, COLS));

        final JTextField usernameField = new JTextField(20);
        final JTextField passwordField = new JTextField(20);
        final JButton getButton = new JButton("Get");

        final JLabel resultLabel = new JLabel();

        getButton.addActionListener(event -> {
            final String username = usernameField.getText();
            final String password = passwordField.getText();
            try {
                final Grade grade = getGradeUseCase.getGrade(username, course);
                JOptionPane.showMessageDialog(jFrame, String.format("Grade: %d", grade.getGrade()));
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(jFrame, ex.getMessage());
            }
        });

        getGradeCard.add(new JLabel("Username:"));
        getGradeCard.add(usernameField);
        getGradeCard.add(new JLabel("Password:"));
        getGradeCard.add(passwordField);
        getGradeCard.add(getButton);
        getGradeCard.add(resultLabel);

        return getGradeCard;
    }


    }



