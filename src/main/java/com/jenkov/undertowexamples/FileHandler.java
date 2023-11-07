package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.io.File;
import java.io.IOException;

public class FileHandler extends BinaryDataHandler implements HttpHandler {

    private String baseDirPath = null;

    public FileHandler(String baseDirPath) {
        this.baseDirPath = baseDirPath;
    }

    protected byte[] loadData(HttpServerExchange httpServerExchange) throws IOException {
        String fileUri  = httpServerExchange.getRequestURI();
        String filePath = (this.baseDirPath + fileUri).replace('/', File.separatorChar);

        byte[] bytes = FileUtil.loadFile(filePath);
        return bytes;
    }

}
