package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Router {
    Map<String, Routable> routes = new HashMap<String, Routable>();
    public static final Router INSTANCE = new Router();
    private Path publicDirectoryPath;

    private Router() {

    }
    public void addRoute(String method, String uri, String responseString) {
        addRoute(method, uri, new StringRoute(responseString));
    }

    public void addRoute(String method, String uri, Path path) {
        Routable route = null;
        if(Files.isDirectory(path)) {
            route = new DirectoryRoute(path, publicDirectoryPath);
        }

        addRoute(method, uri, route);
    }

    public void addRoute(String method, String uri, Routable route) {
        routes.put(getKey(method, uri), route);
    }

    private String getKey(String method, String uri) {
        return method + " " + uri;
    }

    public void resolve(Request request, Response response) {
        Routable route = routes.get(getKey(request.method, request.uri));
        if(route == null) {
            route = new FourOhFourRoute();
            route.resolve(request, response);

        } else {
            route.resolve(request, response);
        }
    }

    public boolean routeExists(Request request) {
        return (routes.get(getKey(request.method, request.uri)) != null);
    }

    public void setPublicDirectory(String publicDirectoryPathStr) {
        publicDirectoryPath = Paths.get(publicDirectoryPathStr);
        addRoute("GET", "/", Paths.get(""));
    }
}
