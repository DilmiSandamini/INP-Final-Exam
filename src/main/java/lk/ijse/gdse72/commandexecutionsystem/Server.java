package lk.ijse.gdse72.commandexecutionsystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Server {
    public static void main(String [] args){
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("Server Started...");

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String message = dataInputStream.readUTF();
                System.out.println("Client: " + message);

                if (message.equalsIgnoreCase("TIME")) {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    break;
                }

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client Disconnected");
                    break;
                }

                System.out.println("Server: ");
                String response = scanner.nextLine();

                dataOutputStream.writeUTF(response);
                dataOutputStream.flush();
            }

            socket.close();
            serverSocket.close();
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
