package se.nackademin;

public class Game {
    private MultiUserHandler player1;
    private MultiUserHandler player2;
    private int player1Score = 0;
    private int player2Score = 0;

    public Game(MultiUserHandler player1, MultiUserHandler player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
