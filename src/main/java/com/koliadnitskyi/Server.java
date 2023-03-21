package com.koliadnitskyi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
    private static Socket client;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        final String error = "An error has occurred in the application";
        final String infoStartServer = "Server started!";
        final String infoFinishServer = "Server closed!";
        final String securityQuestion = "What is palyanica?";
        final String parting = "All the best to you. Goodbye. ";
        final String terminated = "Everything is clear with you. Cooperation terminated.";
        final String bread = "Bread";
        final String hello = "Hello!";
        try {
            try {
                server = new ServerSocket(8081);
                System.out.println(infoStartServer);
                client = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    out.write(hello + "\n");
                    out.flush();
                    String word = in.readLine();
                    if (new Verification().languageControl(word)) {
                        out.write(securityQuestion + "\n");
                        out.flush();
                        if (in.readLine().equals(bread)) {
                            out.write(parting + LocalDateTime.now() + "\n");
                            out.flush();
                        } else {
                            out.write(terminated + "\n");
                            out.flush();
                        }
                    }
                } finally {
                    client.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println(infoFinishServer);
                server.close();
            }
        } catch (IOException e) {
            System.err.println(error);
        }
    }
}
