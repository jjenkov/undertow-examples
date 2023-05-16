package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class SimpleHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange
                .getResponseSender()
                .send("<!DOCTYPE html><html><body><h1>Hello World from Undertow</h1></body></html>");

    }
}
