package main;

import main.environment.Environment;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Client {

    private String serverUrl;
    private int port;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
        serverUrl = Environment.SERVER_URL;
        port = Environment.PORT;
    }

    public Client(String serverUrl, int port) {
        this.serverUrl = serverUrl;
        this.port = port;
    }

    public String send(float firstArg, float secondArg) {
        String result = null;
        try {
//            System.out.printf("Send %.2f and %.2f\n", firstArg, secondArg);
            String message = firstArg + "," + secondArg;

            openConnection();
            out.println(message);
            out.flush();
            String response = in.readLine();
//            System.out.println("Result: " + response);
            result = response;
            closeConnection();
        } catch (UnknownHostException ex) {
            System.err.println("Client: Unknown Host!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Client: Unknown Input/Output Error!");
            ex.printStackTrace();
        } finally {
            return result;
        }
    }

    public String send(String method, String[] args) {
        try {
            System.out.println(method + "#" + String.join("&", args));
            String message = method + "#" + String.join("&", args);

            openConnection();
            out.println(message);
            out.flush();
            String response = in.readLine();
            closeConnection();

            System.out.println(response);
            return response;
        } catch (UnknownHostException ex) {
            System.err.println("Client: Unknown Host!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Client: Unknown Input/Output Error!");
            ex.printStackTrace();
        } finally {
            return null;
        }
    }

    public void openConnection() throws UnknownHostException, IOException {
        socket = new Socket(serverUrl, port);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
