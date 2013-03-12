package com.jerver;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryListingTest {
    protected String path = "/Users/sandropadin/IdeaProjects/Jerver/resources";
    protected DirectoryListing directoryListing = new DirectoryListing(this.path);

    @Test
    public void testGetRootDirectoryPath() {
        assertEquals(this.path, this.directoryListing.getRootDirectoryPath());
    }

    @Test
    public void testGetDirectoryContents() throws IOException {
        List<Path> contents = directoryListing.getDirectoryList();
        assertEquals(3, contents.size());
    }
}
