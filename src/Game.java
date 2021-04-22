import java.util.*;

import game.GameControl;
import gamearea.Board;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many player between 2 and 4?");
        int player = sc.nextInt();
        int end = sc.nextInt();
        GameControl game = new GameControl(player);
        while(true){
            game.combat();
            System.out.println("9 for end");
            if(end == 9){
                break;
            }
        }
        sc.close();
        
    }
}
