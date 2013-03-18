package com.jerver.http.request;

import com.jerver.http.mock.MockInputStream;
import com.jerver.http.stub.StubSystemOut;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import static org.junit.Assert.assertEquals;

public class RequestInputStreamParserTest {
    RequestInputStreamParser requestInputStream;
    Request request;
    StubSystemOut systemOutStub = new StubSystemOut();

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
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.throwErrorOnRead = true;

        systemOutStub.replace();
        requestInputStream.detectCRLF(mockInputStream);
        systemOutStub.reset();

        assertEquals("Failed to read input stream.\n", systemOutStub.getOutput());
    }

    @Test
    public void testDetectCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(13);
        mockInputStream.readList.add(10);

        systemOutStub.replace();
        boolean detectCRLF = requestInputStream.detectCRLF(mockInputStream);
        systemOutStub.reset();

        assertEquals(true, detectCRLF);
    }

    @Test
    public void testDetectNoCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(20);
        mockInputStream.readList.add(20);

        systemOutStub.replace();
        boolean detectCRLF = requestInputStream.detectCRLF(mockInputStream);
        systemOutStub.reset();

        assertEquals(false, detectCRLF);
    }

    @Test
    public void testDetectDoubleCRLFWithNullInputStream() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.throwErrorOnRead = true;

        systemOutStub.replace();
        requestInputStream.detectDoubleCRLF(mockInputStream);
        systemOutStub.reset();

        assertEquals("Failed to read input stream.\n", systemOutStub.getOutput());
    }

    @Test
    public void testDetectDoubleCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(13);
        mockInputStream.readList.add(10);
        mockInputStream.readList.add(13);
        mockInputStream.readList.add(10);

        systemOutStub.replace();
        boolean detectDoubleCRLF = requestInputStream.detectDoubleCRLF(mockInputStream);
        systemOutStub.reset();

        assertEquals(true, detectDoubleCRLF);
    }

    @Test
    public void testDetectNoDoubleCRLF() throws Exception {
        MockInputStream mockInputStream = new MockInputStream();
        mockInputStream.readList.add(20);
        mockInputStream.readList.add(10);
        mockInputStream.readList.add(20);
        mockInputStream.readList.add(10);

        systemOutStub.replace();
        boolean detectDoubleCRLF = requestInputStream.detectDoubleCRLF(mockInputStream);
        systemOutStub.reset();

        assertEquals(false, detectDoubleCRLF);
    }

}
