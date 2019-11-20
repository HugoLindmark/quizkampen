package se.nackademin;

import java.net.Socket;
import java.util.List;

public class GameHandler extends Thread {
    private static int TOTALAMOUNTOFROUNDS = 2; //här properties
    private static int TOTALAMOUNTOFQUESTIONSPERROUND = 2; //här properties

    private Player whosTurn;
    private boolean isGameOn = false;

    private int state = 0;
    private int currentQuestion = 0;
    private final int MaxQuestionsPerGame = ((TOTALAMOUNTOFROUNDS * TOTALAMOUNTOFQUESTIONSPERROUND) * 2);

    private Category currentCategory;
    private List<Question> currentQuestionList;

    private Player player1;
    private Player player2;

    public GameHandler(Socket socket1, Socket socket2) {
        isGameOn = true;
        player1 = new Player(socket1, this);
        player2 = new Player(socket2, this);
        whosTurn = player1;
    }

    @Override
    public void run() {
        while (isGameOn) {

            while (whosTurn.equals(player1) && state != 4) {
                player1.writeToClient(protocolProcess());
                if (state != 2) {
                    checkInput(player1.readFromClient());
                }
            }
            System.out.println("ur första while");

            while (whosTurn.equals(player2) && state != 4) {
                System.out.println("inne i player 2 loopen");
                player2.writeToClient(protocolProcess());
                if (state != 2) {
                    checkInput(player2.readFromClient());
                }
            }
        }
        player1.writeToClient(protocolProcess());
        player2.writeToClient(protocolProcess());

    }

    Category[] categories = new Category[] {new Category("Musik"), new Category("Filmer")};
    Question tempQuestion = new Question("frågan", "rättsvar", "fel1", "fel2", "fel3");


    private void checkInput(String input) {
        if (input != null) {
            if (input.equalsIgnoreCase("Musik") || input.equalsIgnoreCase("Filmer")) {
                for (Category c : categories) {
                    if (input.equalsIgnoreCase(c.getCategoryType())) {
                        currentCategory = c;
                        //setCurrentQuestionList();
                    }
                }
            }
        }

    }

    private Response protocolProcess() {

        Response output = null;

        if (state == 0) {

            output = new Response(categories);
            state++;


        } else if (state == 1) {
//            output = new Response(currentQuestionList.get(currentQuestion));
            output = new Response(tempQuestion);
            currentQuestion++;
            if (currentQuestion == TOTALAMOUNTOFQUESTIONSPERROUND || currentQuestion == ((TOTALAMOUNTOFQUESTIONSPERROUND*2)+TOTALAMOUNTOFQUESTIONSPERROUND)) {
                state++;
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
//            output = new Response(currentQuestionList.get(currentQuestion));
            output = new Response(tempQuestion);
            currentQuestion++;

            if (currentQuestion == (TOTALAMOUNTOFQUESTIONSPERROUND*2)) { //kolla runderna
                state = 0;
            } else if (currentQuestion == MaxQuestionsPerGame) {
                state = 4;
                isGameOn = false;
            }

        } else if (state == 4) {

            output = new Response(player1.getTotalScore(), player2.getTotalScore());
        }

        return output;
    }

}

