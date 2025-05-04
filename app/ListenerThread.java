package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * All this class does is listens for new clients.
 * After a client connects, pass it to the client handler via a queue?
 */
public class ListenerThread implements Runnable {
    private final LinkedBlockingQueue<Socket> CLIENT_QUEUE;
    private final int PORT_NUMBER;

    public ListenerThread(LinkedBlockingQueue<Socket> queue, int port) {
        CLIENT_QUEUE = queue;
        PORT_NUMBER = port;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);
            System.out.println("Server is listening on port " + PORT_NUMBER);

            while (true) {
                Socket socket = server.accept();
                System.out.println("client accepted");
                // After client connects, create new thread for them and put into queue
                CLIENT_QUEUE.add(socket);
            }

        } catch (IOException e) {
            System.out.println("Error creating server socket or accepting client");
            e.printStackTrace();
        }
    }
}
