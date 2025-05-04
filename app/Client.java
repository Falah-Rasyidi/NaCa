package app;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private int id;
    private Socket socket;

    public Client(int id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }

    public int getID() {
        return this.id;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public static void client(int port) {
        Scanner scnr = new Scanner(System.in);

        try {
            // Create client socket
            Socket socket = new Socket("localhost", port);

            // Prepare outgoing message
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String message = scnr.nextLine();
            dos.writeUTF(message);
            dos.flush();

            while (!message.equals("exit")) {
                message = scnr.nextLine();
                dos.writeUTF(message);
                dos.flush();
            }

            // Close everything
            scnr.close();
            dos.close();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
