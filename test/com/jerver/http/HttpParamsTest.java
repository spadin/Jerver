package com.jerver.http;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HttpParamsTest {
    @Test
    public void testGenerateParams() {
        HashMap<String, String> params = HttpParams.generateParams("var1=10&var2=hello");
        assertEquals(params.get("var1"), "10");
        assertEquals(params.get("var2"), "hello");
    }
}
