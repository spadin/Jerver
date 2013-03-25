package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;

import java.util.Map;

public class PostPrintoutRoute implements Routable {
    public void resolve(RequestImpl request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-Type: text/html");
        response.setBody(getBody(request));
    }

    private byte[] getBody(RequestImpl request) {
        String html = "<!DOCTYPE html>\n" +
                      "<html>\n" +
                      "<head>\n" +
                      "    <title>Form result page</title>\n" +
                      "</head>\n" +
                      "<body>\n" +
                      "    <h1>Form result page</h1>\n" +
                      "    <p>This is a listing of all POST params:</p>\n" +
                      "    <ul>\n";
        for(Map.Entry<String, String> entry: request.param.entrySet()) {
            html += "        <li>" + entry.getKey() + ": " + entry.getValue() + "</li>\n";
        }
              html += "    </ul>\n" +
                      "    <p>\n" +
                      "        <a href=\"/form\">Go back to the form</a>\n" +
                      "    </p>\n" +
                      "</body>\n" +
                      "</html>";

        return html.getBytes();
    }
}
