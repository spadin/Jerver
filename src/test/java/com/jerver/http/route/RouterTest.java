package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.mock.MockResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import com.jerver.http.mock.MockRequest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RouterTest {
    private static final Router router = Router.INSTANCE;

    @Test
    public void testGetKey() throws Exception {
        String key = router.getKey("GET", "/some/path");
        assertEquals("GET /some/path", key);
    }

    @Test
    public void testRouteExists() throws Exception {
        Request request;

        router.reset();
        router.addRoute("GET", "/hello", "Hello World!");

        request = new MockRequest("GET", "/hello");
        assertEquals(true, router.routeExists(request));

        request = new MockRequest("GET", "/does-not-exist");
        assertEquals(false, router.routeExists(request));

    }

    @Test
    public void testResolveRoute() throws Exception {
        Request request = new MockRequest("GET", "/hello");
        MockResponse response = new MockResponse();

        router.reset();
        router.addRoute("GET", "/hello", "Hello World!");
        router.resolve(request, response);

        assertThat(response.getResponseText(), containsString("Hello World!"));
    }

    @Test
    public void testSetPublicDirectory() throws Exception {
        router.reset();
        router.setPublicDirectory("src/test/resources");

        Request request = new MockRequest("GET", "/");
        MockResponse response = new MockResponse();
        router.resolve(request, response);

        assertEquals(200, response.status);
    }

    @Test
    public void testSetInvalidPublicDirectory() throws Exception {
        router.reset();

        // Suppress output.
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        router.setPublicDirectory("/does/not/exist");
        System.setOut(original);

        assertEquals("Failed to walk file tree.\n", baos.toString());
    }

    @Test
    public void testPublicRoutesAreProperlyAdded() throws Exception {
        router.reset();
        router.setPublicDirectory("src/test/resources");

        Request request = new MockRequest("GET", "/test.txt");
        MockResponse response = new MockResponse();
        router.resolve(request, response);

        assertEquals(200, response.status);
    }
}
