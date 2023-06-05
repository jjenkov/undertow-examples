package com.jenkov.undertowexamples;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;

public class SimpleHttpServer {

    public static void main(String[] args) {
        //HttpHandler handler = new SimpleHandler();
        HttpHandler handler = new HttpHeadersHandler();

        Undertow.Builder builder = Undertow.builder();
        Undertow undertow = builder
                .addHttpListener(80, "localhost")
                .setHandler(handler)
                .build();

        System.out.println("Undertow started");
        undertow.start();

    }
}
