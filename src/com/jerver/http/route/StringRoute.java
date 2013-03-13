package com.jerver.http.route;

public class StringRoute implements Route {
    protected String responseText;

    public StringRoute(String responseText) {
        this.responseText = responseText;
    }
    public String resolve() {
        return responseText;
    }
}
