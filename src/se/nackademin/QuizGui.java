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
    private JButton debugButton;
    private JButton cat1;
    private JButton cat2;
    private JButton cat3;
    private JButton cat4;
    private JButton alt1;
    private JButton alt2;
    private JButton alt3;
    private JButton alt4;
    private JButton newGame;

    private ArrayList<JButton> buttons = new ArrayList<>();

    private Font textFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 23);
    private Font resultFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 110);

    private String correctAnswer;

    private String response;
    private boolean responded = false;

    private int rounds = 1;

    /* Exits the game panel when a question has been answered after 1 second(1000 millie-seconds) */
    private Timer answerTimer = new Timer(1000, e-> {
        if(rounds < 4) {
            switchPanel(gamePanel, lobbyPanel);
            rounds++;
        }
        else {
            switchPanel(gamePanel, resultPanel);
            rounds = 1;
        }
        resetButtons();
        stopTimer();  //Dont repeat task every second
    });

    /* Stops timer when called */
    private void stopTimer() {
        answerTimer.stop();
    }

    public QuizGui() {
        buildGui();
    }

    /* Builds and shows the gui when called */
    private void buildGui() {
        window = new JFrame("Quizzkampen! - Demo Gui");
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

        debugButton = new JButton(">");
        lobbyPanel.add(debugButton, BorderLayout.SOUTH);
        debugButton.setBackground(lobbyPanel.getBackground());
        debugButton.setForeground(Color.white);
        debugButton.addActionListener(e -> {
            if(rounds == 1) {
                switchPanel(lobbyPanel, categoryPanel);
            }
            else {
                switchPanel(lobbyPanel, gamePanel);
            }
        });

        /* Category view */
        categoryPanel = new JPanel();
        categoryPanel.setBackground(Color.darkGray);
        categoryPanel.setLayout(new BorderLayout());

        categoryMessage = new JLabel("Choose category!", JLabel.CENTER);
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
            switchPanel(categoryPanel, gamePanel);
        });

        cat2 = new JButton();
        categoryButtonPanel.add(cat2);
        cat2.setForeground(Color.white);
        cat2.setBackground(Color.gray);
        cat2.addActionListener( e-> {
            responded = true;
            response = cat2.getText();
            switchPanel(categoryPanel, gamePanel);
        });

        cat3 = new JButton();
        categoryButtonPanel.add(cat3);
        cat3.setForeground(Color.white);
        cat3.setBackground(Color.gray);
        cat3.addActionListener( e-> {
            responded = true;
            response = cat3.getText();
            switchPanel(categoryPanel, gamePanel);
        });

        cat4 = new JButton();
        categoryButtonPanel.add(cat4);
        cat4.setForeground(Color.white);
        cat4.setBackground(Color.gray);
        cat4.addActionListener( e-> {
            responded = true;
            response = cat4.getText();
            switchPanel(categoryPanel, gamePanel);
        });

        /* Game view */
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.darkGray);
        gamePanel.setLayout(new BorderLayout());

        question = new JLabel("Empty", JLabel.CENTER);
        gamePanel.add(question);
        question.setFont(textFont);
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
            answerTimer.start();
        });

        alt2 = new JButton();
        gameButtonPanel.add(alt2);
        alt2.setForeground(Color.white);
        alt2.setBackground(Color.gray);
        alt2.addActionListener( e-> {
            checkButton(alt2);
            answerTimer.start();
        });

        alt3 = new JButton();
        gameButtonPanel.add(alt3);
        alt3.setForeground(Color.white);
        alt3.setBackground(Color.gray);
        alt3.addActionListener(e ->{
            checkButton(alt3);
            answerTimer.start();
        });

        alt4 = new JButton();
        gameButtonPanel.add(alt4);
        alt4.setForeground(Color.white);
        alt4.setBackground(Color.gray);
        alt4.addActionListener( e-> {
            checkButton(alt4);
            answerTimer.start();
        });

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(Color.darkGray);

        result = new JLabel("Empty", JLabel.CENTER);
        resultPanel.add(result);
        result.setFont(resultFont);
        result.setForeground(Color.white);

        newGame = new JButton("Starta nytt spel!");
        resultPanel.add(newGame, BorderLayout.SOUTH);
        newGame.setBackground(Color.gray);
        newGame.setForeground(Color.white);
        newGame.setPreferredSize(new Dimension(100, 50));
        newGame.addActionListener(e -> {
            switchPanel(resultPanel, lobbyPanel);
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
     * Replaces the current panel with another panel
     * @param current the current panel
     * @param target the panel to show
     */
    public void switchPanel(JPanel current, JPanel target) {
        basePanel.remove(current);
        basePanel.add(target);
        basePanel.revalidate();
        basePanel.repaint();
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
     * Merges the score of player one and player two to a string and shows it
     * @param player1 the score of player 1
     * @param player2 the score of player 2
     */
    public void setResult(int player1, int player2) {
        result.setText(player1 + "-" + player2);
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