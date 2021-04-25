package gamearea;

import players.Players;

public class Tiles{
    public int x;
    public int y;
    public Players owner;
    public int dice_num;
    public boolean selectable;
    public int numberOfTiles = 0;
    
    public Tiles(int x, int y, Players owner, int numberOfTiles){
        this.x = x;
        this.y = y;
        this.owner = owner;
        this.numberOfTiles = numberOfTiles;
    }

    public Tiles(int x, int y, Players owner){
        this.x = x;
        this.y = y;
        this.owner = owner;
        dice_num = 0;
        numberOfTiles++;
    }

    public boolean isSelectable(){
        return selectable;
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
    public int getDice_num() {
        return dice_num;
    }
    public void setDice_num(int dice_num) {
        if(dice_num > 1){
            selectable = true;
        } else {
            selectable = false;
        }
        this.dice_num = dice_num;
    }
}
