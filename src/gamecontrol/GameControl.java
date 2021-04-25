package gamecontrol;

import java.util.*;

import exceptions.playerInput;
import gamearea.*;
import players.Players;

public class GameControl{
    private Scanner sc = new Scanner(System.in);
    private Tiles[][] tileBoard;
    private Board board;
    
    
    public GameControl(int players){
        board = new Board(players);
        tileBoard = board.getBoard();
    }
    public void combat(){
        while(true){
            while(playerHasSelectableTiles(board.p1)){
                playerCombat();
                System.out.println("Press 9 to end game, Press 2 to continue");
                int state = sc.nextInt();
                if(state == 9){
                    giveDicesAtTheEndOfYourTurn(board.p1);
                    break;
                }
                if(state == 2){
                    continue;
                }
            }
            while(true){
                initiateEnemyCombat();
                break;
            }
        }
    }

    public void initiateEnemyCombat(){
        if(board.getPlayers() == 2){
            enemyCombat(board.p2);
        }
        if(board.getPlayers() == 3){
            enemyCombat(board.p2);
            enemyCombat(board.p3);
        }
        if(board.getPlayers()== 4){
            enemyCombat(board.p2);
            enemyCombat(board.p3);
            enemyCombat(board.p4);
        }
            
    }

    public boolean playerHasSelectableTiles(Players player){
        return true;
    }

    //as the name suggest share the amount of dices randomly(k=tilesYouOwn/2)
    private void giveDicesAtTheEndOfYourTurn(Players player){
        int amountOfDicesToShare=player.getPlayerTile()/2;
        int iterate = 1;
        while(true){
            int dice = 9;
            int randomRow=board.randomRow();
            int randomCol=board.randomCol();
            int upperRange = dice - tileBoard[randomRow][randomCol].getDice_num();
            int range = (int) (Math.random()*upperRange-1)+1;
            if(tileBoard[randomRow][randomCol].getOwner().name==player.name && iterate < player.getPlayerTile()){
                    tileBoard[randomRow][randomCol].setDice_num(range+tileBoard[randomRow][randomCol].getDice_num());
                    amountOfDicesToShare-=range;
                    iterate++;
            }
            if(tileBoard[randomRow][randomCol].getOwner().name==player.name && iterate < player.getPlayerTile()){
                    tileBoard[randomRow][randomCol].setDice_num(amountOfDicesToShare+tileBoard[randomRow][randomCol].getDice_num());
                    iterate++;
                    break;
            }
            
        }
    }


    //separate combat created for player and ai(mainly for readibility and input handling)
    public void enemyCombat(Players player){
        int choice;
        for (int i = 0; i < board.getRow() ;i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(neighboursAround(i, j, tileBoard[i][j].getOwner()) && 
                tileBoard[i][j].getOwner().name == player.name &&
                        tileBoard[i][j].isSelectable()){
                            while(true){
                                choice = (int) (Math.random()*5-1)+1;
                                if(choice == 1 && upperNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i-1, j);
                                    break;
                                }
                                if(choice == 2 && bottomNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i+1, j);
                                    break;
                                }
                                if(choice == 3 && leftNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i, j-1);
                                    break;
                                }
                                if(choice == 4 && rightNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i, j+1);
                                    break;
                                }
                            }
                        }
                }
        }
        giveDicesAtTheEndOfYourTurn(player);
    }
    public void playerCombat(){
            board.printBoard();
            System.out.println("which row?");
            int OwnPosX = playerInput.posX(board.getRow());
            System.out.println("which column?");
            int OwnPosY = playerInput.posY(board.getRow());
            if(tileBoard[OwnPosX][OwnPosY].isSelectable() && 
                tileBoard[OwnPosX][OwnPosY].getOwner().name == "p1" &&
                    neighboursAround(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                        do{
                            try{
                                printPossibleMoves(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner());
                                int choice = sc.nextInt();
                                if(choice == 1 && upperNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                    attack(OwnPosX, OwnPosY, OwnPosX-1, OwnPosY);
                                    break;
                                }
                                else if(choice == 2 && bottomNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                    attack(OwnPosX, OwnPosY, OwnPosX+1, OwnPosY);
                                    break;
                                }
                                else if(choice == 3 && leftNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                    attack(OwnPosX, OwnPosY, OwnPosX, OwnPosY-1);
                                    break;
                                }
                                else if(choice == 4 && rightNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                    attack(OwnPosX, OwnPosY, OwnPosX, OwnPosY+1);
                                    break;
                                }else{
                                    System.out.println("You've choosen an attack option that is invalid for this tile, please choose again!");
                                }
                            }catch(InputMismatchException e){
                                System.out.println("Please enter a number!");
                                sc.nextLine();
                            }
                    }while(true);
    
            }else{
                System.out.println("Not your tile or The tile cannot be selected or no nearby neighbours, please choose again!");
            }
    }

    //attack with the correct amount of dices and handles the outcome
    public void attack(int attackX, int attackY, int defendX, int defendY){
        int attackValue = rollDiceAttack(tileBoard[attackX][attackY].getDice_num());
        int defendValue = rollDiceAttack(tileBoard[defendX][defendY].getDice_num());
        //checks the roll outcome, compares the two rolls
        if(attackValue > defendValue){
            changePositionOnWin(attackX, attackY, defendX, defendY);
            tileBoard[attackX][attackY].getOwner().increasePlayerTile();
            tileBoard[defendX][defendY].getOwner().decreasePlayerTile();
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
    private boolean neighboursAround(int x, int y, Players player){
        if(upperNeighbour(x, y, player) || 
            bottomNeighbour(x, y, player) ||
                leftNeighbour(x, y, player) ||
                    rightNeighbour(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    //checks upperneighbour owner and handles to avoid out of bound Exception
    private boolean upperNeighbour(int x, int y, Players player) {
        if( x > 0 && upperNeighbourOwner(x, y, player)){
            return true;
        }else{
            return false;
        }
    }
    private boolean bottomNeighbour(int x, int y, Players player) {
        if(x<board.getRow()-1 && bottomNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    private boolean leftNeighbour(int x, int y, Players player) {
        if(y>0 && leftNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    private boolean rightNeighbour(int x, int y, Players player) {
        if(y<board.getCol()-1 && rightNeigbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    //checks if the tile you selected is not your own and not neutral
    private boolean upperNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x-1][y].getOwner().name != player.name && notNeutralTile(x-1, y)){
            return true;
        } else {
            return false;
        }
    }
    private boolean bottomNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x+1][y].getOwner().name != player.name && notNeutralTile(x+1, y)){
            return true;
        } else {
            return false;
        }
    }
    private boolean leftNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x][y-1].getOwner().name != player.name && notNeutralTile(x, y-1)){
            return true;
        } else {
            return false;
        }
    }
    private boolean rightNeigbourOwner(int x, int y, Players player){
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
    public void printPossibleMoves(int x, int y, Players player){
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