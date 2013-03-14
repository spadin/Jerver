package com.jerver.http.route;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SleepyRouteTest {
    @Test
    public void testContentType() throws Exception {
        SleepyRoute route = new SleepyRoute(1);
        assertEquals("text/plain", route.getContentType());
    }
}
