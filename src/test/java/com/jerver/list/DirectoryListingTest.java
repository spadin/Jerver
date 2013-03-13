package com.jerver.list;

import com.jerver.list.DirectoryListing;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectoryListingTest {
    protected String path = "/Users/sandropadin/IdeaProjects/Jerver/resources";
    protected DirectoryListing directoryListing = new DirectoryListing(this.path);

    @Test
    public void testGetList() throws IOException {
        List<Path> directoryPathList = directoryListing.getList();
        assertEquals(5, directoryPathList.size());
    }
}
