package se.nackademin;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class Database<T> {

    private static String readQuestion;
    private static String rightAnswer;
    private static String wrongAnswerOne;
    private static String wrongAnswerTwo;
    private static String wrongAnswerThree;

    private static Question question;
    private static List<String> categories = asList("Filmer", "Sport", "IT", "Djur");


    public static List<String> getCategories() {
        return categories;
    }

    // Lists to save different questions in different categories.
    private static List<Question> movieList = new ArrayList<>();
    private static List<Question> animalList = new ArrayList<>();
    private static List<Question> itList = new ArrayList<>();
    private static List<Question> sportList = new ArrayList<>();


    // Creates filepath for the .txt files we read from to create our Database.
    private static File moviePath = new File("\\src\\se\\nackademin\\movie.txt");
    private static File animalPath = new File("\\src\\se\\nackademin\\snakes.txt");
    private static File computerPath = new File("\\src\\se\\nackademin\\it.txt");
    private static File sportPath = new File("\\src\\se\\nackademin\\sports.txt");

    public static File getMoviePath() {
        return moviePath;
    }

    public static File getAnimalPath() {
        return animalPath;
    }

    public static File getComputerPath() {
        return computerPath;
    }

    public static File getSportPath() {
        return sportPath;
    }

    /*
        One Method that needs one filepath as parameter.
        Uses a Bufferdreader to read each line and creates a question and adds the question to relevant list based on the absoluteFilePath.
        Each list saves to the Database and can easily be fetched by getMovieList ex.
         */
    public static void readFile(File file) {
        BufferedReader reader;

        try {
            String readLine;
            reader = new BufferedReader(new FileReader(file));
            while ((readLine = reader.readLine()) != null) {
                readQuestion = readLine;
                rightAnswer = reader.readLine();
                wrongAnswerOne = reader.readLine();
                wrongAnswerTwo = reader.readLine();
                wrongAnswerThree = reader.readLine();
                question = new Question(readQuestion, rightAnswer, wrongAnswerOne,
                        wrongAnswerTwo, wrongAnswerThree);
                if (file.getAbsolutePath().equals(animalPath.getAbsolutePath())) {
                    animalList.add(question);
                    Database.saveAnimalList(animalList);
                } else if (file.getAbsolutePath().equals(moviePath.getAbsolutePath())) {
                    movieList.add(question);
                    Database.saveMovieList(movieList);
                } else if (file.getAbsolutePath().equals(computerPath.getAbsolutePath())) {
                    itList.add(question);
                    Database.saveItList(itList);
                } else
                    file.getAbsolutePath().equals(sportPath.getAbsolutePath());
                sportList.add(question);
                Database.saveSportList(sportList);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Methods to both save and get relevant list.
     */
    public static void saveMovieList(List<Question> movieList) {
        Database.movieList = movieList;
    }

    public static void saveAnimalList(List<Question> animalList) {
        Database.animalList = animalList;
    }

    public static void saveItList(List<Question> itList) {
        Database.itList = itList;
    }

    public static void saveSportList(List<Question> sportList) {
        Database.sportList = sportList;
    }

    public static List<Question> getSportList() {
        return sportList;
    }

    public static List<Question> getMovieList() {
        return movieList;
    }

    public static List<Question> getAnimalList() {
        return animalList;
    }

    public static List<Question> getItList() {
        return itList;
    }

    public static void main(String[] args) {


    }
}
