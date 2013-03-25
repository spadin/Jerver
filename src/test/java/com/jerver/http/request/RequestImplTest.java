package com.jerver.http.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestImplTest {
    RequestImpl request;

    @Before
    public void setUp() throws Exception {
        request = new RequestImpl();
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

    @Test
    public void testProcessParamsWithNullMethod() throws Exception {
        request.method = null;
        request.setBody("awesome=true&cold=false".getBytes());
        request.generateParams();
        assertEquals(null, request.param.get("awesome"));
    }

    @Test
    public void testProcessParamsForGet() throws Exception {
        request.setRequestLine("GET /?test=1 HTTP/1.1");
        request.generateParams();
        assertEquals("1", request.param.get("test"));
    }

    @Test
    public void testUpdateUriAfterProcessingParams() throws Exception {
        request.setRequestLine("GET /?test=1 HTTP/1.1");
        request.generateParams();
        assertEquals("/", request.uri);
    }

    @Test
    public void testGetParam() throws Exception {
        request.param.put("paramKey", "paramValue");
        assertEquals("paramValue", request.getParam("paramKey"));
    }

    @Test
    public void testGetHeader() throws Exception {
        request.header.put("Location", "http://google.com");
        assertEquals("http://google.com", request.getHeader("Location"));
    }
}
