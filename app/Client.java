package app;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {
    private Socket socket;

    public Client() {
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void initialise() {
        System.out.println("This is the client");
        final String IP_ADDRESS = "localhost";
        final int PORT_NUMBER = 3000;
        
        try {
            // Define socket for client and attempt connection
            this.socket = new Socket(IP_ADDRESS, PORT_NUMBER);

            // If connection successful, move to run method
            run();

        } catch (Exception e) {
            System.out.println("Can't connect to server");
        }
    }

    @Override
    public void run() {
        Scanner scnr = new Scanner(System.in);

        try {
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
