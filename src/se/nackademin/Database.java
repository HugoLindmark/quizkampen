package se.nackademin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String readQuestion;
    private String rightAnswer;
    private String wrongAnswerOne;
    private String wrongAnswerTwo;
    private String wrongAnswerThree;

    private Question question;
    private Question randomQ;

    private static List<Question> questionList = new ArrayList<>();

    public void readMovieFile(){

        BufferedReader reader;

        try {
            String readLine;
            reader = new BufferedReader(new FileReader("C:\\Users\\hugol\\IdeaProjects\\quizkampenV2\\src\\se\\nackademin\\movie.txt"));

            while ((readLine = reader.readLine()) != null){
                readQuestion = reader.readLine();
                rightAnswer = reader.readLine();
                wrongAnswerOne = reader.readLine();
                wrongAnswerTwo = reader.readLine();
                wrongAnswerThree = reader.readLine();
                question = new Question(readQuestion,rightAnswer,wrongAnswerOne,
                        wrongAnswerTwo,wrongAnswerThree);
                questionList.add(question);
            }
            Database.saveList(questionList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readSnakeFile(){

        BufferedReader reader;

        try {
            String readLine;
            reader = new BufferedReader(new FileReader("C:\\Users\\hugol\\IdeaProjects\\quizkampenV2\\src\\se\\nackademin\\snakes.txt"));

            while ((readLine = reader.readLine()) != null){
                readQuestion = reader.readLine();
                rightAnswer = reader.readLine();
                wrongAnswerOne = reader.readLine();
                wrongAnswerTwo = reader.readLine();
                wrongAnswerThree = reader.readLine();
                question = new Question(readQuestion,rightAnswer,wrongAnswerOne,
                        wrongAnswerTwo,wrongAnswerThree);
                questionList.add(question);
            }
            Database.saveList(questionList);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Exception E");
            e.printStackTrace();
        }
    }

    public static void saveList(List<Question> questionList){
        Database.questionList = questionList;
    }

    public List<Question>  getQuestionListList(){
        return questionList;
    }

    public Question  getRandomQuestion(){
        randomQ = questionList.get(1);
        return randomQ;
    }
}
