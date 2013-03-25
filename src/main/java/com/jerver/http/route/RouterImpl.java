package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.FileVisitResult.*;

import java.util.HashMap;
import java.util.Map;

public class RouterImpl implements Router {
    Map<String, Routable> routes = new HashMap<String, Routable>();
    public static RouterImpl INSTANCE = new RouterImpl();
    public Path publicDirectoryPath;

    private RouterImpl() {

    }

    void reset() {
        INSTANCE = new RouterImpl();
    }
    public void addRoute(String method, String uri, String responseString) {
        addRoute(method, uri, new StringRoute(responseString));
    }

    public void addRoute(String method, String uri, Routable route) {
        routes.put(getKey(method, uri), route);
    }

    public String getKey(String method, String uri) {
        return method + " " + uri;
    }

    public void resolve(RequestImpl request, Response response) {
        Routable route = routes.get(getKey(request.method, request.uri));
        if(route == null) {
            route = new FourOhFourRoute();
            route.resolve(request, response);

        } else {
            route.resolve(request, response);
        }
    }

    public boolean routeExists(RequestImpl request) {
        return (routes.get(getKey(request.method, request.uri)) != null);
    }

    public void setPublicDirectory(String publicDirectoryPathStr) {
        publicDirectoryPath = Paths.get(publicDirectoryPathStr);
        DirectoryRouteVisitor directoryRoutesVisitor = new DirectoryRouteVisitor();
        try {
            Files.walkFileTree(publicDirectoryPath, directoryRoutesVisitor);
            System.out.println("Public directory set to " + publicDirectoryPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to walk file tree.");
        }
    }

    private class DirectoryRouteVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            String uri = "/" + publicDirectoryPath.relativize(path);
            addRoute("GET", uri, new FileRoute(path));
            return CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
            String uri = "/";
            String uriPath = publicDirectoryPath.relativize(path).toString();
            if(!uriPath.equals("")) {
                uri += uriPath + "/";
            }

            addRoute("GET", uri, new DirectoryRoute(publicDirectoryPath.relativize(path), publicDirectoryPath));

            return CONTINUE;
        }
    }
}
