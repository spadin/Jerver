package com.jerver.http.route;

public interface Router {
    public void addRoute(String method, String uri, String responseString);
    public void addRoute(String method, String uri, Routable route);
}
