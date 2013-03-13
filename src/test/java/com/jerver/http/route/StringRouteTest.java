package com.jerver.http.route;

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
        assertEquals("Hello World!", new String(route.resolve()));
    }
}
