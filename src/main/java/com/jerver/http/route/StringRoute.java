package com.jerver.http.route;

public class StringRoute implements Route {
    protected String responseText;

    public StringRoute(String responseText) {
        this.responseText = responseText;
    }
    public byte[] resolve() {
        return responseText.getBytes();
    }
    public String getContentType() {
        return "text/plain";
    }
}
