package com.jerver.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public HttpServer(int port) {
        try {
            ServerSocket listener = new ServerSocket(port);
            Socket server;
            while (true) {
                server = listener.accept();
                HttpConnection connection = new HttpConnection(server);
                Thread t = new Thread(connection);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("IOException on binding to port: " + port);
            e.printStackTrace();
        }
    }
}
