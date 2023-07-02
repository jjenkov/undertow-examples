package com.jenkov.undertowexamples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileUtil {

    public static byte[] loadFile(String filePath) throws IOException {
        try(FileInputStream input = new FileInputStream(filePath)) {
            return input.readAllBytes();
        }
    }

    public static byte[] loadFile(File filePath) throws IOException {
        try(FileInputStream input = new FileInputStream(filePath)) {
            return input.readAllBytes();
        }
    }

    public static byte[] loadFile(Path filePath) throws IOException {
        try(FileInputStream input = new FileInputStream(filePath.toFile())) {
            return input.readAllBytes();
        }
    }


}
