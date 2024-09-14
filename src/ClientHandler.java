import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientHandler implements Runnable {

    private Socket client = null;

    public ClientHandler(Socket socket) {
        this.client = socket;
    }
    public void run(){
        try{
            //Reading and writing to the stream
            BufferedReader requestReader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            BufferedWriter responseWriter = new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream()));

            //Read all lines
            while(true) {
                String requestLine = requestReader.readLine();
                System.out.println(requestLine);
                if (requestLine.isEmpty()) break;
            }
            //Build response
            Date today = new Date();
            responseWriter.write("HTTP/1.1 200 OK\r\n\r\n" + today);
            responseWriter.flush();
            //Close client and I/O Stream
            this.client.close();
        }

        catch(IOException e){
            try {
                if (this.client != null){
                    this.client.close();
                }
            } catch (IOException e1) {}

        }
    }
}
