package com.jerver.http.route;

import com.jerver.http.mock.MockResponseImpl;
import com.jerver.http.request.RequestImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class FourOhFourRouteTest {
    @Test
    public void testContentType() throws Exception {
        FourOhFourRoute route = new FourOhFourRoute();
        assertEquals("text/html", route.getContentType());
    }

    @Test
    public void testResponse() throws Exception {
        RequestImpl request = new RequestImpl();
        MockResponseImpl response = new MockResponseImpl();
        FourOhFourRoute route = new FourOhFourRoute();
        route.resolve(request, response);
        assertThat(response.getResponseText(), containsString("File not found"));
    }
}
