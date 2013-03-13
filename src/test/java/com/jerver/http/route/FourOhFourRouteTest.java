package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
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
        Response response = new Response();
        FourOhFourRoute route = new FourOhFourRoute();
        assertThat(new String(route.resolve(request, response)), containsString("File not found"));
    }
}
