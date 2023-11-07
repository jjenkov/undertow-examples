package com.jenkov.undertowexamples;

import javax.net.ssl.*;
import java.io.File;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * This class can set SSLContexts for an Undertow server - both for downloading a file (without verifying the remote certificate)
 * and for using an SSL certificate with an Undertow server.
 */
public class SSLUtil {

    public static final int SERVER_OS_WINDOWS = 1;
    public static final int SERVER_OS_LINUX = 2;


    public static SSLContext createServerSslContext(int serverOS, String keystorePath) {
        SSLContext context;
        try{
            System.out.println("Keystore loading");
            var keyStore = KeyStore.getInstance(new File(keystorePath), "abcdefgh".toCharArray());
            System.out.println("Keystore loaded");

            System.out.println("Creating KeyManagerFactory");
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, "abcdefgh".toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
            System.out.println("KeyManagerFactory created");

            System.out.println("Initializing SSLContext");
            System.out.println("Creating SecureRandom");

            SecureRandom instanceStrong =
            switch(serverOS) {

                case SERVER_OS_WINDOWS -> SecureRandom.getInstanceStrong();
                case SERVER_OS_LINUX   -> SecureRandom.getInstance("NativePRNGNonBlocking");
                default -> SecureRandom.getInstanceStrong();
            };

            System.out.println("SecureRandom created");
            System.out.println("Creating SSLContext");
            context = SSLContext.getInstance("TLS");
            System.out.println("SSLContext created");
            context.init(keyManagers, null, instanceStrong);
            System.out.println("SSLContext KeyManagers set");
            System.out.println("SSLContext initialized");
        } catch (Exception e) {
            System.out.println("Error initializing SSLContext: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error creating server SSLContext: " + e.getMessage(), e);
        }
        System.out.println("SSL configured");
        System.out.println("Keystore loaded");
        return context;
    }

    public static void setDownloadSslContext() {
        // A TrustManager for downloading files via HTTPS - without verifying the host certificate.
        System.out.println("Creating Download TrustManager");
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        System.out.println("Download TrustManager created");

        System.out.println("Creating HostnameVerifier");
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        System.out.println("HostnameVerifier created");

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            // Install trust all certificates SSLContext
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating SSLContext for file download (via URL class).");
        }

        System.out.println("Download SSLContext created");
    }
}
