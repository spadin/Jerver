package com.jerver.http.server;

import com.jerver.http.route.*;
import com.jerver.resource.Resource;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Scanner;

public class Server {
    private static final Router router = Router.INSTANCE;

    public Server(int port) {
        try {
            ServerSocket listener = new ServerSocket(port);
            System.out.println("Jerver started at http://localhost:" + port);
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

    public static void main(String[] args) {
        String publicDir = getOption("-d", "", args);
        int port = getOption("-p", 9999, args);

        router.setPublicDirectory(publicDir);
        router.addRoute("GET", "/hello", "Hello World!");
        router.addRoute("GET", "/time", new SleepyRoute(1000));
        router.addRoute("GET", "/form", new StringRoute(Resource.getStringForResource("form.html"), "text/html"));
        router.addRoute("POST", "/form", new StringSubstitutionRoute(Resource.getStringForResource("form.jrv.html")));

        new Server(port);
    }

    public static String getOption(String code, String def, String[] args) {
        Scanner s = new Scanner(StringUtils.join(args, " "));
        String option = def;
        if(s.findInLine(code) != null) {
            option = s.next();
        }
        return option;
    }

    public static int getOption(String code, int def, String[] args) {
        String optStr = getOption(code, def+"", args);
        int opt = def;

        try {
            opt = Integer.parseInt(optStr);
        } catch (NumberFormatException e) {
            System.out.println(code + " option must be numeric");
        }

        return opt;
    }
}
