package com.jerver.mime;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MimeTypeTest {
    protected static final MimeType mimeType = MimeType.INSTANCE;

    @Test
    public void testGifMimeType() {
        assertEquals("image/gif", mimeType.getForFilename("image.gif"));
    }

    @Test
    public void testJpegMimeType() {
        assertEquals("image/jpeg", mimeType.getForFilename("image.jpg"));
        assertEquals("image/jpeg", mimeType.getForFilename("image.jpeg"));
    }

    @Test
    public void testPngMimeType() {
        assertEquals("image/png", mimeType.getForFilename("image.png"));
    }

    @Test
    public void testPdfMimeType() {
        assertEquals("application/pdf", mimeType.getForFilename("file.pdf"));
    }

    @Test
    public void testPlaintextMimeType() {
        assertEquals("text/plain", mimeType.getForFilename("file.txt"));
    }

    @Test
    public void testHtmlMimeType() {
        assertEquals("text/html", mimeType.getForFilename("file.html"));
    }

    @Test
    public void testInvalidFile() throws Exception {
        assertEquals(null, mimeType.getForFilename("no-extension-file"));
    }

    @Test
    public void testLoadMimeTypes() throws Exception {
        mimeType.loadMimeTypes();
    }
}
