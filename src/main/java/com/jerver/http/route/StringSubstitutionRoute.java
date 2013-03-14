package com.jerver.http.route;

import com.jerver.http.request.Request;
import com.jerver.http.response.Response;
import com.jerver.mime.MimeType;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class StringSubstitutionRoute implements Routable {
    private String template;
    private static final MimeType mimeType = MimeType.INSTANCE;
    private final Path filepath;

    public StringSubstitutionRoute(Path filepath) {
        this.filepath = filepath;

        try {
            this.template = new String(Files.readAllBytes(filepath));
        } catch (IOException e) {
            System.out.println("Failed reading file.");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getContentType() {
        return mimeType.getForFilename(filepath.getFileName().toString());
    }

    public byte[] getBody(Request request) {
        return invokeTemplate(request.param).getBytes();
    }

    public void resolve(Request request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-Type: " + getContentType());
        response.setBody(getBody(request));
    }

    public String invokeTemplate(Map<String, String> data) {
        StrSubstitutor substitutor = new StrSubstitutor(data);
        return substitutor.replace(template);
    }
}
