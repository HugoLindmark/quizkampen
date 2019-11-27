package se.nackademin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuizGui {

    private JFrame window;
    private JPanel basePanel;
    public JPanel lobbyPanel;
    public JPanel categoryPanel;
    private JPanel categoryButtonPanel;
    public JPanel gamePanel;
    private JPanel gameButtonPanel;
    public JPanel resultPanel;
    private JLabel lobbyMessage;
    private JLabel categoryMessage;
    private JLabel question;
    private JLabel result;
    private JButton cat1;
    private JButton cat2;
    private JButton cat3;
    private JButton cat4;
    private JButton alt1;
    private JButton alt2;
    private JButton alt3;
    private JButton alt4;
    private JButton exit;

    private ArrayList<JButton> buttons = new ArrayList<>();

    private Font textFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 23);
    private Font questiontFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 15);

    private String correctAnswer;
    private String response;

    private boolean responded = false;

    public QuizGui() {
        buildGui();
    }

    /* Builds and shows the gui when called */
    private void buildGui() {
        window = new JFrame("Quizzkampen!");
        window.setSize(500,400);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The foundation panel that holds all other panels
        basePanel = new JPanel();
        basePanel.setLayout(new BorderLayout());

        /* Lobby view */
        lobbyPanel = new JPanel();
        basePanel.add(lobbyPanel);
        lobbyPanel.setBackground(Color.darkGray);
        lobbyPanel.setLayout(new BorderLayout());

        lobbyMessage = new JLabel("Väntar på motståndaren..", JLabel.CENTER);
        lobbyPanel.add(lobbyMessage);
        lobbyMessage.setFont(textFont);
        lobbyMessage.setForeground(Color.white);

        /* Category view */
        categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.darkGray);
        categoryPanel.setLayout(new BorderLayout());

        categoryMessage = new JLabel("Välj kategori!", JLabel.CENTER);
        categoryPanel.add(categoryMessage);
        categoryMessage.setFont(textFont);
        categoryMessage.setForeground(Color.white);

        categoryButtonPanel = new JPanel();
        categoryPanel.add(categoryButtonPanel, BorderLayout.SOUTH);
        categoryButtonPanel.setLayout(new GridLayout(1,4));
        categoryButtonPanel.setBackground(Color.darkGray);
        categoryButtonPanel.setPreferredSize(new Dimension(110,110));

        cat1 = new JButton();
        categoryButtonPanel.add(cat1);
        cat1.setForeground(Color.white);
        cat1.setBackground(Color.gray);
        cat1.addActionListener( e-> {
            responded = true;
            response = cat1.getText();
        });

        cat2 = new JButton();
        categoryButtonPanel.add(cat2);
        cat2.setForeground(Color.white);
        cat2.setBackground(Color.gray);
        cat2.addActionListener( e-> {
            responded = true;
            response = cat2.getText();
        });

        cat3 = new JButton();
        categoryButtonPanel.add(cat3);
        cat3.setForeground(Color.white);
        cat3.setBackground(Color.gray);
        cat3.addActionListener( e-> {
            responded = true;
            response = cat3.getText();
        });

        cat4 = new JButton();
        categoryButtonPanel.add(cat4);
        cat4.setForeground(Color.white);
        cat4.setBackground(Color.gray);
        cat4.addActionListener( e-> {
            responded = true;
            response = cat4.getText();
        });

        /* Game view */
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.darkGray);
        gamePanel.setLayout(new BorderLayout());

        question = new JLabel("Missingo", JLabel.CENTER);
        gamePanel.add(question);
        question.setFont(questiontFont);
        question.setForeground(Color.white);

        gameButtonPanel = new JPanel();
        gamePanel.add(gameButtonPanel, BorderLayout.SOUTH);
        gameButtonPanel.setLayout(new GridLayout(2,2));
        gameButtonPanel.setBackground(Color.darkGray);
        gameButtonPanel.setPreferredSize(new Dimension(110,110));

        alt1 = new JButton();
        gameButtonPanel.add(alt1);
        alt1.setForeground(Color.white);
        alt1.setBackground(Color.gray);
        alt1.addActionListener( e-> {
            checkButton(alt1);
        });

        alt2 = new JButton();
        gameButtonPanel.add(alt2);
        alt2.setForeground(Color.white);
        alt2.setBackground(Color.gray);
        alt2.addActionListener( e-> {
            checkButton(alt2);
        });

        alt3 = new JButton();
        gameButtonPanel.add(alt3);
        alt3.setForeground(Color.white);
        alt3.setBackground(Color.gray);
        alt3.addActionListener(e ->{
            checkButton(alt3);
        });

        alt4 = new JButton();
        gameButtonPanel.add(alt4);
        alt4.setForeground(Color.white);
        alt4.setBackground(Color.gray);
        alt4.addActionListener( e-> {
            checkButton(alt4);
        });

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(Color.darkGray);

        result = new JLabel("", JLabel.CENTER);
        resultPanel.add(result);
        result.setFont(textFont);
        result.setForeground(Color.white);

        exit = new JButton("Avsluta");
        resultPanel.add(exit, BorderLayout.SOUTH);
        exit.setBackground(Color.gray);
        exit.setForeground(Color.white);
        exit.setPreferredSize(new Dimension(100, 50));
        exit.addActionListener(e -> {
            System.exit(0);
        });

        //Adds the basePanel after initialization
        window.add(basePanel);
        window.setVisible(true);
    }

    /* Adds the answer alternatives to list  */
    private void listAnswerButtons() {
        buttons.clear();
        buttons.add(alt1);
        buttons.add(alt2);
        buttons.add(alt3);
        buttons.add(alt4);
    }

    /* Adds the categories buttons to list  */
    private void listCategoryButtons() {
        buttons.clear();
        buttons.add(cat1);
        buttons.add(cat2);
        buttons.add(cat3);
        buttons.add(cat4);
    }

    /* Resets button colors */
    private void resetButtons() {
        alt1.setBackground(Color.gray);
        alt2.setBackground(Color.gray);
        alt3.setBackground(Color.gray);
        alt4.setBackground(Color.gray);
    }

    /**
     * Switches from the current panel to the target panel
     * @param target the panel to show
     */
    public void switchTo(JPanel target) {
        basePanel.removeAll();
        basePanel.add(target);
        basePanel.revalidate();
        basePanel.repaint();
        resetButtons();
        resetRespons();
    }

    /**
     * Checks if the pressed button is the correct answer
     * @param pressedButton the pressed button
     */
    private void checkButton(JButton pressedButton) {
        responded = true;
        response = pressedButton.getText();
        if(response.equals(correctAnswer)) {
            pressedButton.setBackground(Color.green.darker());
        }
        else {
            pressedButton.setBackground(Color.red.darker());
            showCorrectAnswer();
        }
    }

    /* Finds the button with the correct answer */
    private void showCorrectAnswer() {
        for(JButton button : buttons) {
            if(button.getText().equals(correctAnswer)) {
                button.setBackground(Color.green.darker());
            }
        }
    }

    /**
     * Shows the result of the game when called
     * @param gameResult the result of the game
     */
    public void showResult(String gameResult) {
        result.setText(gameResult);
    }

    /**
     * Sets the text of the answer alternatives using a list that contains the answer alternatives
     * @param answers the list that contains the answer-alternatives
     */
    public void setAnswerAlternatives(ArrayList <String> answers) {
        int index = 0;

        listAnswerButtons();

        for(String alts : answers) {
            buttons.get(index).setText(alts);
            index++;
        }
    }

    /**
     * Sets the text of the category-buttons using a list that contains the categories
     * @param answers the list that contains the categories
     */
    public void setCategories(ArrayList <String> answers) {
        int index = 0;

        listCategoryButtons();

        for(String alts : answers) {
            buttons.get(index).setText(alts);
            index++;
        }
    }

    /**
     * Sets the correct answer to the question
     * @param answer the correct answer
     */
    public void setCorrectAnswer(String answer) {
        correctAnswer = answer;
    }

    /**
     * Sets the current question of the round
     * @param text the question
     */
    public void setQuestion(String text) {
        question.setText(text);
    }

    /**
     * Shows the result of the game when called
     * @param gameResult the result of the game
     */
    public void setResult(String gameResult) {
        result.setText(gameResult);
    }

    /* Returns the players respons to the question */
    public String getResponse() {
        return response;
    }

    /* Returns if the player has responsed by pressing a button */
    public boolean hasResponded() {
        return responded;
    }

    /* Resets respons when called */
    private void resetRespons() {
        responded = false;
    }
}