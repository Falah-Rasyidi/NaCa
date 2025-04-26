import java.io.IOException;

import app.Server;
import app.Client;

public class Chat {
    public static void main(String[] args) throws IOException {
        final int PORT_NUMBER = 3000;

        if (args.length > 0 && args[0].equals("server")) {
            new Server().server(PORT_NUMBER); 
        }
        else {
            new Client().initialise();
        }
    }
}