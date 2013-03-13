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

        assertEquals(output.toString(), "HTTP/1.1 404 Not Found\r\n\r\n");
    }
}
