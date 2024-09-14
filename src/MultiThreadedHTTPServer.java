import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedHTTPServer {
    public static void main(String[] args) {

        try{
            try(ServerSocket serverSocket = new ServerSocket(8080)) {
                //Keep connexion opened
                while(true){
                    Socket client = serverSocket.accept();
                    Thread thread = new Thread(new ClientHandler(client));
                    thread.start();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
