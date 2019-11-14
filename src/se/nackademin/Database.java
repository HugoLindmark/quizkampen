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

    private static List<Question> movieList = new ArrayList<>();
    private static List<Question> animalList = new ArrayList<>();

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
                movieList.add(question);
            }
            Database.saveMovieList(movieList);
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
                animalList.add(question);
            }
            Database.saveAnimalList(animalList);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Exception E");
            e.printStackTrace();
        }
    }

    public static void saveMovieList(List<Question> questionList){
        Database.movieList = questionList;
    }

    public static void saveAnimalList(List<Question> animalList){
        Database.animalList = animalList;
    }

    public List<Question>  getMovieList(){
        return movieList;
    }

    public List<Question> getAnimalList(){
        return animalList;
    }
}
