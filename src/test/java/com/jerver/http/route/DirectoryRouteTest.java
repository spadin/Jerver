package com.jerver.http.route;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class DirectoryRouteTest {
    protected DirectoryRoute route = new DirectoryRoute("", "/Users/sandropadin/IdeaProjects/Jerver/resources");

    @Test
    public void testContentType() throws Exception {
        assertEquals("text/html", route.getContentType());
    }

    @Test
    public void testDirectoryRoute() throws Exception {
        assertThat(new String(route.resolve()), containsString("test"));
    }
}
