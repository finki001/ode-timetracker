package fhtw.timetracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeTrackerServer {

    public static final int PORT = 1337;

    public static void main(String[] args) {
        final ServerSocket server;
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server: waiting for connection");
        } catch (IOException e) {
            System.out.println("Error setting up ServerSocket: " + e.getMessage());
            return;
        }

        while (true) {
            try (final Socket socket = server.accept()) {
                System.out.println("Connected to client");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
