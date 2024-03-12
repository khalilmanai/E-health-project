package com.example.app.models;

import com.example.app.interfaces.PeerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final PeerListener peerListener;

    public ClientHandler(Socket clientSocket, PeerListener peerListener) {
        this.clientSocket = clientSocket;
        this.peerListener = peerListener;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                String message = reader.readLine();
                if (message == null || message.equalsIgnoreCase("bye")) {
                    break;
                }
                peerListener.onMessageReceived(message);
            }
            clientSocket.close();
        } catch (IOException e) {
            peerListener.onError(e.getMessage());
        }
    }
}
