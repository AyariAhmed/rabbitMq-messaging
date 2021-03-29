package part4;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class nClients {
    public static void main(String[] args) throws IOException, TimeoutException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrer le nombre de client : ");
        int n = sc.nextInt();
        for (int i=0;i<n;i++){

            new ClienInterface("u-"+i, "commQueue");
        }
        // Instancier part3.UserReveiver_graphicalInterface

    }
}
