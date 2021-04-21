package game;

import java.util.*;
import gamearea.*;

public class GameControl{
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    private Tiles[][] tileBoard;
    Board board;
    
    
    public GameControl(int players){
        board = new Board(players);
        tileBoard = board.getBoard();

    }
    public void combat(){
        board.printBoard();
        System.out.println("x coordinate?");
        int x = sc.nextInt();
        System.out.println("y coordinate?");
        int y = sc.nextInt();
        if(tileBoard[x][y].isSelectable() && tileBoard[x][y].getOwner().name == "p1"){
            //int choice = sc.nextInt();
            printPossibleMoves(x, y, tileBoard[x][y].getOwner());
            
        }else{
            System.out.println("not valid move, pls choose again");
        }
    }
    
    private void upperNeighbour(int x, int y, Player player) {
        if( y > 0 && isNull(x, y-1) && tileBoard[x][y-1].getOwner().name != player.name){
            System.out.println("Upper neighbour: x=" + x + ", y=" + (y-1));
        }
    }
    private void bottomNeighbour(int x, int y, Player player) {
        if(y<=board.row && isNull(x, y+1) && tileBoard[x][y+1].getOwner().name != player.name){
            System.out.println("Left neighbour: x=" + x + ", y=" + (y+1));
        }
    }
    private void leftNeighbour(int x, int y, Player player) {
        if(x>0 && isNull(x-1, y) && tileBoard[x][y+1].getOwner().name != player.name){
            System.out.println("Bottom neighbour: x=" + (x-1) + ", y=" + y);
        }
    }
    private void rightNeighbour(int x, int y, Player player) {
        if(x<=board.row && isNull(x+1, y) && tileBoard[x+1][y].getOwner().name != player.name){
            System.out.println("Right neighbour: x=" + (x+1) + ", y=" + y);
        }
    }

    public boolean isNull(int x, int y){
        if(tileBoard[x][y] == null){
            return false;
        } else {
            return true;
        }
    }
    
    public void printPossibleMoves(int x, int y, Player player){
        upperNeighbour(x, y, player);
        bottomNeighbour(x, y, player);
        leftNeighbour(x, y, player);
        rightNeighbour(x, y, player);
    }


    public int rollDiceAttack(){
        int attack_dice_number = rand.nextInt(7-1)+1;;
        return attack_dice_number;
    }
    
    public int rollDiceDefend(){
        int defend_dice_number = rand.nextInt(7-1)+1;
        return defend_dice_number;
    }
    
    public static void changePosition(){
        
    }

}
