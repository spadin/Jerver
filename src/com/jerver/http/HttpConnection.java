package com.jerver.http;

import java.io.*;
import java.net.Socket;

public class HttpConnection implements Runnable {
    protected Socket server;

    public HttpConnection(Socket server) {
        this.server = server;
    }

    public void run() {
        HttpRequest request = new HttpRequest();
        HttpResponse response = new HttpResponse();
        try {
            request.parseInputStream(server.getInputStream());
            response.setOutputStream(server.getOutputStream());

            response.setStatusCode(200);
            response.write();

            System.out.println(request.method + " " + request.uri);

            server.close();
        } catch(IOException e) {}
    }
}
