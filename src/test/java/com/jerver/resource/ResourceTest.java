package com.jerver.resource;

import com.jerver.http.stub.StubSystemOut;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ResourceTest {
    StubSystemOut systemOutStub = new StubSystemOut();

    @Test
    public void testInstance() throws Exception {
        Resource resource = new Resource();
        assertNotNull(resource);
    }

    @Test
    public void testGetStringForResource() throws Exception {
        String mimeTypes = Resource.getStringForResource("mime.types");
        assertThat(mimeTypes, containsString("text/html"));
    }

    @Test
    public void testGetStringForResourceThatDoesNotExist() throws Exception {
        systemOutStub.replace();
        Resource.getStringForResource("does-not-exist.txt");
        systemOutStub.reset();

        assertEquals("Failed to getStringForResource\n", systemOutStub.getOutput());
    }
}
