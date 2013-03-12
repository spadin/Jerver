package com.jerver.http;

import java.io.*;
import java.util.HashMap;

public class HttpRequest {
    private final HttpRequestInputStreamParser httpRequestInputStream = new HttpRequestInputStreamParser(this);
    public HashMap<String,String> header = new HashMap<>();
    public HashMap<String, String> param = new HashMap<>();
    public String method, uri, body;
    public byte[] bodyContent;


    public HttpRequest() {

    }

    void setRequestHeader(String requestHeader) {
        String[] headerParts = requestHeader.split(": ", 2);
        header.put(headerParts[0], headerParts[1]);
    }

    void setRequestLine(String requestLine) {
        String[] lineParts = requestLine.split(" ");
        method = lineParts[0];
        uri = lineParts[1];
    }

    void generateParams() {
        if(method.equals("POST")) {
            param = HttpParams.generateParams(getBody());
        } else if (method.equals("GET")) {
            String[] parts = uri.split("\\?", 2);
            if(parts.length == 2) {
                String queryString = parts[1];
                param = HttpParams.generateParams(queryString);
            }
        }
    }

    void parseInputStream(InputStream inputStream) {
        httpRequestInputStream.parse(inputStream);
        generateParams();
    }

    boolean hasContentLength() {
        return (header.get("Content-Length") != null);
    }

    int getContentLength() {
        return Integer.parseInt(header.get("Content-Length"));
    }

    void setBody(byte[] bytes) {
        bodyContent = bytes;
    }

    String getBody() {
        if(body == null) {
           body = new String(bodyContent);
        }

        return body;
    }
}
