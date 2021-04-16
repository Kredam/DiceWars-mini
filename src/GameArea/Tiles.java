package GameArea;

import Game.Player;

public class Tiles {
    public int x;
    public int y;
    public Player owner;
    public int dice_num;
    public int numberOfTiles = 0;
    
    public Tiles(int x, int y, Player owner, int numberOfTiles){
        this.x = x;
        this.y = y;
        this.owner = owner;
        this.numberOfTiles = numberOfTiles;
    }

    public Tiles(int x, int y, Player owner){
        this.x = x;
        this.y = y;
        this.owner = owner;
        dice_num = 0;
        numberOfTiles++;
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
    public Player getOwner() {
        return owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public int getDice_num() {
        return dice_num;
    }
    public void setDice_num(int dice_num) {
        this.dice_num = dice_num;
    }
}
