package com.jerver.http.route;

import com.jerver.http.request.RequestImpl;
import com.jerver.http.response.Response;
import com.jerver.list.DirectoryListing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryRoute implements Routable {
    DirectoryListing directoryListing;
    Path rootPath;
    Path directoryPath;

    public DirectoryRoute(Path path, Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.directoryPath = rootPath.resolve(path);
        this.directoryListing = new DirectoryListing(directoryPath);

    }

    protected String printableItemName(Path path) {
        String name = path.getFileName().toString();
        if(Files.isDirectory(path)) {
            name += "/";
        }
        return name;
    }

    protected String createHtmlLink(String text) {
        return "<a href=\"" + text + "\">" + text + "</a>";
    }

    protected void appendPathItem(StringBuilder html, Path path) {
        html.append("<li>");
        html.append(createHtmlLink(printableItemName(path)));
        html.append("</li>");
    }

    public byte[] getBody() {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html><html><body><h1>Directory Listing</h1><ul>");

        if(!rootPath.relativize(directoryPath).toString().equals("")) {
            html.append("<li><a href=\"../\">Parent directory</a></li>");
        }

        try {
            for(Path path: directoryListing.getList()) {
                if (isHidden(path)) continue;
                appendPathItem(html, path);
            }
        } catch (Exception e) {
            System.out.println("Failed to get directory listing.");
            System.out.println(e);
        }

        html.append("</ul></body></html>");

        return html.toString().getBytes();
    }

    public void resolve(RequestImpl request, Response response) {
        response.setStatusCode(200);
        response.appendHeader("Content-Type: " + getContentType());
        response.setBody(getBody());
    }

    private boolean isHidden(Path path) throws IOException {
        if(Files.isHidden(path)) {
            return true;
        }
        return false;
    }

    public String getContentType() {
        return "text/html";
    }
}
