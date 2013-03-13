package com.jerver.http.route;

public interface Route {
    public byte[] resolve();
    public String getContentType();
}
