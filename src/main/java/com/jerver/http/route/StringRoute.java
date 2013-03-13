package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;

public class StringRoute implements Routable {
    protected String responseText;

    public StringRoute(String responseText) {
        this.responseText = responseText;
    }
    public void resolve(Request request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-Type: " + getContentType());
        response.setBody(getBody());
    }
    public String getContentType() {
        return "text/plain";
    }
    public byte[] getBody() {
        return responseText.getBytes();
    }
}
