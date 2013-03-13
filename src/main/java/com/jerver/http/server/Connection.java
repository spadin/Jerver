package com.jerver.http.server;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import com.jerver.http.route.Router;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    protected Socket server;
    private static final Router router = Router.INSTANCE;

    public Connection(Socket server) {
        this.server = server;
    }

    public void run() {
        Request request = new Request();
        Response response = new Response();

        try {
            request.parseInputStream(server.getInputStream());
            response.setOutputStream(server.getOutputStream());

            router.resolve(request, response);
            response.write();

            if(request.method != null &&
               request.uri != null) {
                System.out.println(response.status + " " + request.method + " " + request.uri);
            }

            server.close();
        } catch(IOException e) {
            System.out.println("Failed to run connection");
        }
    }
}
