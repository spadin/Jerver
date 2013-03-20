package com.jerver.http.server;

public interface Server extends Runnable {
    public void run();
    public void setPublicDirectory(String directory);
}
