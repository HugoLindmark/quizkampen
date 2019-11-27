package se.quizkampen.server;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{
    private List<String> categories;
    private Question question;
    private boolean waitingView;
    private Integer myScore;
    private Integer opponentScore;

    public Response(List<String> categories) {
        this.categories = categories;
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

    public List<String> getCategories() {
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
