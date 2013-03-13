package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;

public class StringRoute implements Route {
    protected String responseText;

    public StringRoute(String responseText) {
        this.responseText = responseText;
    }
    public byte[] resolve(Request request, Response response) {
        return responseText.getBytes();
    }
    public String getContentType() {
        return "text/plain";
    }
}
