package com.jerver.list;

import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectoryListingTest {
    protected Path path = FileSystems.getDefault().getPath("src/test/resources");
    protected DirectoryListing directoryListing = new DirectoryListing(this.path);

    @Test
    public void testGetList() throws Exception {
        List<Path> directoryPathList = directoryListing.getList();
        assertEquals(7, directoryPathList.size());
    }
}
