package com.jerver.http.route;

import com.jerver.http.mock.MockRequest;
import com.jerver.http.mock.MockResponse;
import com.jerver.http.request.Request;
import org.junit.Test;

import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class FileRouteTest {
    @Test
    public void testPlaintextContentType() throws Exception {
        FileRoute route = new FileRoute(Paths.get("src/test/resources/test.txt"));
        assertEquals("text/plain", route.getContentType());
    }

    @Test
    public void testHtmlContentType() throws Exception {
        FileRoute route = new FileRoute(Paths.get("src/test/resources/test.html"));
        assertEquals("text/html", route.getContentType());
    }

    @Test
    public void testPlaintextFileRoute() throws Exception {
        FileRoute route = new FileRoute(Paths.get("src/test/resources/test.txt"));
        Request request = new Request();
        MockResponse response = new MockResponse();
        route.resolve(request, response);

        assertThat(response.getResponseText(), containsString("This is a test file"));
    }
}
