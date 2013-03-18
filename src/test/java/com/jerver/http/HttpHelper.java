package com.jerver.http;

import com.jerver.http.request.Request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HttpHelper {
    public static InputStream buildRequestInputStream(String method, String uri) {
        return buildRequestInputStream(method, uri, null);
    }

    public static InputStream buildRequestInputStream(String method, String uri, String body) {
        String req = method+ " " + uri + " HTTP/1.1\r\n" +
                "Host: localhost:9999\r\n" +
                "Accept: */*\r\n"; // "\r\n\r\n";

        if(body != null) {
            req += "Content-Length: " + body.length() + "\r\n";
        }
        req += "\r\n";
        if(body != null) {
            req += body;
        }

        return new ByteArrayInputStream(req.getBytes());
    }

    public static Request buildRequest(String method, String uri) {
        Request request = new Request();
        request.parseInputStream(buildRequestInputStream(method, uri));

        return request;
    }
}

