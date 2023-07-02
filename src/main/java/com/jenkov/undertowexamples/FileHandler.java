package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.io.File;
import java.nio.ByteBuffer;

public class FileHandler implements HttpHandler {

    private String baseDirPath = null;

    public FileHandler(String baseDirPath) {
        this.baseDirPath = baseDirPath;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        String fileUri  = httpServerExchange.getRequestURI();
        String mimetype = MimeTypeDetector.detectMimeType(fileUri);
        String filePath = (this.baseDirPath + fileUri).replace('/', File.separatorChar);

        try{
            byte[] bytes = FileUtil.loadFile(filePath);

            //Perhaps allocating a new ByteBuffer for every request is not optimal.
            // A single instance could be reused, although it then has to be big enough to hold all files.
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);

            buffer.clear();
            buffer.put(bytes);
            buffer.flip();

            httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, mimetype);
            httpServerExchange.getResponseSender().send(buffer);
            httpServerExchange.endExchange();
        } catch(Throwable t) {
            t.printStackTrace();
        }

    }

}
