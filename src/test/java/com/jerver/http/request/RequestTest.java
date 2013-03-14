package com.jerver.http.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestTest {
    Request request;

    @Before
    public void setUp() throws Exception {
        request = new Request();
    }

    @Test
    public void testSetRequestLine() throws Exception {
        request.setRequestLine("HEAD / HTTP/1.1");

        assertEquals("HEAD", request.method);
        assertEquals("/", request.uri);
    }

    @Test
    public void testSetRequestHeader() throws Exception {
        request.setRequestHeader("Host: localhost:9999");
        request.setRequestHeader("Accept: */*");

        assertEquals("localhost:9999", request.header.get("Host"));
        assertEquals("*/*", request.header.get("Accept"));
    }

    @Test
    public void testHasContentLength() throws Exception {
        request.setRequestHeader("Content-Length: 16");
        assertEquals(true, request.hasContentLength());
    }

    @Test
    public void testDoesntHaveContentLength() throws Exception {
        request.setRequestHeader("Host: localhost:9999");
        assertEquals(false, request.hasContentLength());
    }

    @Test
    public void testGetContentLength() throws Exception {
        request.setRequestHeader("Content-Length: 16");
        assertEquals(16, request.getContentLength());
    }

    @Test
    public void testSetBody() throws Exception {
        request.setBody("awesome=true&cold=not".getBytes());
        assertEquals("awesome=true&cold=not", new String(request.bodyContent));
    }

    @Test
    public void testProcessParams() throws Exception {
        request.setRequestLine("POST / HTTP/1.1");
        request.setBody("awesome=true&cold=false".getBytes());
        request.generateParams();
        assertEquals("true", request.param.get("awesome"));

    }
}
