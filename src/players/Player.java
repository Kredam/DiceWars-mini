package players;

public class Player extends Players{

    /**
     * Ez a játékos konstruktöre ami a játékosok gyerekosztálya
     * Ez a konstruktor beállítja a paraméterül kapott nevet, és csempék számát ami kiosztható
     */
    public Player(String name, int boardNumberOfTiles){
        super(name, boardNumberOfTiles);
    }

   
}
