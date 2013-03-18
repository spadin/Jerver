package com.jerver.http.request;

import com.jerver.http.mock.MockInputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import static org.junit.Assert.assertEquals;

public class RequestInputStreamParserTest {
    RequestInputStreamParser requestInputStream;
    Request request;

    @Before
    public void setUp() throws Exception {
        request = new Request();
        requestInputStream = new RequestInputStreamParser(request);

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
    public void testDetectCRLFWithNullInputStream() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.throwErrorOnRead = true;

        requestInputStream.detectCRLF(mockInputStream);
        System.setOut(original);

        assertEquals("Failed to read input stream.\n", baos.toString());
    }

    @Test
    public void testDetectCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(13);
        mockInputStream.readList.add(10);

        assertEquals(true, requestInputStream.detectCRLF(mockInputStream));
    }

    @Test
    public void testDetectNoCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(20);
        mockInputStream.readList.add(20);

        assertEquals(false, requestInputStream.detectCRLF(mockInputStream));
    }

    @Test
    public void testDetectDoubleCRLFWithNullInputStream() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.throwErrorOnRead = true;

        requestInputStream.detectDoubleCRLF(mockInputStream);
        System.setOut(original);

        assertEquals("Failed to read input stream.\n", baos.toString());
    }

    @Test
    public void testDetectDoubleCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(13);
        mockInputStream.readList.add(10);
        mockInputStream.readList.add(13);
        mockInputStream.readList.add(10);

        assertEquals(true, requestInputStream.detectDoubleCRLF(mockInputStream));
    }

    @Test
    public void testDetectNoDoubleCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(20);
        mockInputStream.readList.add(10);
        mockInputStream.readList.add(20);
        mockInputStream.readList.add(10);

        assertEquals(false, requestInputStream.detectDoubleCRLF(mockInputStream));
    }

}
