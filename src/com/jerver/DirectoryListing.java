package com.jerver;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class DirectoryListing {
    protected Path path;

    public DirectoryListing(String path) {
        this.path = Paths.get(path);
    }
    public String getRootDirectoryPath() {
        return this.path.toString();
    }
    public List<Path> getDirectoryList() throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.path)) {
            for (Path entry: stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            throw ex.getCause();
        }
        return result;
    }
}
