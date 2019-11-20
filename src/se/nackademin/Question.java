package se.nackademin;

public class Question {

    private String question;
    private String rightAnswer;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;
    private String wrongAnswerThree;


    public Question(String question, String rightAnswer, String wrongAnswerOne,
                    String wrongAnswerTwo, String wrongAnswerThree) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.wrongAnswerOne = wrongAnswerOne;
        this.wrongAnswerTwo = wrongAnswerTwo;
        this.wrongAnswerThree = wrongAnswerThree;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getWrongAnswerOne() {
        return wrongAnswerOne;
    }

    public String getWrongAnswerTwo() {
        return wrongAnswerTwo;
    }
    public String getWrongAnswerThree() {
        return wrongAnswerThree;
    }
}
