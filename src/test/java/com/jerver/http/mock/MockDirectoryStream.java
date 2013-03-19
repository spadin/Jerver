package com.jerver.http.mock;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Iterator;

public class MockDirectoryStream implements DirectoryStream<Path> {
    public boolean throwExceptionOnClose = false;

    public MockDirectoryStream() {
        super();
    }

    @Override
    public Iterator<Path> iterator() {
        return new Iterator<Path>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Path next() {
                return null;
            }

            @Override
            public void remove() {

            }
        };
    }

    @Override
    public void close() throws IOException {
        if(throwExceptionOnClose) {
            throw new IOException("MockDirectoryStream#close throwExceptionOnClose");
        }
    }
}
