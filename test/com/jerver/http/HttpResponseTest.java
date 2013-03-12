package com.jerver.http;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class HttpResponseTest {
    @Test
    public void testSetStatusCode() throws Exception {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(200);
        assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

    }

    @Test
    public void testWrite() throws Exception {
        HttpResponse response = new HttpResponse();
        response.setStatusCode(200);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        response.setOutputStream(ps);
        response.write();
        assertEquals("HTTP/1.1 200 OK\r\n", baos.toString());
    }
}
