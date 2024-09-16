package com.taskify;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Helper {
    public String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }

    public String getPublicDocumentPath() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return "D:/Users/Public/Documents";
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            return "/Users/Public/Documents";
        } else {
            throw new UnsupportedOperationException("Unsupported Operating System: " + os);
        }
    }

    public void validateDirectoryExists(String filePath) {
        File directory = new File(filePath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + filePath);
            } else {
                System.out.println("Failed to create directory: " + filePath);
            }
        }
    }

    public void convertToJson(Map<String, String> taskInfo) {
    }

}
