import backend.Manager;
import backend.network.request.Request;
import frontend.login.EnterPage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int locationPart = new Scanner(System.in).nextInt();
        Manager.start(locationPart);
        new EnterPage();
        Runtime.getRuntime().addShutdownHook(new Thread(Request::close));
    }
}