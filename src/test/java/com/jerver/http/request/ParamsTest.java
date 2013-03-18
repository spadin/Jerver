package com.jerver.http.request;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ParamsTest {
    @Test
    public void testGenerateParams() {
        HashMap<String, String> params = Params.generateParams("var1=10&var2=hello");
        assertEquals("10", params.get("var1"));
        assertEquals("hello", params.get("var2"));
    }

    @Test
    public void testNullValueParams() throws Exception {
        HashMap<String, String> params = Params.generateParams("var1=&var2=hello");
        assertEquals("", params.get("var1"));
        assertEquals("hello", params.get("var2"));
    }
}
