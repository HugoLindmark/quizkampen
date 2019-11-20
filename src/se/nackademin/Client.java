package se.nackademin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static int port = 12345;
    private static String hostAdress = "127.0.0.1";

    public static void main(String[] args) {
        try (Socket socket = new Socket(hostAdress, port);
             PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
             ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream())
        ) {
            //QuizGui quiGUI = new QuizGui();

            Scanner scanner = new Scanner(System.in);
            String fromUser;
            String serverMessage;
            Response fromServer;

            System.out.println("Show waiting view");

            while ((fromServer = (Response) inFromServer.readObject()) != null) {
                System.out.println("in client "+fromServer);
                if (fromServer.getCategories() != null) {
                    for (Category c : fromServer.getCategories()) {
                        System.out.println(c.getCategoryType());
                    }
                }
                if (fromServer.getQuestion() != null) {
                    System.out.println(fromServer.getQuestion().getQuestion());
                    System.out.println(fromServer.getQuestion().getRightAnswer());
                    System.out.println(fromServer.getQuestion().getWrongAnswerOne());
                    System.out.println(fromServer.getQuestion().getWrongAnswerTwo());
                    System.out.println(fromServer.getQuestion().getWrongAnswerThree());
                }
                if (fromServer.getWaitingView()) {
                    System.out.println("showing waiting view");
                }
                if (fromServer.getMyScore() != null && fromServer.getOpponentScore() != null) {
                    System.out.println(fromServer.getMyScore() + " opp:" + fromServer.getOpponentScore());
                }

                if (!fromServer.getWaitingView()) {
                    fromUser = scanner.nextLine();
                    outToServer.println(fromUser);
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

