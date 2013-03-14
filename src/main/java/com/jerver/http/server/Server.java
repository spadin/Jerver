package com.jerver.http.server;

import com.jerver.http.route.FileRoute;
import com.jerver.http.route.Router;
import com.jerver.http.route.SleepyRoute;
import com.jerver.http.route.StringSubstitutionRoute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;

public class Server {
    private static final Router router = Router.INSTANCE;

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

    public static void main(String[]args) {
        router.setPublicDirectory("src/test/resources");
        router.addRoute("GET", "/hello", "Hello World!");
        router.addRoute("GET", "/time", new SleepyRoute(1000));
        router.addRoute("GET", "/form", new FileRoute(Paths.get("src/main/resources/form.html")));
        router.addRoute("POST", "/form", new StringSubstitutionRoute(Paths.get("src/main/resources/form.jrv.html")));

        new Server(9999);
    }
}
