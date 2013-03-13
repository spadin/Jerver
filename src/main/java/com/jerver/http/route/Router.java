package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import com.jerver.http.response.ResponseStatusCode;

import java.util.HashMap;
import java.util.Map;

public class Router {
    Map<String, Route> routes = new HashMap<String, Route>();
    protected static final ResponseStatusCode statusCode = ResponseStatusCode.INSTANCE;
    public static final Router INSTANCE = new Router();

    private Router() {

    }
    public void addRoute(String method, String uri, String responseString) {
        routes.put(getKey(method, uri), new StringRoute(responseString));
    }

    public void addRoute(String method, String uri, Route route) {
        routes.put(getKey(method, uri), route);
    }

    private String getKey(String method, String uri) {
        return method + " " + uri;
    }

    public Response resolve(Request request, Response response) {
        Route route = routes.get(getKey(request.method, request.uri));
        if(route == null) {
            response.setStatusCode(404);
        } else {
            response.setStatusCode(200);
            response.appendHeader("Content-Type: " + route.getContentType());
            response.setBody(route.resolve());
        }
        return response;
    }

    public boolean routeExists(Request request) {
        return (routes.get(getKey(request.method, request.uri)) != null);
    }

    public void setPublicDirectory(String directoryPath) {
        Route route = new DirectoryRoute("", directoryPath);
        addRoute("GET", "/", route);
    }
}
