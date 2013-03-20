package com.jerver.http.response;

public interface Response {
    public void setStatusCode(int statusCodeInt);
    public void appendHeader(String headerString);
    public void setBody(byte[] body);
}
