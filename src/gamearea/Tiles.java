package gamearea;

import players.Players;

public class Tiles{
    private int x;
    private int y;
    private Players owner;
    private int diceNumber;
    private boolean selectable;
    private int NumberOfSelectableTiles;
    public int numberOfTiles = 0;
    
    public Tiles(int x, int y, Players owner, int numberOfTiles){
        this.NumberOfSelectableTiles = 0;
        this.x = x;
        this.y = y;
        this.owner = owner;
        this.numberOfTiles = numberOfTiles;
    }

    public Tiles(int x, int y, Players owner){
        this.NumberOfSelectableTiles = 0;
        this.x = x;
        this.y = y;
        this.owner = owner;
        numberOfTiles++;
    }

    public boolean isSelectable(){
        return selectable;
    }
    public void setSelectable(int diceNumber){
        if(diceNumber > 1){
            selectable = true;
            NumberOfSelectableTiles++;
        } else {
            selectable = false;
        }
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Players getOwner() {
        return owner;
    }
    
    public void setOwner(Players owner) {
        this.owner = owner;
    }
    public int getDiceNumber() {
        return diceNumber;
    }
    public int getNumberOfSelectableTiles(){
        return NumberOfSelectableTiles;
    }
    /**
     * Set the dice number for this tile<br/>
     * and set the selectable boolean varibale to true if diceNumber bigger than 1, else false
     * @param diceNumber
     */
    public void setDiceNumber(int diceNumber) {
        setSelectable(diceNumber);
        this.diceNumber = diceNumber;
    }
}
