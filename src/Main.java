
import java.util.*;
import exceptions.*;
import gameplay.*;

public class Main {
    /**
     * Elindítja a játékot, azzáltal hogy példányosítja a GameControl osztályt
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        new GameControl(playerInput.checkPlayerInput());
        sc.close();
    }
}
