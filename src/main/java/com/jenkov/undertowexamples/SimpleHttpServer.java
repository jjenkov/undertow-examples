package com.jenkov.undertowexamples;

import io.undertow.Undertow;

public class SimpleHttpServer {

    public static void main(String[] args) {
        Undertow.Builder builder = Undertow.builder();
        Undertow undertow = builder
                .addHttpListener(80, "localhost")
                .setHandler(new SimpleHandler())
                .build();

        System.out.println("Undertow started");
        undertow.start();

    }
}
