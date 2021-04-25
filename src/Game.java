
import java.util.*;
import exceptions.*;
import gamecontrol.GameControl;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        GameControl game = new GameControl(playerInput.checkPlayerInput());
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
