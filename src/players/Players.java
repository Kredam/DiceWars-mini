package players;


public abstract class Players {
    public static int numberOfPlayers = 0;
    public String name;
    private int playerTile;
    private int playerDices;
    private int boardNumberOfTiles;

    public Players(String name){
        this.name=name;
    }
    public Players(String name, int boardNumberOfTiles){
        this.name = name;
        this.boardNumberOfTiles = boardNumberOfTiles;
        numberOfPlayers++;
    }
    
    public int getPlayerDices() {
        return playerDices;
    }
    public void setPlayerDices() {
        playerDices=(boardNumberOfTiles/numberOfPlayers)*2;
    }
    public void decreasePlayerDices(int range){
        playerDices-=range;
    }
    public void setPlayerTile(int playerTile) {
        this.playerTile = playerTile;
    }
    public void increasePlayerTile(){
        playerTile++;
    }
    public void decreasePlayerTile(){
        playerTile--;
    }
    public int getPlayerTile() {
        return playerTile;
    }

    public abstract void combat();

}
