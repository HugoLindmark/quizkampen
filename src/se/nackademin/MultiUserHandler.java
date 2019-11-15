package se.nackademin;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Handler;

public class MultiUserHandler implements Runnable{

    private Socket socket;
    private Database db;

    public MultiUserHandler(Socket socket) {
        this.socket = socket;

    }


    @Override
    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());) {

            do {
                //output.writeObject(db.getRandomQuestion());

            } while (input.readObject() != null);
        }catch (Exception e){

        }
    }



}
