
import java.util.*;
import exceptions.*;
import gamecontrol.GameControl;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many player between 2 and 4?");
        GameControl game = new GameControl(playerInput.players());
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
