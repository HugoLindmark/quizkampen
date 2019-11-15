package se.nackademin;

import javax.swing.*;
import java.awt.*;

public class QuizzGui {

    private JFrame window;
    private JPanel basePanel;
    private JPanel lobbyPanel;
    private JPanel categoryPanel;
    private JPanel categoryButtonPanel;
    private JPanel gamePanel;
    private JPanel gameButtonPanel;
    private JPanel resultPanel;
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

    private Font textFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 23);
    private Font resultFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 110);

    private String correctAnswer = "Gustav";
    private String currentQuestion = "Vad heter kungen?";

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

        lobbyMessage = new JLabel("Waitng for Opponent..", JLabel.CENTER);
        lobbyPanel.add(lobbyMessage);
        lobbyMessage.setFont(textFont);
        lobbyMessage.setForeground(Color.white);

        debugButton = new JButton(">");
        lobbyPanel.add(debugButton, BorderLayout.SOUTH);
        debugButton.setBackground(lobbyPanel.getBackground());
        debugButton.setForeground(Color.white);
        debugButton.addActionListener(e -> {
            switchScenes(lobbyPanel, categoryPanel);
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

        cat1 = new JButton("Historia");
        categoryButtonPanel.add(cat1);
        cat1.setForeground(Color.white);
        cat1.setBackground(Color.gray);
        cat1.addActionListener( e-> {
            switchScenes(categoryPanel, gamePanel);
        });

        cat2 = new JButton("Mattematik");
        categoryButtonPanel.add(cat2);
        cat2.setForeground(Color.white);
        cat2.setBackground(Color.gray);
        cat2.addActionListener( e-> {
            switchScenes(categoryPanel, gamePanel);
        });

        cat3 = new JButton("Teknik");
        categoryButtonPanel.add(cat3);
        cat3.setForeground(Color.white);
        cat3.setBackground(Color.gray);
        cat3.addActionListener( e-> {
            switchScenes(categoryPanel, gamePanel);
        });

        cat4 = new JButton("Musik");
        categoryButtonPanel.add(cat4);
        cat4.setForeground(Color.white);
        cat4.setBackground(Color.gray);
        cat4.addActionListener( e-> {
            switchScenes(categoryPanel, gamePanel);
        });

        /* Game view */
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.darkGray);
        gamePanel.setLayout(new BorderLayout());

        question = new JLabel(currentQuestion, JLabel.CENTER);
        gamePanel.add(question);
        question.setFont(textFont);
        question.setForeground(Color.white);

        gameButtonPanel = new JPanel();
        gamePanel.add(gameButtonPanel, BorderLayout.SOUTH);
        gameButtonPanel.setLayout(new GridLayout(2,2));
        gameButtonPanel.setBackground(Color.darkGray);
        gameButtonPanel.setPreferredSize(new Dimension(110,110));

        alt1 = new JButton("Hugo");
        gameButtonPanel.add(alt1);
        alt1.setForeground(Color.white);
        alt1.setBackground(Color.gray);
        alt1.addActionListener( e-> {
            checkButton(alt1);
            switchScenes(gamePanel, resultPanel);
        });

        alt2 = new JButton(correctAnswer);
        gameButtonPanel.add(alt2);
        alt2.setForeground(Color.white);
        alt2.setBackground(Color.gray);
        alt2.addActionListener( e-> {
            checkButton(alt2);
        });

        alt3 = new JButton("Marcus");
        gameButtonPanel.add(alt3);
        alt3.setForeground(Color.white);
        alt3.setBackground(Color.gray);
        alt3.addActionListener(e ->{
            checkButton(alt3);
        });

        alt4 = new JButton("Johannes");
        gameButtonPanel.add(alt4);
        alt4.setForeground(Color.white);
        alt4.setBackground(Color.gray);
        alt4.addActionListener( e-> {
            checkButton(alt4);
        });

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(Color.darkGray);

        result = new JLabel("1-0", JLabel.CENTER);
        resultPanel.add(result);
        result.setFont(resultFont);
        result.setForeground(Color.white);

        newGame = new JButton("Starta nytt spel!");
        resultPanel.add(newGame, BorderLayout.SOUTH);
        newGame.setBackground(Color.gray);
        newGame.setForeground(Color.white);
        newGame.setPreferredSize(new Dimension(100, 50));
        newGame.addActionListener(e -> {
            switchScenes(resultPanel, lobbyPanel);
        });

        //Adds the basepanel and shows gui after initialization
        window.add(basePanel);
        window.setVisible(true);
    }

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
    private void switchScenes(JPanel current, JPanel target) {
        basePanel.remove(current);
        basePanel.add(target);
        basePanel.repaint();
        basePanel.revalidate();
        resetButtons();
    }

    /**
     * Checks if the pressed button is the correct answer
     * @param pressedButton the pressed button
     */
    private void checkButton(JButton pressedButton) {
        if(pressedButton.getText().equals(correctAnswer)) {
            pressedButton.setBackground(Color.green.darker());
        }
        else {
            pressedButton.setBackground(Color.red.darker());
            alt2.setBackground(Color.green.darker());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new QuizzGui().buildGui();
    }
}