package se.nackademin;

import javax.swing.*;
import java.awt.*;

public class QuizzGui {

    /**
     * TODO
     *
     * lås resize + size tweeks
     *
     * kategori vy
     *
     * result vy
     *
     **/

    private JFrame window;
    private JPanel basePanel;
    private JPanel buttonPanel;
    private JPanel lobbyPanel;
    private JPanel gamePanel;
    private JTextArea lobbyMessage;
    private JTextArea question;
    private JButton debugButton;
    private JButton alt1;
    private JButton alt2;
    private JButton alt3;
    private JButton alt4;

    private Font textFont = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 23);

    private String correctAnswer = "Gustav";
    private String currentQuestion = "Vad heter kungen?";

    private boolean showGame = false;

    private void buildGui() {
        window = new JFrame("Quizzkampen! - Demo Gui");
        window.setSize(500,550);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        basePanel = new JPanel();
        basePanel.setLayout(new BorderLayout());

        lobbyPanel = new JPanel();
        basePanel.add(lobbyPanel);
        lobbyPanel.setBackground(Color.darkGray);
        lobbyPanel.setLayout(new BorderLayout());

        debugButton = new JButton(">");
        lobbyPanel.add(debugButton, BorderLayout.SOUTH);
        debugButton.setBackground(lobbyPanel.getBackground());
        debugButton.setForeground(Color.white);
        debugButton.addActionListener(e -> {
            switchScenes();
        });

        lobbyMessage = new JTextArea("\n \t" + "Wating for Opponent..");
        lobbyPanel.add(lobbyMessage, BorderLayout.CENTER);
        lobbyMessage.setFont(textFont);
        lobbyMessage.setForeground(Color.white);
        lobbyMessage.setBackground(Color.darkGray);
        lobbyMessage.setEditable(false); //User is not allowed to edit lobby-message

        gamePanel = new JPanel();
        gamePanel.setBackground(Color.darkGray);
        gamePanel.setLayout(new BorderLayout());

        question = new JTextArea("\n \t " + currentQuestion);
        gamePanel.add(question, BorderLayout.NORTH);
        question.setFont(textFont);
        question.setForeground(Color.white);
        question.setBackground(Color.darkGray);
        question.setEditable(false); //User is not allowed to edit question

        buttonPanel = new JPanel();
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setPreferredSize(new Dimension(110,110));

        alt1 = new JButton("Hugo");
        buttonPanel.add(alt1);
        alt1.setForeground(Color.white);
        alt1.setBackground(Color.gray);
        alt1.addActionListener( e-> {
            checkButton(alt1);
        });

        alt2 = new JButton(correctAnswer);
        buttonPanel.add(alt2);
        alt2.setForeground(Color.white);
        alt2.setBackground(Color.gray);
        alt2.addActionListener( e-> {
            checkButton(alt2);
        });

        alt3 = new JButton("Marcus");
        buttonPanel.add(alt3);
        alt3.setForeground(Color.white);
        alt3.setBackground(Color.gray);
        alt3.addActionListener(e ->{
            checkButton(alt3);
        });

        alt4 = new JButton("Johannes");
        buttonPanel.add(alt4);
        alt4.setForeground(Color.white);
        alt4.setBackground(Color.gray);
        alt4.addActionListener( e-> {
            checkButton(alt4);
        });

        window.add(basePanel);
        window.setVisible(true);
    }

    private void resetButtons() {
        alt1.setBackground(Color.gray);
        alt2.setBackground(Color.gray);
        alt3.setBackground(Color.gray);
        alt4.setBackground(Color.gray);
    }

    private void switchScenes() {
        if(showGame) {
            basePanel.remove(gamePanel);
            basePanel.add(lobbyPanel);
            showGame = false;
        }
        else {
            basePanel.remove(lobbyPanel);
            basePanel.add(gamePanel);
            showGame = true;
        }
        resetButtons();
        basePanel.repaint();
        basePanel.revalidate();
    }

    private void checkButton(JButton pressedButton) {
        if(pressedButton.getText().equals(correctAnswer)) {
            pressedButton.setBackground(Color.green.darker());
        }
        else {
            pressedButton.setBackground(Color.red.darker());
            alt2.setBackground(Color.green.darker());
        }
    }

    public static void main(String[] args) {
        new QuizzGui().buildGui();
    }
}