package com.jerver.http.response;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Response {
    public int status;
    private final static ResponseStatusCode statusCode = ResponseStatusCode.INSTANCE;
    private OutputStream outputStream;
    public List<String> headers = new ArrayList<String>();
    private byte[] body;

    public Response() {

    }

    public void setStatusCode(int statusCodeInt) {
        this.status = statusCodeInt;
    }

    public String getStatusLine() {
        return statusCode.getStatusLine(status);
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeCRLF(OutputStream outputStream) throws IOException {
        outputStream.write(13);
        outputStream.write(10);
    }

    public void write() throws IOException {
        outputStream.write(getStatusLine().getBytes());
        writeCRLF(outputStream);
        for(String header: headers) {
            outputStream.write(header.getBytes());
            writeCRLF(outputStream);
        }

        if(hasBody()) {
            String contentLengthStr = "Content-Length: " + body.length;
            outputStream.write(contentLengthStr.getBytes());
            writeCRLF(outputStream);
        }

        writeCRLF(outputStream);

        if(hasBody()) {
            outputStream.write(body);
        }

        outputStream.close();
    }

    public void appendHeader(String headerString) {
        if(headerString != null) {
            headers.add(headerString);
        }
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public boolean hasBody() {
        return (this.body != null);
    }
}
