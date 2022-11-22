import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Username;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1234;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.Username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {

        }

    }

    public void sendMessage() {
        try {
            bufferedWriter.write(Username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);

            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(Username + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {

        }
    }


    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromOthers;
                while (socket.isConnected()) {
                    try {
                        messageFromOthers = bufferedReader.readLine();
                        System.out.println(messageFromOthers);
                    } catch (IOException e) {

                    }
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username : ");
            String username = scanner.nextLine();
            Socket socket = new Socket(HOST, PORT);

            Client client = new Client(socket, username);
            client.listenForMessage();
            client.sendMessage();
        } catch (IOException e) {

        }

    }
}
