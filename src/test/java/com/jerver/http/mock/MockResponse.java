package com.jerver.http.mock;

import com.jerver.http.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MockResponse extends Response {
    protected ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public MockResponse() {
        super();
        super.setOutputStream(outputStream);
    }

    public String getResponseText() {
        super.write();
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
