package se.quizkampen.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {
    public static int port = 12345;

    public static void main(String[] args) throws IOException {
        System.out.println("Server: server is online");
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {

            try {
                GameHandler g = new GameHandler(serverSocket.accept(), serverSocket.accept());
                g.start();

            } catch (IOException e) {
                System.out.println("FEL: " + e.getMessage());
            }
        }


    }
}
