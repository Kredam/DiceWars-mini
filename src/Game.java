import java.util.*;

import game.GameControl;
import gamearea.Board;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many player between 2 and 4?");
        int player = sc.nextInt();
        GameControl game = new GameControl(player);
        game.combat();
        sc.close();
        
    }
}
