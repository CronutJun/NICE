package com.nicetcm.nibsplus.broker.msg;

import java.rmi.server.RMISocketFactory;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerRMISocketFactory extends RMISocketFactory {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerRMISocketFactory.class);

    /**
     * Creates a client socket connected to the specified host and port and writes out debugging info
     * @param host the host name
     * @param port the port number
     * @return a socket connected to the specified host and port.
     * @exception IOException if an I/O error occurs during socket creation
     */
    public Socket createSocket(String host, int port) throws IOException {
        logger.debug("creating socket to host : {} on port {}", host, port );
        return new Socket(host, port);
    }

    /**
     * Create a server socket on the specified port (port 0 indicates
     * an anonymous port) and writes out some debugging info
     * @param port the port number
     * @return the server socket on the specified port
     * @exception IOException if an I/O error occurs during server socket
     * creation
     */
    public ServerSocket createServerSocket(int port) throws IOException {

        logger.debug("creating ServerSocket on port {}", port);
        return new ServerSocket(port);

    }

}