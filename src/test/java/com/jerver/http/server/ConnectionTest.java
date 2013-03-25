package com.jerver.http.server;

import com.jerver.http.mock.MockRequestImpl;
import com.jerver.http.mock.MockResponseImpl;
import com.jerver.http.mock.MockSocket;
import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.jerver.http.HttpHelper.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ConnectionTest {
    MockSocket socket;
    MockRequestImpl request;
    MockResponseImpl response;
    Connection connection;
    OutputStream output;
    StubSystemOut systemOutStub = new StubSystemOut();


    public void prepareTest(String method, String uri) {
        InputStream input = buildRequestInputStream(method, uri);
        output = new ByteArrayOutputStream();
        socket = new MockSocket(input, output);
        request = new MockRequestImpl();
        response = new MockResponseImpl();
        connection = new Connection(socket, request, response);
    }

    @Test
    public void testConnection() {
        prepareTest("HEAD", "/");

        systemOutStub.replace();
        connection.run();
        systemOutStub.reset();

        assertThat(output.toString(), containsString("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void testSocketCloseException() throws Exception {
        prepareTest("HEAD", "/");
        socket.exceptionOnClose = true;

        systemOutStub.replace();
        connection.run();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to run connection"));
    }

    @Test
    public void testSocketGetInputStreamException() throws Exception {
        prepareTest("GET", "/");
        socket.exceptionOnGetInputStream = true;

        systemOutStub.replace();
        connection.run();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to run connection"));
    }

    @Test
    public void testSocketGetOutputStreamException() throws Exception {
        prepareTest("GET", "/");
        socket.exceptionOnGetOutputStream = true;

        systemOutStub.replace();
        connection.run();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to run connection"));
    }

    @Test
    public void testSocketWriteResponseException() throws Exception {
        prepareTest("GET", "/");
        response.exceptionOnWrite = true;

        systemOutStub.replace();
        connection.run();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to run connection"));
    }
}