import backend.Manager;
import frontend.login.EnterPage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int locationPart = new Scanner(System.in).nextInt();
        Manager.start(locationPart);
        System.out.println(Manager.SCREEN_HEIGHT);
        System.out.println(Manager.SINGLE_BLOCK_HEIGHT);
        System.out.println(Manager.SCREEN_WIDTH);
        System.out.println(Manager.SINGLE_BLOCK_WIDTH);
        new EnterPage();
    }
}