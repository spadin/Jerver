package com.jerver.http.route;

import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class DirectoryRouteTest {
    StubSystemOut systemOutStub = new StubSystemOut();

    @Test
    public void testContentType() throws Exception {
        DirectoryRoute route = new DirectoryRoute(Paths.get(""), Paths.get("src/test/resources").toAbsolutePath());
        assertEquals("text/html", route.getContentType());
    }

    @Test
    public void testContainsParentDirectoryLink() throws Exception {
        DirectoryRoute route = new DirectoryRoute(Paths.get("images"), Paths.get("src/test/resources").toAbsolutePath());
        assertThat(new String(route.getBody()), containsString("Parent directory"));
    }

    @Test
    public void testDoesNotContainParentDirectoryLink() throws Exception {
        DirectoryRoute route = new DirectoryRoute(Paths.get(""), Paths.get("src/test/resources").toAbsolutePath());
        assertThat(new String(route.getBody()), not(containsString("Parent directory")));
    }

    @Test
    public void testNullDirectoryListing() throws Exception {
        DirectoryRoute route = new DirectoryRoute(Paths.get(""), Paths.get("src/test/resources").toAbsolutePath());
        route.directoryListing = null;

        systemOutStub.replace();
        route.getBody();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to get directory listing"));
    }
}
