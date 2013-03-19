package com.jerver.list;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DirectoryListing implements Listing {
    protected Path path;
    protected DirectoryStream<Path> stream;

    public DirectoryListing(Path path) throws IOException {
        this.path = path;
        this.stream = Files.newDirectoryStream(path);
    }

    public List<Path> getList() throws IOException {
        List<Path> result = new ArrayList<Path>();

        for (Path entry: stream) {
            result.add(entry);
        }

        stream.close();
        return result;
    }
}
