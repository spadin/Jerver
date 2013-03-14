package com.jerver.http.server;

import com.jerver.http.route.Router;
import com.jerver.http.route.SleepyRoute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Router router = Router.INSTANCE;

    public Server(int port) {
        ExecutorService executor = Executors.newFixedThreadPool(30);
        try {
            ServerSocket listener = new ServerSocket(port);
            Socket socket;
            while (true) {
                socket = listener.accept();
                Connection connection = new Connection(socket);
                executor.execute(connection);
            }
        } catch (IOException e) {
            System.out.println("IOException on binding to port: " + port);
            e.printStackTrace();
        }
    }

    public static void main(String[]args) {
        router.setPublicDirectory("src/test/resources");
        router.addRoute("GET", "/hello", "Hello World!");
        router.addRoute("GET", "/time", new SleepyRoute(1000));

        new Server(9999);
    }
}
