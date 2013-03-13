package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringRouteTest {
    StringRoute route = new StringRoute("Hello World!");

    @Test
    public void testContentType() throws Exception {
        assertEquals("text/plain", route.getContentType());
    }

    @Test
    public void testNewStringRoute() throws Exception {
        Request request = new Request();
        Response response = new Response();
        assertEquals("Hello World!", new String(route.resolve(request, response)));
    }
}
