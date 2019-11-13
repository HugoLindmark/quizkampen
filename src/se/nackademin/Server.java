package se.nackademin;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    MultiUserHandler clientHandler;

    public Server(){
        try(ServerSocket serverSocket = new ServerSocket(12345)) {

            while (true) {
                Socket socket = serverSocket.accept();
                Thread t = new Thread(new MultiUserHandler(socket));
                t.start();
            }

        }catch (IOException e){
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }


}
