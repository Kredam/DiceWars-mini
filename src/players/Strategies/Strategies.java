package players.Strategies;
import gamearea.*;

import players.Players;

public class Strategies {
    private Tiles[][] tileBoard;
    private Board board;

    public Strategies(int players){
        board = new Board(players);
        tileBoard = board.getBoard();
    }
    public Board getBoard() {
        return board;
    }
    public Tiles[][] getTileBoard() {
        return tileBoard;
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
    protected boolean neighboursAround(int x, int y, Players player){
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
    protected boolean upperNeighbour(int x, int y, Players player) {
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
     protected boolean bottomNeighbour(int x, int y, Players player) {
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
    protected boolean leftNeighbour(int x, int y, Players player) {
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
    protected boolean rightNeighbour(int x, int y, Players player) {
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
