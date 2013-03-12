package com.jerver.http;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.assertEquals;

public class HttpRequestInputStreamParserTest {
    HttpRequestInputStreamParser requestInputStream;
    HttpRequest request;

    @Before
    public void setUp() throws Exception {
        request = new HttpRequest();
        requestInputStream = new HttpRequestInputStreamParser(request);

    }

    @Test
    public void testParse() throws Exception {
        String str = "HEAD / HTTP/1.1\r\n" +
                "User-Agent: curl/7.24.0 (x86_64-apple-darwin12.0) libcurl/7.24.0 OpenSSL/0.9.8r zlib/1.2.5\r\n" +
                "Host: localhost:9999\r\n\r\n";

        requestInputStream.parse(new ByteArrayInputStream(str.getBytes()));

        assertEquals("HEAD", request.method);
        assertEquals("localhost:9999", request.header.get("Host"));
    }

    @Test
    public void testDetectCRLF() throws Exception {
        String str = "\r\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        assertEquals(true, requestInputStream.detectCRLF(bais));

        str = "no newline";
        bais = new ByteArrayInputStream(str.getBytes());
        assertEquals(false, requestInputStream.detectCRLF(bais));
    }

    @Test
    public void testDoubleDetectCRLF() throws Exception {
        String str = "\r\n\r\n";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        assertEquals(true, requestInputStream.detectDoubleCRLF(bais));

        str = "\r\n";
        bais = new ByteArrayInputStream(str.getBytes());
        assertEquals(false, requestInputStream.detectDoubleCRLF(bais));

        str = "no newline";
        bais = new ByteArrayInputStream(str.getBytes());
        assertEquals(false, requestInputStream.detectDoubleCRLF(bais));
    }
}
