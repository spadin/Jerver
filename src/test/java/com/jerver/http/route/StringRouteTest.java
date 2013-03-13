package com.jerver.http.route;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringRouteTest {
    @Test
    public void testNewStringRoute() throws Exception {
        StringRoute route = new StringRoute("Hello World!");
        assertEquals("Hello World!", route.resolve());
    }
}
