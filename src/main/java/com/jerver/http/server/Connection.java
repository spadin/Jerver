package com.jerver.http.server;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import com.jerver.http.route.Router;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    protected Socket socket;
    protected Request request;
    protected Response response;
    private static final Router router = Router.INSTANCE;

    public Connection(Socket socket, Request request, Response response) {
        this.socket = socket;
        this.request = request;
        this.response = response;
    }

    public void run() {
        try {
            request.parseInputStream(socket.getInputStream());
            response.setOutputStream(socket.getOutputStream());
            router.resolve(request, response);
            response.write();
            System.out.println(response.status + " " + request.method + " " + request.uri);
            socket.close();

        } catch(Exception e) {
            System.out.println("Failed to run connection.");
        }
        return;
    }
}
