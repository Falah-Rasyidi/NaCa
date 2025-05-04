package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * All this class does is listens for new clients.
 * After a client connects, pass it to the client handler via a blocking queue. able to pass info between threads
 */
public class ListenerThread implements Runnable {
    private final LinkedBlockingQueue<Socket> CLIENT_QUEUE;
    private final int PORT_NUMBER;

    public ListenerThread(LinkedBlockingQueue<Socket> queue, int port) {
        CLIENT_QUEUE = queue;
        PORT_NUMBER = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);
            System.out.println("Server is listening on port " + PORT_NUMBER);

            while (true) {
                Socket socket = server.accept();
                System.out.println("\nClient accepted");

                // After client connects, place into queue to be picked up by client handler
                CLIENT_QUEUE.add(socket);
            }

        } catch (IOException e) {
            System.out.println("Error creating server socket or accepting client");
            e.printStackTrace();
        }
    }
}
