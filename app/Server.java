package app;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    public static void server(LinkedBlockingQueue<Socket> queue, int port) throws IOException {
        // Start listener thread. Sole job is to listen for new clients
        new Thread(new ListenerThread(queue, port)).start();

        // Start client handler thread. Grabs new clients and polls over them for messages
        new Thread(new ClientHandler(queue)).start();
    }
}
