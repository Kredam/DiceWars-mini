import java.util.*;

import game.GameControl;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many player between 2 and 4?");
        int player = sc.nextInt();
        GameControl game = new GameControl(player);
        while(true){
            game.combat();
            
            System.out.println("Press 1 to quit game");
            if(sc.nextInt() == 1){
                break;
            }
            
        }
        
        sc.close();
    }
}
