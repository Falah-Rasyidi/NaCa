package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/*
 * All this class does is listens for new clients.
 * After a client connects, pass it to the client handler via a blocking queue?
 */
public class ListenerThread implements Runnable {
    private final BlockingQueue<Socket> CLIENT_QUEUE;
    private final int PORT_NUMBER;

    public ListenerThread(BlockingQueue<Socket> queue, int port) {
        CLIENT_QUEUE = queue;
        PORT_NUMBER = port;
    }

    
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);

            while (true) {
                Socket socket = server.accept();

                // After client connects, place into queue and send over to client handler
                CLIENT_QUEUE.put(socket);
            }

        } catch (IOException e) {
            System.out.println("Error creating server socket or accepting client");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Error putting client socket into blocking queue");
            e.printStackTrace();
        }
    }
}
