import backend.Manager;
import backend.network.request.Request;
import frontend.login.EnterPage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("enter game location:\n0 1\n2 3");
        int locationPart = new Scanner(System.in).nextInt();
        Manager.start(locationPart);
        new EnterPage();
        Runtime.getRuntime().addShutdownHook(new Thread(Request::close));
    }
}