package com.jerver.http.request;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RequestInputStreamParser {
    private final RequestImpl request;

    public RequestInputStreamParser(RequestImpl request) {
        this.request = request;
    }

    public void parse(InputStream is) {
        try {
            int b;
            boolean readingHeaders = true;
            boolean firstLine = true;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            while ((b = bis.read()) != -1) {
                baos.write(b);

                if (readingHeaders) {
                    if (detectDoubleCRLF(bis)) {
                        baos.flush();
                        request.setRequestHeader(baos.toString().trim());
                        baos.reset();

                        if (!request.hasContentLength()) {
                            break;
                        } else {
                            bis.skip(4);
                            readingHeaders = false;
                        }
                    } else if (detectCRLF(bis)) {
                        baos.flush();

                        if (firstLine) {
                            request.setRequestLine(baos.toString().trim());
                            firstLine = false;
                        } else {
                            request.setRequestHeader(baos.toString().trim());
                        }

                        baos.reset();
                    }
                } else {
                    if (baos.size() == request.getContentLength()) {
                        request.setBody(baos.toByteArray());
                        baos.close();
                        break;
                    }
                }
            }
        } catch (Exception e) {
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