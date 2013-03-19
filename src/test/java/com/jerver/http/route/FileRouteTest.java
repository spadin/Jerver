package com.jerver.http.route;

import com.jerver.http.mock.MockRequest;
import com.jerver.http.mock.MockResponse;
import com.jerver.http.request.Request;
import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import java.nio.file.Paths;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class FileRouteTest {
    StubSystemOut systemOutStub = new StubSystemOut();

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

    @Test
    public void testRunNullFilePath() throws Exception {
        FileRoute route = new FileRoute(Paths.get("src/test/resources/test.txt"));
        route.filePath = null;

        systemOutStub.replace();
        route.getBody();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to open file"));
    }
}
