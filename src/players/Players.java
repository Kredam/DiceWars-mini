package players;


public abstract class Players {
    public static int numberOfPlayers = 0;
    public String name;
    private int playerTile;
    private int playerDices;
    private int boardNumberOfTiles;

    /**
     * Players konstruktor, ami csak a nevet várja
     * , semleges játékosra 
     * @param name
     */
    public Players(String name){
        this.name=name;
    }
    /**
     * Ez a konstruktor beállítja a paraméterül kapott nevet, és csempék számát ami kiosztható
     * @param name Játékos neve
     * @param boardNumberOfTiles Összes csempe szám ami kiosztható
     */
    public Players(String name, int boardNumberOfTiles){
        this.name = name;
        this.boardNumberOfTiles = boardNumberOfTiles;
        numberOfPlayers++;
    }
    /**
     * Visszadja a játékos dobókockáit
     * @return dobókockák
     */
    public int getPlayerDices() {
        return playerDices;
    }
    /**
     * beállítja a játékos kioszható dobókockáit
     */
    public void setPlayerDices() {
        playerDices=(boardNumberOfTiles/numberOfPlayers)*2;
    }
    /**
     * Csökkenti a játékos dobókockáit paraméterben megadott mennyiséggel
     * @param range maradék dobókocka
     */
    public void decreasePlayerDices(int playerDiceGiven){
        playerDices-=playerDiceGiven;
    }
    /**
     * Növeli a játékos csempéinek(Tile)-nak számát 1 egységgel
     */
    public void increasePlayerTile(){
        playerTile++;
    }
    /**
    * Csökkneti a játékos csempéinek(Tile)-nak számát 1 egységgel
     */
    public void decreasePlayerTile(){
        playerTile--;
    }
    /**
     * Visszadja a játékos rendelkezésre álló csempéit
     * @return Játékos rendelkezésre álló csempéi
     */
    public int getPlayerTile() {
        return playerTile;
    }

}
