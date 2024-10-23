package com.github.marcelektro;

import lombok.val;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.bbottema.javasocksproxyserver.SocksServer;
import org.bbottema.javasocksproxyserver.auth.UsernamePasswordAuthenticator;

import java.util.Objects;

@Slf4j
public class SocksProxyServer {

    private static final int DEFAULT_PORT = 2348;


    public static void main(String[] args) {

        val portEnv = System.getenv("PORT");
        int port;
        try {
            if (portEnv == null) {
                port = DEFAULT_PORT;
                log.info("Using default port {}.", DEFAULT_PORT);
            }
            else {
                port = Integer.parseInt(portEnv);
                log.info("Using port {}.", port);
            }

        } catch (NumberFormatException ignored) {
            log.error("Invalid port number provided: `{}`.", portEnv);
            System.exit(-1);
            return;
        }

        val socksServer = new SocksServer(port);


        val username = System.getenv("AUTH_USERNAME");
        val password = System.getenv("AUTH_PASSWORD");

        if (username != null && !username.isEmpty()) {

            if (password == null || password.isEmpty()) {
                log.error("No authentication password provided.");
                System.exit(-1);
                return;
            }

            log.info("Authentication enabled with username `{}`.", username);

            socksServer.setAuthenticator(new UsernamePasswordAuthenticator(false) {

                @Override
                public boolean validate(String u, String p) {
                    return Objects.equals(username, u) && Objects.equals(password, p);
                }

            });

        } else {
            log.warn("[!] No authentication is used, server will be open to the public [!]");
        }


        if (System.getenv("DEBUG") != null && System.getenv("DEBUG").equalsIgnoreCase("true")) {
            log.info("Debug mode enabled");
        } else {
            ((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.INFO);
        }

        log.info("Starting server on port {}...", port);
        socksServer.start();
        log.info("Listening for connections!");

    }

}
