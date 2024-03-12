package com.example.app.interfaces;

import java.io.IOException;
import java.net.Socket;

public interface PeerListener {
    void onPeerStarted(String message);
    void onClientConnected(Socket clientSocket);
    void onMessageReceived(String message);
    void onMessageSending(String message, Socket clientSocket) throws IOException;
    void onError(String errorMessage);
}
