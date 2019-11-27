package se.quizkampen.client;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    private String question;
    private String rightAnswer;
    private ArrayList<String> answers = new ArrayList<>();


    public Question(String question, String rightAnswer, String wrongAnswerOne,
                    String wrongAnswerTwo, String wrongAnswerThree) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        answers.add(rightAnswer);
        answers.add(wrongAnswerOne);
        answers.add(wrongAnswerTwo);
        answers.add(wrongAnswerThree);
    }

    public String getQuestion() {

        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
