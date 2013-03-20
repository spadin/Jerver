package com.jerver.list;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DirectoryListing implements Listing {
    protected Path path;

    public DirectoryListing(Path path) {
        this.path = path;

    }

    public List<Path> getList() throws IOException {
        List<Path> result = new ArrayList<Path>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(path);

        for (Path entry: stream) {
            result.add(entry);
        }

        return result;
    }
}
