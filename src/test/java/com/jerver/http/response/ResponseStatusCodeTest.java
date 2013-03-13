package com.jerver.http.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseStatusCodeTest {
    ResponseStatusCode statusCode = ResponseStatusCode.INSTANCE;

    @Test
    public void test200StatusCodePhrase() throws Exception {
        assertEquals("OK", statusCode.getPhrase(200));
    }

    @Test
    public void test404StatusCodePhrase() throws Exception {
        assertEquals("Not Found", statusCode.getPhrase(404));
    }

    @Test
    public void testGet200StatusLine() throws Exception {
        assertEquals("HTTP/1.1 200 OK", statusCode.getStatusLine(200));
    }

    @Test
    public void testGet403StatusLine() throws Exception {
        assertEquals("HTTP/1.1 403 Forbidden", statusCode.getStatusLine(403));
    }
}
