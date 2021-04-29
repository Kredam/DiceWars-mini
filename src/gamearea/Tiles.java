package gamearea;

import players.Players;

public class Tiles{
    private Players owner;
    private int diceNumber;
    private boolean selectable;
    public int numberOfTiles = 0;

    /**
     * Tile konstruktora
     * @param owner csempre tulajdonosa
     */
    public Tiles(Players owner){
        this.owner = owner;
    }
    /**
     * Visszaadja hogy kiválasztható-e a mező
     * @return csempre kiválaszható igaz, ellenkező esetben hamis
     */
    public boolean isSelectable(){
        return selectable;
    }
    /**
     * Visszaadja a csempe tulajdonosát
     * @return csempre tulajdonos
     */
    public Players getOwner() {
        return owner;
    }
    /**
     * Beállítja a csempe tulajdonosát, a támadásnál használjuk, akkor ha elfoglal valaim egy meőt
     * @param owner
     */
    public void setOwner(Players owner) {
        this.owner = owner;
    }
    /**
     * Visszadja a csempének a kockaszámát
     * @return kockaszám
     */
    public int getDiceNumber() {
        return diceNumber;
    }
    /**
     * Beállítja hogy a csempe(Tile) hány dobókockával rendelkezik<br/>
     * , hogy hozzá rendel a csempéhez a boolean értéket, ami checkolja hogy eggyes-e
     * , mivel hogy akkor nem támadhatunk vele
     * @param diceNumber Mennyiség amit a csempére állítsunk
     */
    public void setDiceNumber(int diceNumber) {
        if(diceNumber > 1){
            selectable = true;
        } else {
            selectable = false;
        }
        this.diceNumber = diceNumber;
    }
}
