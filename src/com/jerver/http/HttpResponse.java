package com.jerver.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class HttpResponse {
    public int statusCodeInt;
    private final static HttpResponseStatusCode statusCode = HttpResponseStatusCode.INSTANCE;
    private OutputStream outputStream;

    public HttpResponse() {

    }

    public void setStatusCode(int statusCodeInt) {
        this.statusCodeInt = statusCodeInt;
    }

    public String getStatusLine() {
        return statusCode.getStatusLine(statusCodeInt);
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeCRLF(OutputStream outputStream) throws IOException {
        outputStream.write(13);
        outputStream.write(10);
    }

    public void write() {

        try {
            outputStream.write(getStatusLine().getBytes());
            writeCRLF(outputStream);
        } catch (IOException e) {
            System.out.println("Failed writing response");
        }
    }
}
