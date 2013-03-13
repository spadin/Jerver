package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.mock.MockResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.jerver.http.mock.MockRequest;

public class RouterTest {
    private static final Router router = Router.INSTANCE;

    @Test
    public void testRouteExists() throws Exception {
        Request request;

        router.addRoute("GET", "/hello", "Hello World!");

        request = new MockRequest("GET", "/hello");
        assertEquals(true, router.routeExists(request));

        request = new MockRequest("GET", "/");
        assertEquals(false, router.routeExists(request));

    }

    @Test
    public void testResolveRoute() throws Exception {
        Request request = new MockRequest("GET", "/hello");
        MockResponse response = new MockResponse();

        router.addRoute("GET", "/hello", "Hello World!");
        router.resolve(request, response);

        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-Length: 12\r\n\r\nHello World!", response.getResponseText());
    }
}
