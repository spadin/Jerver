package com.jerver.http.route;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class StringSubstitutionRouteTest {
    @Test
    public void testStringSubstitution() throws Exception {
        Map<String, String> data = new HashMap<String, String>();
        data.put("animal", "Cat");
        data.put("sound", "meow");

        Path filepath = Paths.get("src/test/resources/templates/animal-sounds.jrv.html");

        StringSubstitutionRoute route = new StringSubstitutionRoute(filepath);
        assertThat(route.invokeTemplate(data), containsString("The Cat goes meow."));
    }
}
