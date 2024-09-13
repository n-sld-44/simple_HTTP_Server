import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHTTPServer {

    public static void main(String[] args) throws Exception {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080");
        while (true) {
            try (Socket clientSocket = server.accept()) {
                ;
                // 1. Read HTTP request from the client socket
                InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();
                while (!(line.isEmpty())) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                // 2. Prepare an HTTP response
                Date today = new Date();
                String httpresponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                // 3. Send HTTP response to the client
                clientSocket.getOutputStream().write(httpresponse.getBytes("UTF-8"));
                // 4. Close the socket

            }
        }

    }
}
