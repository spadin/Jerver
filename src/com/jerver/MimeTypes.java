package com.jerver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class MimeTypes {
    protected HashMap<String,String> mimeTypes = new HashMap<>();

    public MimeTypes() {
        try {
            this.loadMimeTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMimeTypes() throws IOException {
        Path mimeTypesPath = Paths.get("resources/mime.types");
        Scanner scanner = new Scanner(mimeTypesPath);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            mimeTypes.put(parts[0], parts[1]);
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
        return mimeTypes.get(extension);
    }
}
