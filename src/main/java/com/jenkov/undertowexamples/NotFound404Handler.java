package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

public class NotFound404Handler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange.setStatusCode(404);
        httpServerExchange.getResponseSender().send("URI not found: " + httpServerExchange.getRequestURI());
    }


}
