package com.jerver.http.server;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import com.jerver.http.route.Router;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    protected Socket socket;
    private static final Router router = Router.INSTANCE;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        Request request = new Request();
        Response response = new Response();

        try {
            request.parseInputStream(socket.getInputStream());
            response.setOutputStream(socket.getOutputStream());

            if(!request.method.equals("null") && !request.uri.equals("null")) {
                router.resolve(request, response);
                response.write();
                System.out.println(response.status + " " + request.method + " " + request.uri);
            } else {
                System.out.println("No request method/uri");
            }

            socket.close();
        } catch(IOException e) {
            System.out.println("Failed to run connection");
        }
    }
}
