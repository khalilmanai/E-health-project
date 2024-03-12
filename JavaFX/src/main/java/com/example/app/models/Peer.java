package com.example.app.models;

import com.example.app.interfaces.PeerListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Peer {
    private final List<Socket> clientSockets = new ArrayList<>();
    private final PeerListener peerListener;

    public Peer(int port, PeerListener peerListener) {
        this.peerListener = peerListener;
        startListener(port);
    }

    private void startListener(int port) {
        Thread listenerThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                peerListener.onPeerStarted("Peer started, listening on port " + port);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    peerListener.onClientConnected(clientSocket);
                    clientSockets.add(clientSocket);
                    Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket, peerListener));
                    clientHandlerThread.start();
                }
            } catch (IOException e) {
                peerListener.onError(e.getMessage());
            }
        });
        listenerThread.start();
    }

    public void sendMessageToClients(String message) {
        for (Socket clientSocket : clientSockets) {
            try {
                peerListener.onMessageSending(message, clientSocket);
            } catch (IOException e) {
                peerListener.onError(e.getMessage());
            }
        }
    }
}