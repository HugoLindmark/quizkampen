package se.nackademin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {
    private Socket socket;
    private int totalScore = 0;
    private int currentScore = 0;
    private GameHandler gameHandler;
    ObjectOutputStream outToClient;
    BufferedReader inFromClient;

    public Player(Socket socket, GameHandler gameHandler) {
        this.socket = socket;
        this.gameHandler = gameHandler;
        try {
            outToClient = new ObjectOutputStream(socket.getOutputStream());
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void writeToClient(Response response) {
        try {
            outToClient.writeObject(response);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String readFromClient() {
        String tempLine = null;
        try {
            tempLine = inFromClient.readLine();

            if (tempLine.equalsIgnoreCase("rätt")) {
                currentScore++;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tempLine;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void increaseTotalScore() {
        totalScore++;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }



}
