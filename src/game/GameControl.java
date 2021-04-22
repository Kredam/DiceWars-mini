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
    public void playerCombat(){
        while(true){
            combat();
            System.out.println("Press 9 to end game, Press 2 to continue");
            int end = sc.nextInt();
            if(end == 9){
                break;
            }
            if(end == 2){
                continue;
            }
        }
    }

    public void enemyCombat(){
        int choice;
        for (int i = 0; i < board.row ;i++) {
            board.printBoard();
            for (int j = 0; j < board.col; j++) {
                if(NeighboursAround(i, j, tileBoard[i][j].getOwner()) && tileBoard[i][j].getOwner().name != "p1"){
                    while(true){
                        choice = (int) (Math.random()*5-1)+1;
                        if(upperNeighbour(i, j, tileBoard[i][j].getOwner()) && choice == 1){
                            attack(i, j, i-1, j);
                            break;
                        }
                        if(bottomNeighbour(i, j, tileBoard[i][j].getOwner()) && choice == 2){
                            attack(i, j, i+1, j);
                            break;
                        }
                        if(leftNeighbour(i, j, tileBoard[i][j].getOwner()) && choice == 3){
                            attack(i, j, i, j-1);
                            break;
                        }
                        if(rightNeighbour(i, j, tileBoard[i][j].getOwner()) && choice == 4){
                            attack(i, j, i, j+1);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void combat(){
        board.printBoard();
        System.out.println("which row?");
        int OwnPosX = sc.nextInt();
        System.out.println("which column?");
        int OwnPosY = sc.nextInt();
        if(tileBoard[OwnPosX][OwnPosY].isSelectable() && 
            tileBoard[OwnPosX][OwnPosY].getOwner().name == "p1" &&
                NeighboursAround(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                    printPossibleMoves(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner());
                    int choice = sc.nextInt();
                    if(choice == 1 && upperNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                        attack(OwnPosX, OwnPosY, OwnPosX-1, OwnPosY);
                    }
                    if(choice == 2 && bottomNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                        attack(OwnPosX, OwnPosY, OwnPosX+1, OwnPosY);
                    }
                    if(choice == 3 && leftNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                        attack(OwnPosX, OwnPosY, OwnPosX, OwnPosY-1);
                    }
                    if(choice == 4 && rightNeigbourOwner(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                        attack(OwnPosX, OwnPosY, OwnPosX, OwnPosY+1);
                    }

        }else{
            System.out.println("Not your tile or The tile cannot be selected or no nearby neighbours, please choose again!");
        }
    }

    //decides what to do basded on the roll outcome
    public void attack(int attackX, int attackY, int defendX, int defendY){
        int attackValue = rollDiceAttack(tileBoard[attackX][attackY].getDice_num());
        int defendValue = rollDiceAttack(tileBoard[defendX][defendY].getDice_num());
        //checks the roll outcome, compares the two rolls
        if(attackValue > defendValue){
            changePositionOnWin(attackX, attackY, defendX, defendY);
        }
        if(attackValue < defendValue || attackValue == defendValue){
            changePositionOnLoss(attackX, attackY, defendX, defendY);
        }
        System.out.println("Attack roll: " + attackValue + ", Defend roll: " + defendValue);
        
    }
    //change position based on the roll result
    public void changePositionOnWin(int attackX, int attackY, int defendX, int defendY){
        tileBoard[defendX][defendY].setOwner(tileBoard[attackX][attackY].getOwner());
        tileBoard[defendX][defendY].setDice_num(tileBoard[attackX][attackY].getDice_num()-1);
        tileBoard[attackX][attackY].setDice_num(1);
    }
    public void changePositionOnLoss(int attackX, int attackY, int defendX, int defendY){
        tileBoard[attackX][attackY].setDice_num(1);
    }
    //roll the dice*dice amount the tile has
    public int rollDiceAttack(int dices){
        int numberOfTimes = 0;
        int DiceValue = 0;
        int dice_number = 0;
        while(numberOfTimes < dices){
            dice_number = (int) (Math.random()*7-1)+1;
            DiceValue+=dice_number;
            numberOfTimes++;
        }
        return DiceValue;
    }
    //checks if there is at least 1 neigbour you can attack
    private boolean NeighboursAround(int x, int y, Player player){
        if(upperNeighbour(x, y, player) || 
            bottomNeighbour(x, y, player) ||
                leftNeighbour(x, y, player) ||
                    rightNeigbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    //checks upperneighbour owner and handles to avoid out of bound Exception
    private boolean upperNeighbour(int x, int y, Player player) {
        if( x > 0 && upperNeighbourOwner(x, y, player)){
            return true;
        }else{
            return false;
        }
    }
    private boolean bottomNeighbour(int x, int y, Player player) {
        if(x<board.row-1 && bottomNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    private boolean leftNeighbour(int x, int y, Player player) {
        if(y>0 && leftNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    private boolean rightNeighbour(int x, int y, Player player) {
        if(y<board.col-1 && rightNeigbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    //checks if the tile you selected is not your own and not neutral
    private boolean upperNeighbourOwner(int x, int y, Player player){
        if(tileBoard[x-1][y].getOwner().name != player.name && notNeutralTile(x-1, y)){
            return true;
        } else {
            return false;
        }
    }
    private boolean bottomNeighbourOwner(int x, int y, Player player){
        if(tileBoard[x+1][y].getOwner().name != player.name && notNeutralTile(x+1, y)){
            return true;
        } else {
            return false;
        }
    }
    private boolean leftNeighbourOwner(int x, int y, Player player){
        if(tileBoard[x][y-1].getOwner().name != player.name && notNeutralTile(x, y-1)){
            return true;
        } else {
            return false;
        }
    }
    private boolean rightNeigbourOwner(int x, int y, Player player){
        if(tileBoard[x][y+1].getOwner().name != player.name && notNeutralTile(x, y+1)){
            return true;
        } else {
            return false;
        }
    }    
    public boolean notNeutralTile(int x, int y){
        if(tileBoard[x][y].getOwner().name != "neutral"){
            return true;
        } else {
            return false;
        }
    }
    //print possible neighbours that you can attack
    public void printPossibleMoves(int x, int y, Player player){
        if(upperNeighbour(x, y, player)){
            System.out.println("Press 1 to attack upper neighbour");
        }
        if(bottomNeighbour(x, y, player)){
            System.out.println("Press 2 to attack bottom neighbour");
        }
        if(leftNeighbour(x, y, player)){
            System.out.println("Press 3 to attack left neighbour");
        }
        if(rightNeighbour(x, y, player)){
            System.out.println("Press 4 to attack right neighbour");
        }
    }
}
    
