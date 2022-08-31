import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    //declare variables
    //Constructor
    Socket socket;
    int clientId;
    Database db;


    public ClientHandler(Socket socket, int clientId, Database db) {
        //complete the constructor
        this.socket = socket;
        this.clientId = clientId;
        this.db = db;
    }

    public void run() {
            /*System.out.println("ClientHandler started...");
              Create I/O streams to read/write data, PrintWriter and BufferedReader
              Receive messages from the client and send replies, until the user types "stop"
                  System.out.println("Client sent the artist name " + clientMessage);
                  Request the number of titles from the db
                  Send reply to Client:
                  outToClient.println("Number of titles: " + titlesNum + " records found");

              System.out.println("Client " + clientId + " has disconnected");
              outToClient.println("Connection closed, Goodbye!");
              Close I/O streams and socket*/





        System.out.println("ClientHandler started...");

        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage;

            while (!(clientMessage = inFromClient.readLine()).equals("exit")){

                System.out.println("Client sent the artist name "+clientMessage);
                int titlesNum=db.getTitles(clientMessage);
                outToClient.println("Number of titles: " + titlesNum + " records found");

            }

            System.out.println("Client " + clientId + " has disconnected");
            outToClient.println("Connection closed, Goodbye!");




            inFromClient.close();
            outToClient.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
