package app;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * This class holds all the clients and continuously polls over them
 * If a client has a message, send the message to the server
 */
public class ClientHandler implements Runnable {
    private final LinkedBlockingQueue<Socket> CLIENT_QUEUE;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public ClientHandler(LinkedBlockingQueue<Socket> queue) {
        CLIENT_QUEUE = queue;
    }

    @Override
    public void run() {
        final int NO_CONTENT = 0;
        int id = 1;

        try {
            while (true) {
                // If theres a new client, grab from listener thread queue and add to clients array list
                if (!CLIENT_QUEUE.isEmpty()) {
                    clients.add(new Client(id++, CLIENT_QUEUE.poll()));
                }

                // Poll existing clients for messages
                for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); ) {
                    Client client = iterator.next();
                    DataInputStream dis = new DataInputStream(client.getSocket().getInputStream());

                    // Print message only if there's content
                    if (dis.available() != NO_CONTENT) {
                        String message = dis.readUTF();

                        if (message.equals("exit")) {
                            // Remove socket
                            System.out.println("\nDisconnected client #" + client.getID());
                            iterator.remove();
                            continue;
                        }

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
