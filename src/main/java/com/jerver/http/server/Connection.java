package com.jerver.http.server;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.ResponseImpl;
import com.jerver.http.route.RouterImpl;

import java.net.Socket;

public class Connection implements Runnable {
    protected Socket socket;
    protected RequestImpl request;
    protected ResponseImpl response;
    private static final RouterImpl router = RouterImpl.INSTANCE;

    public Connection(Socket socket, RequestImpl request, ResponseImpl response) {
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
            e.printStackTrace(System.out);
        }
        return;
    }
}
