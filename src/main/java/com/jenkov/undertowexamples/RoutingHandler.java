package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.util.HashMap;
import java.util.Map;

public class RoutingHandler implements HttpHandler {

    private Map<String, HttpHandler> handlerMap = new HashMap<>();

    public void addHandler(String uri, HttpHandler handler) {
        this.handlerMap.put(uri, handler);
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        String uri = httpServerExchange.getRequestURI();

        HttpHandler handler = this.handlerMap.get(uri);

        if(handler != null) {
            handler.handleRequest(httpServerExchange);
        }

        // else - send back an HTTP 404. See a later example.

    }

}
