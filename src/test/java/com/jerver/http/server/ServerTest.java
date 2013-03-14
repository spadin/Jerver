package com.jerver.http.server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void testConnection() {

    }

    @Test
    public void testGetOptionWhenSet() throws Exception {
        String[] args = {"-p", "9898", "-d", "/some/cool/path"};
        String opt = Server.getOption("-d", "/src/test/resources", args);

        assertEquals("/some/cool/path", opt);
    }

    @Test
    public void testGetOptionWhenNotSet() throws Exception {
        String[] args = {"-p", "9898"};
        String opt = Server.getOption("-d", "/src/test/resources", args);

        assertEquals("/src/test/resources", opt);
    }

    @Test
    public void testGetOptionWhenNothingIsSet() throws Exception {
        String[] args = {};
        String opt = Server.getOption("-d", "/src/test/resources", args);

        assertEquals("/src/test/resources", opt);
    }

    @Test
    public void testGetOptionAsInt() throws Exception {
        String[] args = {"-p", "9997"};
        int opt = Server.getOption("-p", 9999, args);

        assertEquals(9997, opt);
    }
}
