package com.jerver.http.route;

import com.jerver.http.request.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringRouteTest {
    @Test
    public void testNewStringRoute() throws Exception {
        Request request = new Request();
        StringRoute route = new StringRoute("Hello World!");
        assertEquals("Hello World!", route.resolve());
    }
}
