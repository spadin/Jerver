package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;
import com.jerver.mime.MimeType;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileRoute implements Routable {
    Path filePath;
    protected static final MimeType mimeType = MimeType.INSTANCE;

    public FileRoute(Path filePath) {
        this.filePath = filePath;
    }

    public byte[] getBody() {
        byte[] body = null;
        try {
            body = Files.readAllBytes(filePath);
        } catch (Exception e) {
            System.out.println("Failed to open file");
        }

        return body;
    }

    public void resolve(RequestImpl request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-Type: " + getContentType());
        response.setBody(getBody());
    }

    public String getContentType() {
        return mimeType.getForFilename(filePath.getFileName().toString());
    }
}
