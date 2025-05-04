import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import app.Server;
import app.Client;

public class Chat {
    public static void main(String[] args) throws IOException {
        final LinkedBlockingQueue<Socket> CLIENT_QUEUE = new LinkedBlockingQueue<Socket>();
        final int PORT_NUMBER = 3000;

        if (args.length > 0 && args[0].equals("server")) {
            Server.server(CLIENT_QUEUE, PORT_NUMBER);
        }
        else {
            Client.client(PORT_NUMBER);
        }
    }
}