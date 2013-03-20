package com.jerver.http.server;

import com.jerver.http.mock.MockRequest;
import com.jerver.http.route.RouterImpl;
import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ServerImplTest {
    StubSystemOut systemOutStub = new StubSystemOut();
    private static final RouterImpl router = RouterImpl.INSTANCE;

    @Test
    public void testInstance() {
        ServerImpl server = new ServerImpl(9998);
        assertEquals(9998, server.port);
    }

    @Test
    public void testAcquirePort() throws Exception {
        ServerImpl server = new ServerImpl(10001);

        systemOutStub.replace();
        server.acquirePort();
        systemOutStub.reset();

        assertNotNull(server.listener);
        server.listener.close();
    }

    @Test
    public void testAcquirePortThatIsTaken() throws Exception {
        ServerImpl server = new ServerImpl(10001);

        systemOutStub.replace();
        server.acquirePort();
        systemOutStub.reset();

        ServerSocket l1 = server.listener;

        systemOutStub.replace();
        server.acquirePort();
        ServerSocket l2 = server.listener;
        systemOutStub.reset();

        l1.close();
        l2.close();

        assertThat(systemOutStub.getOutput(), containsString("Failed to acquire port"));
    }

    @Test
    public void testGetOptionWhenSet() throws Exception {
        String[] args = {"-p", "9898", "-d", "/some/cool/path"};
        String opt = ServerImpl.getOption("-d", "/src/test/resources", args);

        assertEquals("/some/cool/path", opt);
    }

    @Test
    public void testGetOptionWhenNotSet() throws Exception {
        String[] args = {"-p", "9898"};
        String opt = ServerImpl.getOption("-d", "/src/test/resources", args);

        assertEquals("/src/test/resources", opt);
    }

    @Test
    public void testGetOptionWhenNothingIsSet() throws Exception {
        String[] args = {};
        String opt = ServerImpl.getOption("-d", "/src/test/resources", args);

        assertEquals("/src/test/resources", opt);
    }

    @Test
    public void testGetOptionAsInt() throws Exception {
        String[] args = {"-p", "9997"};
        int opt = ServerImpl.getOption("-p", 9999, args);

        assertEquals(9997, opt);
    }

    @Test
    public void testGetOptionThrowsError() throws Exception {
        String[] args = {"-p", "eight-thousand"};

        systemOutStub.replace();
        int opt = ServerImpl.getOption("-p", 9999, args);
        systemOutStub.reset();

        assertEquals(9999, opt);
    }

    @Test
    public void testAddDefaultRoutes() throws Exception {
        ServerImpl server = new ServerImpl(10001);
        server.addDefaultRoutes();

        assertTrue(router.routeExists(new MockRequest("GET", "/hello")));
        assertTrue(router.routeExists(new MockRequest("GET", "/time")));
        assertTrue(router.routeExists(new MockRequest("GET", "/form")));
        assertTrue(router.routeExists(new MockRequest("POST", "/form", "test=test")));
    }

    @Test
    public void testRunAsThread() throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm running");
            }
        };

        ServerImpl server = new ServerImpl(10001);
        systemOutStub.replace();
        Thread thread = server.runAsThread(runnable);
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("I'm running"));
        thread.interrupt();
    }

    @Test
    public void testSetPublicDirectory() throws Exception {
        ServerImpl server = new ServerImpl(10001);

        systemOutStub.replace();
        server.setPublicDirectory("src/test/resources/");
        systemOutStub.reset();

        assertTrue(router.publicDirectoryPath.equals(Paths.get("src/test/resources")));
    }

    @Test
    public void testRun() throws Exception {
        ServerImpl server = new ServerImpl(10001);
        server.listener = new ServerSocket(10001) {
            @Override
            public Socket accept() throws IOException {
                throw new IOException("testRun IOException");
            }

        };

        systemOutStub.replace();
        server.run();
        systemOutStub.reset();
        server.listener.close();

        assertThat(systemOutStub.getOutput(), containsString("listener.accept failed"));
    }

    @Test
    public void testRunOnce() throws Exception {
        ServerImpl server = new ServerImpl(10001);
        server.listener = new ServerSocket(10001) {
            private boolean firstAccept = true;
            @Override
            public Socket accept() throws IOException {
                if(!firstAccept) {
                    throw new IOException("testRun IOException");
                }

                firstAccept = false;
                return new Socket();
            }

        };

        systemOutStub.replace();
        server.run();
        systemOutStub.reset();
        server.listener.close();

        assertThat(systemOutStub.getOutput(), containsString("Failed to run connection"));
    }

    @Test
    public void testRunNotRunning() throws Exception {
        ServerImpl server = new ServerImpl(10001);
        ServerImpl.RUNNING = false;

        systemOutStub.replace();
        server.run();
        systemOutStub.reset();
        server.listener.close();

        // assertThat(systemOutStub.getOutput(), containsString("listener.accept failed"));
    }

    @Test
    public void testStop() throws Exception {
        ServerImpl server = new ServerImpl(10001);
        server.stop();

        assertFalse(ServerImpl.RUNNING);
    }

    @Test
    public void testMain() throws Exception {
        ServerImpl.RUNNING = false;

        systemOutStub.replace();
        ServerImpl.main(new String[]{});
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Jerver started"));

        ServerImpl.RUNNING = true;
    }
}
