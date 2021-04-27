package gameplay;

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
    public Board getBoard() {
        return board;
    }
    public Tiles[][] getTileBoard() {
        return tileBoard;
    }

    public void enemyTurn(Players player){
        int choice;
        for (int i = 0; i < board.getRow() ;i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(neighboursAround(i, j, tileBoard[i][j].getOwner()) && 
                tileBoard[i][j].getOwner().name.equals(player.name) &&
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

    /**
     * this method is responsible for the players turn
     */
    public void playerTurn(){
            board.printBoard();
            int OwnPosX = playerInput.posX(board.getRow());
            int OwnPosY = playerInput.posY(board.getRow());
            if(tileBoard[OwnPosX][OwnPosY].isSelectable() && 
                tileBoard[OwnPosX][OwnPosY].getOwner().name.equals("p1") &&
                    neighboursAround(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                        do{
                            printPossibleMoves(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner());
                            try{
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
                                }else if(choice == 5){
                                    break;
                                }else{
                                    System.out.println(Console.WHITE_BOLD+"You've choosen an attack option that is invalid for this tile, please choose again!"+Console.RESET);
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

    /**
     * Shares the dice based on the formula which is =  palyerCurrentTiles/2
     * @param player which player dice to share at the end of the turn
     */
    public void giveDicesAtTheEndOfYourTurn(Players player){
        int amountOfDicesToShare=player.getPlayerTile()/2;
        int iterate = 1;
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(tileBoard[i][j].getOwner().name.equals(player.name)){
                    int range = (int) (Math.random()*amountOfDicesToShare-1)+1;
                    System.out.println(range);
                    if(iterate < player.getPlayerTile() && amountOfDicesToShare > 0 && tileBoard[i][j].getDiceNumber()+range <=8){
                        tileBoard[i][j].setDiceNumber(tileBoard[i][j].getDiceNumber()+range);
                        amountOfDicesToShare-=range;
                        System.out.println(amountOfDicesToShare + "= amount dice to share");
                    }
                    if(iterate==player.getPlayerTile() && amountOfDicesToShare > 0){
                        tileBoard[i][j].setDiceNumber(tileBoard[i][j].getDiceNumber()+amountOfDicesToShare);
                    }
                    iterate++;
                }
            }
        }
        board.printBoard();
    }

    /**
     * This method checks player tiles<br/>
     * if you are out of tiles it ends the game
     * @param player user player
     * @return true if you have tiles, false you are out of tiles
     */
    public boolean playerHasTiles(){
        if(board.p1.getPlayerTile() != 0){
            return true;
        }else{
            System.out.println("You have been defeated");
            return false;
        }
    }
    /**
     * Attacker attack with the tile on attacking position, defender defens with tile on the defend poisiton, and<br/>
     * handles the outcome of the attack
     * @param attackX which row to attack from
     * @param attackY which col to attack rom
     * @param defendX which row to attack
     * @param defendY which col to attack
     */    
    public void attack(int attackX, int attackY, int defendX, int defendY){
        int attackValue = rollDiceAttack(tileBoard[attackX][attackY].getDiceNumber());
        int defendValue = rollDiceAttack(tileBoard[defendX][defendY].getDiceNumber());
        if(attackValue > defendValue){
            changePositionOnWin(attackX, attackY, defendX, defendY);
        }
        if(attackValue < defendValue || attackValue == defendValue){
            changePositionOnLoss(attackX, attackY);
        }
        System.out.println("Attack roll: " + attackValue + ", Defend roll: " + defendValue);
        
    }

    /**
     * Changes the tiles ownership, dicenumber on win
     * @param attackX attacking row position
     * @param attackY attacking col position
     * @param defendX defending row position
     * @param defendY defending col position
     */
    public void changePositionOnWin(int attackX, int attackY, int defendX, int defendY){
        tileBoard[attackX][attackY].getOwner().increasePlayerTile();
        tileBoard[defendX][defendY].getOwner().decreasePlayerTile();
        tileBoard[defendX][defendY].setOwner(tileBoard[attackX][attackY].getOwner());
        tileBoard[defendX][defendY].setDiceNumber(tileBoard[attackX][attackY].getDiceNumber()-1);
        tileBoard[attackX][attackY].setDiceNumber(1);
    }
    /**
     * On loss changes the current tile value to 0
     * @param attackX attacking row position
     * @param attackY attacking col position
     */
    public void changePositionOnLoss(int attackX, int attackY){
        tileBoard[attackX][attackY].setDiceNumber(1);
    }
    /**
     * Roll the dice by the amount the tile has
     * @param dices the tile's diceNumber
     * @return all the dice rolls value
     */
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


    /**
     * Check if there is at least 1 neigbour around the tile
     * @param x row coordinate
     * @param y col coordinate
     * @param player which player to check on the surronding tiles
     * @return
     */
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
    /**
     * Determines if there is a upper neighbour
     * @param x row coordinate
     * @param y col coordinate
     * @param player which player ownership to check
     * @return true if there is a upper neighbour, else false
     */
    private boolean upperNeighbour(int x, int y, Players player) {
        if( x > 0 && upperNeighbourOwner(x, y, player)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Determines if there is a bottom neighbour
     * @param x row coordinate
     * @param y col coordinate
     * @param player which player ownership to check
     * @return true if there is a bottom neighbour, else false
     */
     private boolean bottomNeighbour(int x, int y, Players player) {
        if(x<board.getRow()-1 && bottomNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Determines if there is a left neighbour
     * @param x row coordinate
     * @param y col coordinate
     * @param player which player ownership to check
     * @return true if there is a left neighbour, else false
     */
    private boolean leftNeighbour(int x, int y, Players player) {
        if(y>0 && leftNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Determines if there is a right neighbour
     * @param x row coordinate to check on
     * @param y col coordinate to check on
     * @param player which player ownership to check
     * @return true if there is a right neighbour, else false
     */
    private boolean rightNeighbour(int x, int y, Players player) {
        if(y<board.getCol()-1 && rightNeigbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Determine the upper neighbour ownership
     * @param x row coordinate check on
     * @param y col coordinate check on
     * @param player which player to check on the upper tile
     * @return true if not your tile and not neutral, else false
     */
    private boolean upperNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x-1][y].getOwner().name != player.name && notNeutralTile(x-1, y)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Determine the bottom neighbour ownership
     * @param x row coordinate check on
     * @param y col coordinate check on
     * @param player which player to check on the bottom tile
     * @return true if not your tile and not neutral, else false
     */
    private boolean bottomNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x+1][y].getOwner().name != player.name && notNeutralTile(x+1, y)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Determine the bottom neighbour ownership
     * @param x row coordinate check on
     * @param y col coordinate check on
     * @param player which player to check on the left tile
     * @return true if not your tile and not neutral, else false
     */
    private boolean leftNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x][y-1].getOwner().name != player.name && notNeutralTile(x, y-1)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Determine the right neighbour ownership
     * @param x row coordinate check on
     * @param y col coordinate check on
     * @param player which player to check on the right tile
     * @return true if not your tile and not neutral, else false
     */
    private boolean rightNeigbourOwner(int x, int y, Players player){
        if(tileBoard[x][y+1].getOwner().name != player.name && notNeutralTile(x, y+1)){
            return true;
        } else {
            return false;
        }
    }    
    /**
     * Determine the neutrality of the tile
     * @param x row coordinate check on
     * @param y col coordinate check on
     * @return true if its not neutral, false if it is neutral
     */
    public boolean notNeutralTile(int x, int y){
        if(tileBoard[x][y].getOwner().name != "neutral"){
            return true;
        } else {
            return false;
        }
    }
    /**
     * prints possible moves based on the tiles neighbours
     * @param x row coordinates check on
     * @param y col coordinates check on
     * @param player which player's ownership to check
     */
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
        System.out.println("Press 5 to abort attack");
    }
}