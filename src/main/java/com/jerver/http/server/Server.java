package com.jerver.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(int port) {
        try {
            ServerSocket listener = new ServerSocket(port);
            Socket socket;
            while (true) {
                socket = listener.accept();
                Connection connection = new Connection(socket);
                Thread t = new Thread(connection);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("IOException on binding to port: " + port);
            e.printStackTrace();
        }
    }
}
