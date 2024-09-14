import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public class SocketThread extends Thread {

    private final ArrayBlockingQueue<Socket> queue;

    //Capacity is the max number of connexion waiting in the queue
    public SocketThread(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public void setSocket(Socket socket){
        this.queue.add(socket);
    }


    public void run(){
        try {
            Socket client = this.queue.take();

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            while(true){
                String line = reader.readLine();
                System.out.println(line);
                if(line.isEmpty()) break;
            }

            Date date = new Date();
            writer.write("HTTP/1.1 200 OK\r\n\r\n" + date);
            writer.flush();

            client.close();

            this.run();

        } catch (IOException | InterruptedException e) {}
    }
}
