import backend.Manager;
import frontend.login.EnterPage;

public class Main {

    public static void main(String[] args) {
        Manager.start();
        new EnterPage();
    }
}