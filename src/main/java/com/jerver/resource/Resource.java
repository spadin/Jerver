package com.jerver.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Resource {
    public static String getStringForResource(String resource) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(resource);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int next;

        try {
            next = inputStream.read();
            while (next > -1) {
                bos.write(next);
                next = inputStream.read();
            }
        } catch (Exception e) {
            System.out.println("Failed to getStringForResource");
            return null;
        }

        byte[] result = bos.toByteArray();
        return new String(result);
    }
}
