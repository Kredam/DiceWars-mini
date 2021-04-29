package players;

public class Enemy extends Players{
     /**
     * Ez a ellenfél konstruktöre ami a játékosok gyerekosztálya
     * Ez a konstruktor beállítja a paraméterül kapott nevet, és csempék számát ami kiosztható
     */
    public Enemy(String name, int boardNumberOfTiles){
        super(name, boardNumberOfTiles);
    }


}
