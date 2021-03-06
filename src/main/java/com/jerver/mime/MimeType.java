package com.jerver.mime;

import com.jerver.resource.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class MimeType {
    protected HashMap<String, String> mimeType = new HashMap<String, String>();
    public static final MimeType INSTANCE = new MimeType();

    private MimeType() {
        this.loadMimeTypes();
    }
    public void loadMimeTypes() {
        String mimeTypes = Resource.getStringForResource("mime.types");
        Scanner scanner = new Scanner(mimeTypes);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            mimeType.put(parts[0], parts[1]);
        }

        scanner.close();
    }

    public String getFilenameExtension(String filename) {
        String extension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i+1);
        }

        return extension;
    }

    public String getForFilename(String filename) {
        String extension = this.getFilenameExtension(filename);
        return mimeType.get(extension);
    }
}
