package se.quizkampen.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private String numberOfRounds;
    private String numberOfQuestions;

    public Settings() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\se\\quizkampen\\server\\info.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.numberOfRounds = String.valueOf(properties.getProperty("rounds", "2"));
        this.numberOfQuestions = String.valueOf(properties.getProperty("questions", "2"));

    }

    public String getNumberOfRounds() {
        return numberOfRounds;
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }
}
