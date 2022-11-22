import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Username;


    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.Username = bufferedReader.readLine();
            clientHandlers.add(this);
        } catch (IOException e) {

        }
    }


    public void broadcast(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.Username.equals(Username)) {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void run() {
        String messageFromUser;

        while (socket.isConnected()) {
            try {
                messageFromUser = bufferedReader.readLine();
                broadcast(messageFromUser);
            } catch (IOException e) {

            }
        }
    }
}

