package com.jerver.http.mock;

import com.jerver.http.response.Response;

import java.io.ByteArrayOutputStream;

public class MockResponse extends Response {
    protected ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public MockResponse() {
        super();
        super.setOutputStream(outputStream);
    }

    public String getResponseText() {
        super.write();
        return outputStream.toString();
    }
}
