package com.jerver.http.request;

public interface Request {
    public String getParam(String key);
    public String getHeader(String key);
}
