import java.util.*;
import gamearea.*;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many player between 2 and 4?");
        int player = sc.nextInt();
        Board board = new Board(player);
        board.printBoard();
        sc.close();
        
    }
}
