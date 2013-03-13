package com.jerver.http.route;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class DirectoryRouteTest {
    @Test
    public void testDirectoryRoute() throws Exception {
        DirectoryRoute route = new DirectoryRoute("", "/Users/sandropadin/IdeaProjects/Jerver/resources");
        assertThat(route.resolve(), containsString("test"));
    }
}
