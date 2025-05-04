package app;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * This class holds all the clients and continuously polls over them
 * If a client has a message, send the message to the server
 */
public class ClientHandler implements Runnable {
    private final LinkedBlockingQueue<Socket> CLIENT_QUEUE;
    private ArrayList<Socket> clients;

    public ClientHandler(LinkedBlockingQueue<Socket> queue) {
        CLIENT_QUEUE = queue;
        clients = new ArrayList<Socket>();
    }

    @Override
    public void run() {
        final int NO_CONTENT = 0;

        try {
            while (true) {
                // If theres a new client, assign them to a new Client object and place into clients list
                if (!CLIENT_QUEUE.isEmpty()) {
                    clients.add(CLIENT_QUEUE.poll());
                }

                // Poll existing clients for messages
                for (Socket socket : clients) {
                    DataInputStream dis = new DataInputStream(socket.getInputStream());

                    // Print message only if there's actual content
                    if (dis.available() != NO_CONTENT) {
                        String message = dis.readUTF();
                        System.out.printf("%nMessage received!%n    %s%n", message);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
