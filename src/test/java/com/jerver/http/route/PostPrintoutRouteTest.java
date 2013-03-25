package com.jerver.http.route;

import com.jerver.http.mock.MockRequestImpl;
import com.jerver.http.mock.MockResponseImpl;
import com.jerver.http.request.RequestImpl;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class PostPrintoutRouteTest {
    @Test
    public void testGetContentType() throws Exception {
        PostPrintoutRoute route = new PostPrintoutRoute();
        RequestImpl request = new MockRequestImpl("POST", "/hello", "var1=test&hello=world");
        MockResponseImpl response = new MockResponseImpl();

        route.resolve(request, response);

        assertThat(response.getResponseText(), containsString("hello: world"));
        assertThat(response.getResponseText(), containsString("var1: test"));
    }
}
