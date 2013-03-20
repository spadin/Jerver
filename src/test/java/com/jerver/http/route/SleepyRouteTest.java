package com.jerver.http.route;

import com.jerver.http.mock.MockResponseImpl;
import com.jerver.http.request.Request;
import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

public class SleepyRouteTest {
    StubSystemOut systemOutStub = new StubSystemOut();

    @Test
    public void testContentType() throws Exception {
        SleepyRoute route = new SleepyRoute(1);
        assertEquals("text/plain", route.getContentType());
    }

    @Test
    public void testGetFormattedDate() throws Exception {
        SleepyRoute route = new SleepyRoute(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = df.format(new Date());
        assertThat(route.getFormattedDate(), containsString(dateString));
    }

    @Test
    public void testInterruptSleepingThread() throws Exception {
        SleepyRoute route = new SleepyRoute(1);
        Thread.currentThread().interrupt();

        systemOutStub.replace();
        route.sleep();
        systemOutStub.reset();

        assertThat(systemOutStub.getOutput(), containsString("Failed to sleep"));
    }

    @Test
    public void testThreadSleep() throws Exception {
        SleepyRoute route = new SleepyRoute(100);

        long startTime = (new Date()).getTime();
        route.sleep();
        long endTime = (new Date()).getTime();

        boolean condition = (endTime - startTime >= 100);
        assertTrue("Thread slept for 100 milliseconds", condition);
    }

    @Test
    public void testGetBodyContainsDate() throws Exception {
        SleepyRoute route = new SleepyRoute(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = df.format(new Date());
        assertThat(new String(route.getBody()), containsString(dateString));
    }

    @Test
    public void testResolve() throws Exception {
        SleepyRoute route = new SleepyRoute(1);
        Request request = new Request();
        MockResponseImpl response = new MockResponseImpl();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = df.format(new Date());
        route.resolve(request, response);

        assertThat(response.getResponseText(), containsString(dateString));
    }
}
