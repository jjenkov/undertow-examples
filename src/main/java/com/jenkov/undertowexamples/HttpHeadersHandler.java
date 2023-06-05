package com.jenkov.undertowexamples;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;

public class HttpHeadersHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {

        //obtain header values for the Accept HTTP header
        HeaderValues acceptHeader = httpServerExchange.getRequestHeaders().get("Accept");

        System.out.println("");
        System.out.println("URI: " + httpServerExchange.getRequestURI());
        System.out.println("Accept header values: " + acceptHeader.size());

        //print each value out to System.out
        for(int i=0; i < acceptHeader.size(); i++) {
            System.out.println("" + i + " : " + acceptHeader.get(i));
        }

        httpServerExchange
                .getResponseSender()
                .send("<!DOCTYPE html><html><body><h1>HTTP Accept Headers Printed to System.out</h1></body></html>");

    }
}
