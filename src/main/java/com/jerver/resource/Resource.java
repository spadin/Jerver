package com.jerver.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Resource {
    public static String getStringForResource(String resource) throws IOException {;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resource);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next;

        next = inputStream.read();
        while (next > -1) {
            bos.write(next);
            next = inputStream.read();
        }
        bos.flush();
        byte[] result = bos.toByteArray();
        bos.close();

        return new String(result);
    }
}
