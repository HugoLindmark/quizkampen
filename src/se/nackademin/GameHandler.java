package se.nackademin;

import javax.xml.crypto.Data;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class GameHandler<T> extends Thread {
    private int TOTALAMOUNTOFROUNDS;
    private int TOTALAMOUNTOFQUESTIONSPERROUND;

    private Player whosTurn;
    private boolean isGameOn = false;

    private int state = 0;
    private int currentQuestion = 0;
    private int MaxQuestionsPerGame;

    private List<Question> currentQuestionList;
    private int questionIndex = 0;

    private Player player1;
    private Player player2;

    public GameHandler(Socket socket1, Socket socket2) {
        isGameOn = true;
        player1 = new Player(socket1, this);
        player2 = new Player(socket2, this);
        whosTurn = player1;
        Settings gameSettings = new Settings();
        TOTALAMOUNTOFQUESTIONSPERROUND = Integer.parseInt(gameSettings.getNumberOfQuestions());
        TOTALAMOUNTOFROUNDS = Integer.parseInt(gameSettings.getNumberOfRounds());
        MaxQuestionsPerGame = ((TOTALAMOUNTOFROUNDS * TOTALAMOUNTOFQUESTIONSPERROUND) * 2);
    }

    @Override
    public void run() {
        while (isGameOn) {

            while (whosTurn.equals(player1) && state != 4) {
                player1.writeToClient(protocolProcess(player1));
                if (state != 2) {
                    checkInput(player1.readFromClient());
                }
            }
            while (whosTurn.equals(player2) && state != 4) {
                player2.writeToClient(protocolProcess(player2));
                if (state != 2) {
                    checkInput(player2.readFromClient());
                }
            }
        }
        player1.writeToClient(protocolProcess(player1));
        player2.writeToClient(protocolProcess(player2));
    }

    private void checkInput(String input) {
        try {
            if (input == null) {
                System.out.println("No input");
            }
            switch (input) {
                case "Filmer":
                    Database.readFile(Database.getMoviePath());
                    currentQuestionList = Database.getMovieList();
                    Collections.shuffle(currentQuestionList);
                    break;
                case "Sport":
                    Database.readFile(Database.getSportPath());
                    currentQuestionList = Database.getSportList();
                    Collections.shuffle(currentQuestionList);
                    break;
                case "Djur":
                    Database.readFile(Database.getAnimalPath());
                    currentQuestionList = Database.getAnimalList();
                    Collections.shuffle(currentQuestionList);
                    break;
                case "IT":
                    Database.readFile(Database.getComputerPath());
                    currentQuestionList = Database.getItList();
                    Collections.shuffle(currentQuestionList);
                    break;
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private Response protocolProcess(Player tempPlayer) {

        Response output = null;

        if (state == 0) {

            output = new Response(Database.getCategories());
            state++;


        } else if (state == 1) {
            output = new Response(currentQuestionList.get(questionIndex));
            currentQuestion++;
            questionIndex++;

            if (currentQuestion == TOTALAMOUNTOFQUESTIONSPERROUND || currentQuestion == ((TOTALAMOUNTOFQUESTIONSPERROUND * 2) +
                    TOTALAMOUNTOFQUESTIONSPERROUND)) {
                state++;
                questionIndex = 0;
            }

        } else if (state == 2) {
            output = new Response(true);

            if (whosTurn.equals(player1)) {
                whosTurn = player2;
            } else {
                whosTurn = player1;
            }

            state++;


        } else if (state == 3) {
            output = new Response(currentQuestionList.get(questionIndex));
            currentQuestion++;
            questionIndex++;

            if (currentQuestion == (TOTALAMOUNTOFQUESTIONSPERROUND * 2)) {
                checkPlayersScore();
                state = 0;
                questionIndex = 0;
                currentQuestionList = null;

            } else if (currentQuestion == MaxQuestionsPerGame) {
                checkPlayersScore();
                state = 4;
                isGameOn = false;
            }

        } else if (state == 4) {
            if (tempPlayer.equals(player1)) {
                output = new Response(player1.getTotalScore(), player2.getTotalScore());
            } else {
                output = new Response(player2.getTotalScore(), player1.getTotalScore());
            }
        }
        return output;
    }

    private void checkPlayersScore() {
        if (player1.getCurrentScore() < player2.getCurrentScore()) {
            player2.increaseTotalScore();
        } else if (player1.getCurrentScore() > player2.getCurrentScore()) {
            player1.increaseTotalScore();
        } else if (player1.getCurrentScore() == player2.getCurrentScore()) {
            player1.increaseTotalScore();
            player2.increaseTotalScore();
        }
        player1.resetCurrentScore();
        player2.resetCurrentScore();
    }

}

