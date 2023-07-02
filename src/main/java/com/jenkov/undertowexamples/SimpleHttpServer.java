package com.jenkov.undertowexamples;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;

public class SimpleHttpServer {

    public static void main(String[] args) {
        //HttpHandler handler = new SimpleHandler();
        //HttpHandler handler = new HttpHeadersHandler();
        //HttpHandler handler = new FileHandler("assets");
        HttpHandler handler = new FileHandler("C:\\data\\projects\\tutorial-projects\\undertow-examples\\assets");

        Undertow.Builder builder = Undertow.builder();
        Undertow undertow = builder
                .addHttpListener(80, "localhost")
                .setHandler(handler)
                .build();

        System.out.println("Undertow started");
        undertow.start();

    }
}
