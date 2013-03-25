package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;

public class FourOhFourRoute implements Routable {
    public FourOhFourRoute() {

    }

    public String getContentType() {
        return "text/html";
    }

    public byte[] getBody() {
        return "<!DOCTYPE html><html><body><h1>File not found</h1></body></html>".getBytes();
    }

    public void resolve(RequestImpl request, Response response) {
        response.setStatusCode(404);
        response.appendHeader("Content-type: " + getContentType());
        response.setBody(getBody());
    }
}
