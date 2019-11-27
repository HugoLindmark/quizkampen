package se.nackademin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

public class Client {
    private static int port = 12345;
    private static String hostAdress = "127.0.0.1";

    public static void main(String[] args) {
        QuizGui gui = new QuizGui();
        try (Socket socket = new Socket(hostAdress, port);
             PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
             ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream())
        ) {

            Response fromServer;

            System.out.println("Show waiting view");
            // call the categories

            while ((fromServer = (Response) inFromServer.readObject()) != null) {
                System.out.println("looping");
                if (fromServer.getCategories() != null) {
                    System.out.println("kategorier sektion ");
                    gui.setCategories((ArrayList<String>) fromServer.getCategories());
                    // switch panels to Category view
                    gui.switchTo(gui.categoryPanel); //reset

                } else if (fromServer.getQuestion() != null) {
                    // get the list with answers
                    // and the correct answer and the question
                    Question question = fromServer.getQuestion();
                    gui.setQuestion(question.getQuestion());
                    System.out.println("Question sektion " + question.getQuestion());
                    ArrayList<String> answers = question.getAnswers();
                    Collections.shuffle(answers);
                    gui.setAnswerAlternatives(answers);
                    gui.setCorrectAnswer(question.getRightAnswer());
                    // switch panels to Game view
                    gui.switchTo(gui.gamePanel);

                } else if (fromServer.getWaitingView()) {
                    System.out.println("showing waiting view");
                    // switch panels to Waiting view
                    gui.switchTo(gui.lobbyPanel);
                    // he has to wait
                } else if (fromServer.getMyScore() != null && fromServer.getOpponentScore() != null) {
                    if (fromServer.getMyScore() > fromServer.getOpponentScore()) {
                        gui.showResult("You won by " + fromServer.getMyScore() + "-" + fromServer.getOpponentScore() + "!");
                    } else if (fromServer.getMyScore() < fromServer.getOpponentScore()) {
                        gui.showResult("You lost by " + fromServer.getOpponentScore() + "-" + fromServer.getMyScore() + "!");
                    } else if (fromServer.getMyScore().equals(fromServer.getOpponentScore())) {
                        gui.showResult("The game is a draw!");
                    }
                    gui.switchTo(gui.resultPanel);
                }

                if (!fromServer.getWaitingView()) {
                    // get response and while loop hasresponded == true
                    while (!gui.hasResponded()) {
                        try {
                            Thread.sleep(100);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        }
                    if (gui.hasResponded()) {
                        String toServer = gui.getResponse();
                        System.out.println(toServer);

                        if (fromServer.getQuestion() != null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                System.out.println(e.getMessage());
                            }
                            if (toServer.equals(fromServer.getQuestion().getRightAnswer())) {
                                outToServer.println("rätt");
                            } else {
                                outToServer.println("fel");
                                System.out.println("Skicka QuizGUI response to server");
                            }
                        } else {
                            outToServer.println(toServer);
                        }
                    }
                }
            }


        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
