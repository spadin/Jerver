package com.jerver.http.route;

import com.jerver.http.mock.MockResponse;
import com.jerver.http.request.Request;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class DirectoryRouteTest {
    protected DirectoryRoute route = new DirectoryRoute(Paths.get(""), Paths.get("/Users/sandropadin/IdeaProjects/Jerver/resources"));

    @Test
    public void testContentType() throws Exception {
        assertEquals("text/html", route.getContentType());
    }

    @Test
    public void testDirectoryRoute() throws Exception {
        Request request = new Request();
        MockResponse response = new MockResponse();
        route.resolve(request, response);

        assertThat(response.getResponseText(), containsString("test"));
    }
}
