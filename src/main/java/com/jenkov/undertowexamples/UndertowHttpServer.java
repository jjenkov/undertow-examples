package com.jenkov.undertowexamples;

import io.undertow.Undertow;

import java.io.IOException;

public class UndertowHttpServer {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Args missing: ");
            System.out.println("ipaddress    OS");
            return;
        }
        String ipAddress = args[0];
        String operatingSystem = args[1];
        int tcpPort = 80;

        System.out.println("ip address: " + ipAddress);
        System.out.println("OS        : " + operatingSystem);

        System.out.println("Undertow starting");

        System.out.println("Building Undertow server");
        Undertow.Builder builder = Undertow.builder();
        Undertow undertow = builder
                //.addHttpListener(80, "localhost")
                //.addHttpListener(80, "135.181.102.160")
                .addHttpListener(tcpPort, ipAddress)
                //.addHttpListener(80, "ec2-52-17-198-128.eu-west-1.compute.amazonaws.com")
                //.setHandler(new WebHandler(cmsHandler))
                .setHandler(new SimpleHandler())
                .build();

        System.out.println("Undertow started");
        undertow.start();

    }

}
