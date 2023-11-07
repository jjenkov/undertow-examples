package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class BinaryDataHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        setContentTypeFromURI(httpServerExchange);
        loadAndReturnData(httpServerExchange);
    }

    protected void setContentTypeFromURI(HttpServerExchange httpServerExchange) {
        String fileUri  = httpServerExchange.getRequestURI();
        String mimetype = MimeTypeDetector.detectMimeType(fileUri);
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, mimetype);
    }

    protected void loadAndReturnData(HttpServerExchange httpServerExchange) throws IOException {
        try{
            //byte[] bytes = FileUtil.loadFile(filePath);
            byte[] bytes = loadData(httpServerExchange);

            //Perhaps allocating a new ByteBuffer for every request is not optimal.
            // A single instance could be reused, although it then has to be big enough to hold all files.
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);

            buffer.clear();
            buffer.put(bytes);
            buffer.flip();

            httpServerExchange.getResponseSender().send(buffer);
            httpServerExchange.endExchange();
        } catch(Throwable t) {
            t.printStackTrace();
        }

    }

    protected abstract byte[] loadData(HttpServerExchange httpServerExchange) throws IOException;
}
