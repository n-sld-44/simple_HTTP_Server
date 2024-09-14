import java.net.ServerSocket;
import java.net.Socket;

public class PooledHTTPServer {
    public static void main(String[] args) {

        SocketThread thread1 = new SocketThread(1);
        thread1.start();

        SocketThread thread2 = new SocketThread(1);
        thread2.start();

        try{
            try(ServerSocket serverSocket = new ServerSocket(8080)) {
                int i = 0;

                while(true) {
                    Socket client = serverSocket.accept();

                    if ((i&1)==1){
                        thread1.setSocket(client);
                    }
                    else {
                        thread2.setSocket(client);
                    }
                    i++;
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
