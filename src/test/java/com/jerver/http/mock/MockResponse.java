package com.jerver.http.mock;

import com.jerver.http.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockResponse extends Response {
    protected ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public boolean exceptionOnWrite = false;

    public MockResponse() {
        super();
        super.setOutputStream(outputStream);
    }

    public void write() throws IOException {
        if(exceptionOnWrite) {
            throw new IOException("MockResponse#write exceptionOnWrite");
        }
        super.write();
    }

    public String getResponseText() {
        try {
            write();
        } catch (IOException e) {
            System.out.println("Failed to write response in MockResponse#getResponseText");
        }
        String response = outputStream.toString();
        try {
            outputStream.close();
        }
        catch (IOException e) {
            System.out.println("Failed closing stream: " + e.getMessage());
        }
        return response;
    }
}
