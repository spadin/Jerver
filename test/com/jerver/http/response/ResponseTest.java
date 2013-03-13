package com.jerver.http.response;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class ResponseTest {
    @Test
    public void testSetStatusCode() throws Exception {
        Response response = new Response();
        response.setStatusCode(200);
        assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

    }

    @Test
    public void testWrite() throws Exception {
        Response response = new Response();
        response.setStatusCode(200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.setOutputStream(baos);
        response.write();
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", baos.toString());
    }

    @Test
    public void testSetHeader() throws Exception {
        Response response = new Response();
        response.setStatusCode(200);
        response.appendHeader("Content-Type: text/plain");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.setOutputStream(baos);
        response.write();
        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n", baos.toString());
    }

    @Test
    public void testSetBody() throws Exception {
        Response response = new Response();
        response.setStatusCode(200);
        response.appendHeader("Content-Type: text/plain");
        response.setBody("Hello World!".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.setOutputStream(baos);
        response.write();
        assertEquals(true, response.hasBody());
        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 12\r\n\r\nHello World!", baos.toString());

    }
}
