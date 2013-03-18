package com.jerver.http.stub;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class StubSystemOut {

    private PrintStream original;
    private ByteArrayOutputStream outputStream;
    private String output;

    public StubSystemOut() {

    }

    public void replace() {
        original = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public void reset() {
        output = outputStream.toString();
        System.setOut(original);
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOutput() {
        return output;
    }
}
