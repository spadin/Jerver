package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;

public class StringRoute implements Routable {
    protected String responseText;
    private String contentType;

    public StringRoute(String responseText) {
        this(responseText, "text/plain");
    }

    public StringRoute(String responseText, String contentType) {
        this.responseText = responseText;
        this.contentType = contentType;
    }

    public void resolve(RequestImpl request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-Type: " + getContentType());
        response.setBody(getBody());
    }
    public String getContentType() {
        return contentType;
    }
    public byte[] getBody() {
        return responseText.getBytes();
    }
}
