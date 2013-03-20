package com.jerver.http.response;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ResponseImplTest {
    @Test
    public void testSetStatusCode() throws Exception {
        ResponseImpl response = new ResponseImpl();
        response.setStatusCode(200);
        assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

    }

    @Test
    public void testWrite() throws Exception {
        ResponseImpl response = new ResponseImpl();
        response.setStatusCode(200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.setOutputStream(baos);
        response.write();
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", baos.toString());
    }

    @Test
    public void testSetHeader() throws Exception {
        ResponseImpl response = new ResponseImpl();
        response.setStatusCode(200);
        response.appendHeader("Content-Type: text/plain");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.setOutputStream(baos);
        response.write();
        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n", baos.toString());
    }

    @Test
    public void testAppendHeader() throws Exception {
        ResponseImpl response = new ResponseImpl();
        response.appendHeader("Content-Type: text/plain");
        assertEquals("Content-Type: text/plain", response.headers.get(0));
    }

    @Test
    public void testAppendNullHeader() throws Exception {
        ResponseImpl response = new ResponseImpl();
        response.appendHeader(null);
        assertEquals(0, response.headers.size());
    }

    @Test
    public void testSetBody() throws Exception {
        ResponseImpl response = new ResponseImpl();
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
