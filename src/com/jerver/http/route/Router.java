package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import com.jerver.http.response.ResponseStatusCode;

import java.util.HashMap;
import java.util.Map;

public class Router {
    Map<String, Route> routes = new HashMap<>();
    protected static final ResponseStatusCode statusCode = ResponseStatusCode.INSTANCE;
    public static final Router INSTANCE = new Router();

    private Router() {

    }
    public void addRoute(String method, String uri, String responseString) {
        routes.put(getKey(method, uri), new StringRoute(responseString));
    }

    private String getKey(String method, String uri) {
        return method + " " + uri;
    }

    public String resolve(String method, String uri) {
        Route route = routes.get(getKey(method, uri));
        String response = null;
        if(route == null) {
            response = buildResponse(404, "");
        } else {
            response = buildResponse(200, route.resolve());
        }

        return response;
    }

    public Response resolve(Request request, Response response) {
        // System.out.println("Router resolving: " + " " + request.method + " " + request.uri);
        Route route = routes.get(getKey(request.method, request.uri));
        if(route == null) {
            response.setStatusCode(404);
        } else {
            response.setStatusCode(200);
            response.appendHeader("Content-Type: text/plain");
            response.setBody(route.resolve().getBytes());
        }
        return response;
    }

    private String buildResponse(int statusCodeInt, String responseBody) {
        return statusCode.getStatusLine(statusCodeInt) +"\r\n\r\n"+ responseBody;
    }

    public boolean routeExists(Request request) {
        return (routes.get(getKey(request.method, request.uri)) != null);
    }
}
