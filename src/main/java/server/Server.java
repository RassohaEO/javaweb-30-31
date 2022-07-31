package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9743);
            System.out.println("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try { while (true){
                            DataInputStream in = new DataInputStream(socket.getInputStream());
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                            String userMassage = in.readUTF();
                            System.out.println(userMassage);
                        out.writeUTF(userMassage.toUpperCase(Locale.ROOT));}


                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                    }
                });
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
