package se.nackademin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static int port = 12345;
    private static String hostAdress = "127.0.0.1";
    private QuizGui quiGUI;
    public volatile boolean responded = false;

    public Client() {
        this.quiGUI = new QuizGui(this);
    }

    public static void main(String[] args) {
        Client client = new Client();
        try (Socket socket = new Socket(hostAdress, port);
             PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
             ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream())
        ) {

            Response fromServer;

            System.out.println("Show waiting view");
            // call the categories

            while ((fromServer = (Response) inFromServer.readObject()) != null) {
                System.out.println("looping");
                client.responded = false;
                if (fromServer.getCategories() != null) {
                    System.out.println("kategorier sektion ");
                    client.quiGUI.setCategories((ArrayList<String>) fromServer.getCategories());
                    // switch panels to Category view
                    client.quiGUI.switchPanel(client.quiGUI.getCurrentPanel(), client.quiGUI.categoryPanel);
                    while (!client.responded) {
                        try {
                            System.out.println("vänta på klient respons");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("QuizGUI has responded");
                    if (client.responded) {
                        outToServer.println(client.quiGUI.getResponse());
                        System.out.println("Skickat klient response");
                    }
                } else if (fromServer.getQuestion() != null) {
                    client.responded = false;
                    // get the list with answers
                    // and the correct answer and the question
                    Question question = fromServer.getQuestion();
                    client.quiGUI.setQuestion(question.getQuestion());
                    System.out.println("Question sektion " + question.getQuestion());
                    ArrayList<String> answers = question.getAnswers();
                    Collections.shuffle(answers);
                    client.quiGUI.setAnswerAlternatives(answers);
                    client.quiGUI.setCorrectAnswer(question.getRightAnswer());
                    // switch panels to Game view
                    client.quiGUI.resetButtons();
                    client.quiGUI.switchPanel(client.quiGUI.getCurrentPanel(), client.quiGUI.gamePanel);
                    while (!client.responded) {
                        try {
                            System.out.println("vänta på klient response");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("klient has responded");
                    if (client.responded) {
                        String toServer = client.quiGUI.getResponse();
                        if (toServer.equals(fromServer.getQuestion().getRightAnswer())) {
                            outToServer.println("rätt");
                        } else {
                            outToServer.println(toServer);
                            System.out.println("Skicka QuizGUI response to server");
                        }

                    }
                } else if (fromServer.getWaitingView()) {
                    System.out.println("showing waiting view");
                    // switch panels to Waiting view
                    client.quiGUI.switchPanel(client.quiGUI.getCurrentPanel(), client.quiGUI.lobbyPanel);
                    // he has to wait
                } else if (fromServer.getMyScore() != null && fromServer.getOpponentScore() != null) {
                    if (fromServer.getMyScore() > fromServer.getOpponentScore()) {
                        client.quiGUI.showResult("You won by " + fromServer.getMyScore() + "-" + fromServer.getOpponentScore() + "!");
                    } else if (fromServer.getMyScore() < fromServer.getOpponentScore()) {
                        client.quiGUI.showResult("You lost by " + fromServer.getOpponentScore() + "-" + fromServer.getMyScore() + "!");
                    } else if (fromServer.getMyScore().equals(fromServer.getOpponentScore())) {
                        client.quiGUI.showResult("The game is a draw!");
                    }


                    //show the result
                    client.quiGUI.switchPanel(client.quiGUI.getCurrentPanel(), client.quiGUI.resultPanel);
                }
               /* else if (!fromServer.getWaitingView()) {
                    // get response and while loop hasresponded == true

                    if (!client.quiGUI.hasResponded()) {
                        outToServer.println(client.quiGUI.getResponse());
                    }
                } */
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
