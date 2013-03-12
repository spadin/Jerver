package com.jerver.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpRequestInputStreamParser {
    private final HttpRequest httpRequest;

    public HttpRequestInputStreamParser(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public void parse(InputStream is) {
        int b;
        boolean readingHeaders = true;
        boolean firstLine = true;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        try {
            while ((b = bis.read()) != -1) {
                baos.write(b);

                if (readingHeaders) {
                    if (detectDoubleCRLF(bis)) {
                        baos.flush();
                        httpRequest.setRequestHeader(baos.toString().trim());
                        baos.reset();

                        if (!httpRequest.hasContentLength()) {
                            break;
                        } else {
                            bis.skip(4);
                            readingHeaders = false;
                        }
                    } else if (detectCRLF(bis)) {
                        baos.flush();

                        if (firstLine) {
                            httpRequest.setRequestLine(baos.toString().trim());
                            firstLine = false;
                        } else {
                            httpRequest.setRequestHeader(baos.toString().trim());
                        }

                        baos.reset();
                    }
                } else {
                    if (baos.size() == httpRequest.getContentLength()) {
                        httpRequest.setBody(baos.toByteArray());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Read input stream failure.");
        }
    }

    public boolean detectCRLF(InputStream is) {
        is.mark(0);

        boolean crlf = false;
        try {
            crlf = (is.read() == 13 && is.read() == 10);
            is.reset();
        } catch (IOException e) {
            System.out.println("Failed to read input stream.");
        }

        return crlf;
    }

    public boolean detectDoubleCRLF(InputStream is) {
        is.mark(0);

        boolean crlf = false;
        try {
            crlf = (is.read() == 13 && is.read() == 10 &&
                    is.read() == 13 && is.read() == 10);
            is.reset();
        } catch (IOException e) {
            System.out.println("Failed to read input stream.");
        }

        return crlf;
    }
}