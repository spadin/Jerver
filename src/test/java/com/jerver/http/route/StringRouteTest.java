package com.jerver.http.route;

import com.jerver.http.mock.MockResponseImpl;
import com.jerver.http.request.RequestImpl;
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
        RequestImpl request = new RequestImpl();
        MockResponseImpl response = new MockResponseImpl();
        route.resolve(request, response);

        assertThat(response.getResponseText(), containsString("Hello World!"));
    }
}
