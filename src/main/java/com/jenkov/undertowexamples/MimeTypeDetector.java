package com.jenkov.undertowexamples;

public class MimeTypeDetector {

    public MimeTypeDetector() {
    }

    public static String detectMimeType(String resourcePath) {
        if (resourcePath.endsWith(".txt")) {
            return "text/plain";
        } else if (resourcePath.endsWith(".html")) {
            return "text/html";
        } else if (resourcePath.endsWith(".htm")) {
            return "text/html";
        } else if (resourcePath.endsWith(".xml")) {
            return "text/xml";
        } else if (resourcePath.endsWith(".js")) {
            return "application/javascript";
        } else if (resourcePath.endsWith(".json")) {
            return "application/json";
        } else if (resourcePath.endsWith(".css")) {
            return "text/css";
        } else if (resourcePath.endsWith(".svg")) {
            return "image/svg+xml";
        } else if (resourcePath.endsWith(".png")) {
            return "image/png";
        } else if (resourcePath.endsWith(".ico")){
            return "image/x-icon";
        } else {
            return resourcePath.endsWith(".gif") ? "image/gif" : null;
        }
    }
}