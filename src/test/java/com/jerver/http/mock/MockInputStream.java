package com.jerver.http.mock;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class MockInputStream extends InputStream {
    public boolean throwErrorOnRead = false;
    public LinkedList<Integer> readList = new LinkedList<Integer>();

    public MockInputStream() {
        super();
    }

    public void add(int item) {
        readList.add(item);
    }

    @Override
    public int read() throws IOException {
        if(throwErrorOnRead) {
            throw new IOException("MockInputStream throwErrorOnRead");
        }
        if(readList.size() == 0) {
            throw new IOException("MockInputStream readList is empty");
        }

        return readList.remove();
    }
}
