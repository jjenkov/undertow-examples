package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
    This Undertow HttpHandler is capable of downloading a file from a remote server, and return it to the
    web browser. Thus, this handler acts as a "proxy" for another web server. In this concrete class the
    URL is hardcoded, but it could be determined based on the request URI (available via the HttpServerExchange parameter)
 */
public class DownloadProxyHandler extends BinaryDataHandler implements HttpHandler {

    @Override
    protected byte[] loadData(HttpServerExchange httpServerExchange) throws IOException {
        URL url = new URL("https://jenkov.com");

        try(InputStream input = url.openStream()) {

            return input.readAllBytes();

        }

    }
}
