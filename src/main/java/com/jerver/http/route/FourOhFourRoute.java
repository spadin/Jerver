package com.jerver.http.route;

public class FourOhFourRoute implements Route {
    public FourOhFourRoute() {

    }

    public String getContentType() {
        return "text/html";
    }

    public byte[] resolve() {
        return "<!DOCTYPE html><html><body><h1>File not found</h1></body></html>".getBytes();
    }
}
