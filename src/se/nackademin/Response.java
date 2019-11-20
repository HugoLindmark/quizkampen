package se.nackademin;

import java.io.Serializable;

public class Response implements Serializable{
    private Category[] categories;
    private Question question;
    private boolean waitingView;
    private Integer myScore;
    private Integer opponentScore;

    public Response(Category[] array) {
        this.categories = array;
    }
    public Response(Question question) {
        this.question = question;
    }
    public Response(boolean waitingView) {
        this.waitingView = waitingView;
    }
    public Response(int myScore, int opponentScore) {
        this.myScore = myScore;
        this.opponentScore = opponentScore;
    }

    public Category[] getCategories() {
        return categories;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean getWaitingView() {
        return waitingView;
    }

    public Integer getMyScore() {
        return myScore;
    }

    public Integer getOpponentScore() {
        return opponentScore;
    }
}
