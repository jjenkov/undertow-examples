package com.jenkov.undertowexamples;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

/**
 * This is just a utility Main class - to debug stuff that doesn't work - for the other examples.
 * It has no other purpose besides that.
 * 
 */
public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\data\\projects\\tutorial-projects\\undertow-examples\\assets\\text-file.txt");

        System.out.println("File exists: " + file.exists());

        try(FileInputStream inputStream = new FileInputStream(file)){
            byte[] bytes = inputStream.readAllBytes();
        } catch(Throwable t) {
            t.printStackTrace();
        }

        try {
            FileUtil.loadFile(new File("assets\\text-file.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
