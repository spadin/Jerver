package com.jerver.http.server;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.ResponseImpl;
import com.jerver.http.route.*;
import com.jerver.resource.Resource;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerImpl implements Server {
    private static final RouterImpl router = RouterImpl.INSTANCE;
    public static boolean RUNNING = true;

    int port;
    ServerSocket listener;

    public ServerImpl(int port) {
        this.port = port;
    }

    public Thread runAsThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.run();
        return thread;
    }

    public void run() {
        try {
            acquirePort();

            while (ServerImpl.RUNNING) {
                Socket socket = listener.accept();
                RequestImpl request = new RequestImpl();
                ResponseImpl response = new ResponseImpl();
                Connection connection = new Connection(socket, request, response);
                runAsThread(connection);
            }
        } catch (Exception e) {
            System.out.println("listener.accept failed: " + e);
        }
    }

    public void acquirePort() {
        try {
            listener = new ServerSocket(port);
            System.out.println("Jerver started at http://localhost:" + port);
        } catch (IOException e) {
            System.out.println("Failed to acquire port" + e);
        }
    }
    public void setPublicDirectory(String publicDir) {
        router.setPublicDirectory(publicDir);
    }

    public void addDefaultRoutes() {
        router.addRoute("GET", "/hello", "Hello World!");
        router.addRoute("GET", "/time", new SleepyRoute(1000));
        router.addRoute("GET", "/form", new StringRoute(Resource.getStringForResource("form.html"), "text/html"));
        router.addRoute("POST", "/form", new PostPrintoutRoute());
    }

    public static void main(String[] args) {
        String publicDir = getOption("-d", "", args);
        int port = getOption("-p", 9999, args);

        ServerImpl server = new ServerImpl(port);
        server.setPublicDirectory(publicDir);
        server.addDefaultRoutes();
        server.run();
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

    public void stop() {
        RUNNING = false;
    }
}
