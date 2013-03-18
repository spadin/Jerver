package com.jerver.http.server;

import com.jerver.http.mock.MockSocket;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import static com.jerver.http.HttpHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ConnectionTest {
    @Test
    public void testConnection() {
        InputStream input = buildRequestInputStream("HEAD", "/");
        OutputStream output = new ByteArrayOutputStream();
        Socket socket = new MockSocket(input, output);

        Connection connection = new Connection(socket);

        // Suppress output.
        PrintStream original = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        connection.run();

        System.setOut(original);

        assertThat(output.toString(), containsString("HTTP/1.1 404 Not Found"));
    }

    @Test
    public void testNullRequestMethod() throws Exception {
        InputStream input = buildRequestInputStream(null, null);
        OutputStream output = new ByteArrayOutputStream();
        Socket socket = new MockSocket(input, output);

        Connection connection = new Connection(socket);

        // Suppress output.
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        connection.run();
        System.setOut(original);

        assertThat(baos.toString(), containsString("No request method/uri\n"));
    }
}
