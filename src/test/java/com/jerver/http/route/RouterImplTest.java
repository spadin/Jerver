package com.jerver.http.route;

import com.jerver.http.mock.MockRequestImpl;
import com.jerver.http.mock.MockResponseImpl;
import com.jerver.http.request.RequestImpl;
import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class RouterImplTest {
    private static final RouterImpl router = RouterImpl.INSTANCE;
    StubSystemOut systemOutStub = new StubSystemOut();

    @Test
    public void testGetKey() throws Exception {
        String key = router.getKey("GET", "/some/path");
        assertEquals("GET /some/path", key);
    }

    @Test
    public void testRouteExists() throws Exception {
        RequestImpl request;

        router.reset();
        router.addRoute("GET", "/hello", "Hello World!");

        request = new MockRequestImpl("GET", "/hello");
        assertEquals(true, router.routeExists(request));

        request = new MockRequestImpl("GET", "/does-not-exist");
        assertEquals(false, router.routeExists(request));

    }

    @Test
    public void testResolveRoute() throws Exception {
        RequestImpl request = new MockRequestImpl("GET", "/hello");
        MockResponseImpl response = new MockResponseImpl();

        router.reset();
        router.addRoute("GET", "/hello", "Hello World!");
        router.resolve(request, response);

        assertThat(response.getResponseText(), containsString("Hello World!"));
    }

    @Test
    public void testSetPublicDirectory() throws Exception {
        router.reset();
        systemOutStub.replace();
        router.setPublicDirectory("src/test/resources");
        systemOutStub.reset();

        RequestImpl request = new MockRequestImpl("GET", "/");
        MockResponseImpl response = new MockResponseImpl();
        router.resolve(request, response);

        assertEquals(200, response.status);
    }

    @Test
    public void testSetInvalidPublicDirectory() throws Exception {
        router.reset();

        systemOutStub.replace();
        router.setPublicDirectory("/does/not/exist");
        systemOutStub.reset();

        assertEquals("Failed to walk file tree.\n", systemOutStub.getOutput());
    }

    @Test
    public void testPublicRoutesAreProperlyAdded() throws Exception {
        router.reset();
        systemOutStub.replace();
        router.setPublicDirectory("src/test/resources");
        systemOutStub.reset();

        RequestImpl request = new MockRequestImpl("GET", "/test.txt");
        MockResponseImpl response = new MockResponseImpl();
        router.resolve(request, response);

        assertEquals(200, response.status);
    }
}
