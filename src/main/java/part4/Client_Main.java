package part4;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/* instancie 2 clients avec interface graphique */
public class Client_Main {
    public static void main(String[] args) throws IOException, TimeoutException {
        new ClienInterface("Ahmed", "commQueue");
        new ClienInterface("Amal", "commQueue");
        // Instancier part3.UserReveiver_graphicalInterface
    }
}
