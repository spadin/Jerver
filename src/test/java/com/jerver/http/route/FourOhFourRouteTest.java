package com.jerver.http.route;

import com.jerver.http.mock.MockResponse;
import com.jerver.http.request.Request;
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
        Request request = new Request();
        MockResponse response = new MockResponse();
        FourOhFourRoute route = new FourOhFourRoute();
        route.resolve(request, response);
        assertThat(response.getResponseText(), containsString("File not found"));
    }
}
