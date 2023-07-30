package backend.network;

import backend.Manager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    public final Socket socket;
    public final Scanner scanner;
    public final PrintStream printer;

    public Connection() throws IOException {
        socket = new Socket("127.0.0.1", Manager.SERVER_PORT);
        printer = new PrintStream(socket.getOutputStream());
        scanner = new Scanner(socket.getInputStream());
    }

    public void send(Object message) {
        printer.println(message);
        printer.flush();
    }
}
