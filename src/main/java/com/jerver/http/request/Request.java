package com.jerver.http.request;

import java.io.*;
import java.util.HashMap;

public class Request {
    private final RequestInputStreamParser httpRequestInputStream = new RequestInputStreamParser(this);
    public HashMap<String, String> header = new HashMap<String, String>();
    public HashMap<String, String> param = new HashMap<String, String>();
    public String method, uri, body;
    public byte[] bodyContent;


    public Request() {

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
            param = Params.generateParams(getBody());
        } else if (method.equals("GET")) {
            String[] parts = uri.split("\\?", 2);
            if(parts.length == 2) {
                String queryString = parts[1];
                param = Params.generateParams(queryString);
            }
        }
    }

    public void parseInputStream(InputStream inputStream) {
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
