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
    public List<Path> getList() {
        List<Path> result = new ArrayList<Path>();
        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream(this.path);

            for (Path entry: stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            System.out.println("Failed directory iterator.");
        } catch (IOException e) {
            System.out.println("Failed to list directory.");
        }
        return result;
    }
}
