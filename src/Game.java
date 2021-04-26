
import java.util.*;
import exceptions.*;
import gamecontrol.GameControl;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GameControl game = new GameControl(playerInput.checkPlayerInput());
        game.start();
        sc.close();
    }
}
