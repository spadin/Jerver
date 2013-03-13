package com.jerver.http.route;

import com.jerver.http.mock.MockResponse;
import com.jerver.http.request.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class StringRouteTest {
    StringRoute route = new StringRoute("Hello World!");

    @Test
    public void testContentType() throws Exception {
        assertEquals("text/plain", route.getContentType());
    }

    @Test
    public void testNewStringRoute() throws Exception {
        Request request = new Request();
        MockResponse response = new MockResponse();
        route.resolve(request, response);

        assertThat(response.getResponseText(), containsString("Hello World!"));
    }
}
