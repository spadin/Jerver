package com.jerver.http.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MockSocket extends Socket {
    InputStream input;
    OutputStream output;
    public boolean exceptionOnClose = false;
    public boolean exceptionOnGetInputStream = false;
    public boolean exceptionOnGetOutputStream = false;

    public MockSocket(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public InputStream getInputStream() throws IOException {
        if(exceptionOnGetInputStream) {
            throw new IOException("MockSocket#getInputStream exceptionOnGetInputStream");
        }
        return input;
    }

    public OutputStream getOutputStream() throws IOException {
        if(exceptionOnGetOutputStream) {
            throw new IOException("MockSocket#getOutputStream exceptionOnGetOutputStream");
        }
        return output;
    }

    public void close() throws IOException {
        if(exceptionOnClose) {
            throw new IOException("MockSocket#close exceptionOnClose");
        }
        super.close();
    }
}