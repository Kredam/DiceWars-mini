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
        System.out.println("which row?");
        int OwnPosX = sc.nextInt();
        System.out.println("which column?");
        int OwnPosY = sc.nextInt();
        if(tileBoard[OwnPosX][OwnPosY].isSelectable() && tileBoard[OwnPosX][OwnPosY].getOwner().name == "p1"){
            printPossibleMoves(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner());
        }else{
            System.out.println("Not your tile or The tile had been selected, please choose again!");
        }
    }
    
    public void decideWinner(int attackX, int attackY, int defendX, int defendY){
        if(rollDiceAttack(tileBoard[attackX][attackY].getDice_num()) > rollDiceAttack(tileBoard[defendX][defendY].getDice_num())){

        }
        if(rollDiceAttack(tileBoard[attackX][attackY].getDice_num()) < rollDiceAttack(tileBoard[defendX][defendY].getDice_num())){
        
        }
        if(rollDiceAttack(tileBoard[attackX][attackY].getDice_num()) == rollDiceAttack(tileBoard[defendX][defendY].getDice_num())){
        
        }
    }

    private boolean upperNeighbour(int x, int y, Player player) {
        if( x > 0 && upperNeighbourOwner(x, y, player)){
            System.out.println("Upper neighbour: x=" + x + ", y=" + (y-1));
            return true;
        }else{
            return false;
        }
    }
    private boolean bottomNeighbour(int x, int y, Player player) {
        if(x<board.row && bottomNeighbourOwner(x, y, player)){
            System.out.println("Bottom neighbour: x=" + x + ", y=" + (y+1));
            return true;
        } else {
            return false;
        }
    }
    private boolean leftNeighbour(int x, int y, Player player) {
        if(y>0 && leftNeighbourOwner(x, y, player)){
            System.out.println("Left neighbour: x=" + (x-1) + ", y=" + y);
            return true;
        } else {
            return false;
        }
    }
    private boolean rightNeighbour(int x, int y, Player player) {
        if(y<board.col && rightNeigbourOwner(x, y, player)){
            System.out.println("Right neighbour: x=" + (x+1) + ", y=" + y);
            return true;
        } else {
            return false;
        }
    }
    private boolean upperNeighbourOwner(int x, int y, Player player){
        if(tileBoard[x-1][y].getOwner().name != player.name && tileBoard[x-1][y].getOwner().name != "neutral"){
            return true;
        } else {
            return false;
        }
    }
    private boolean bottomNeighbourOwner(int x, int y, Player player){
        if(tileBoard[x+1][y].getOwner().name != player.name && tileBoard[x+1][y].getOwner().name != "neutral"){
            return true;
        } else {
            return false;
        }
    }
    private boolean leftNeighbourOwner(int x, int y, Player player){
        if(tileBoard[x][y-1].getOwner().name != player.name && tileBoard[x][y-1].getOwner().name != "neutral"){
            return true;
        } else {
            return false;
        }
    }
    private boolean rightNeigbourOwner(int x, int y, Player player){
        if(tileBoard[x][y+1].getOwner().name != player.name && tileBoard[x][y+1].getOwner().name != "neutral"){
            return true;
        } else {
            return false;
        }
    }    
    public boolean neutralTile(int x, int y){
        if(tileBoard[x][y].getOwner().name == "neutral"){
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


    public int rollDiceAttack(int dices){
        int numberOfTimes = 0;
        int attackDiceValue = 0;
        while(numberOfTimes < dices){
            int attack_dice_number = rand.nextInt(7-1)+1;
            attackDiceValue+=attack_dice_number;
            numberOfTimes++;
        }
        return attackDiceValue;
    }
    public int rollDiceDefend(int dices){
        int numberOfTimes = 0;
        int defendDiceValue = 0;
        while(numberOfTimes < dices){
            int defend_dice_number = rand.nextInt(7-1)+1;
            defendDiceValue+=defend_dice_number;
            numberOfTimes++;
        }
        return defendDiceValue;
    }
    
    public static void changePosition(){
        
    }

}