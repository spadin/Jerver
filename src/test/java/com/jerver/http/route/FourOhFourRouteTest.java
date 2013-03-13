package com.jerver.http.route;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class FourOhFourRouteTest {
    @Test
    public void testContentType() throws Exception {
        FourOhFourRoute route = new FourOhFourRoute();
        assertEquals("text/html", route.getContentType());
    }

    @Test
    public void testResponse() throws Exception {
        FourOhFourRoute route = new FourOhFourRoute();
        assertThat(new String(route.resolve()), containsString("File not found"));
    }
}
