package app;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public void server(int port) throws IOException {
        System.out.println("This is the server");

        // Define ServerSocket (makes it accessible for communication from other machines)
        ServerSocket socket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        // Continuously listen for any incoming messages
        // Regular socket created after message received so that both server and client on same wavelength
        boolean running = true;
        Socket s = socket.accept();
        while (running) {
            
            // Create new thread for each incoming client
            // Thread clientThread = new Thread(new Client(s));
            // clientThread.start();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String message = dis.readUTF();

            if (message.equals("exit")) {
                System.out.println("\nTerminating connection");
                socket.close();
                return;
            }

            System.out.printf("%nMessage received!%n    %s%n", message);
        }
    }
}
