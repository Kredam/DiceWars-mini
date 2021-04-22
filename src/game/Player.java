package game;


public class Player {
    public static int numberOfPlayers = 1;
    public String name;
    private int playerTile;
    private int playerDices;
    private int boardNumberOfTiles;

    public Player(String name){
        this.name=name;
    }
    public Player(String name, int boardNumberOfTiles){
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
    public int getPlayerTile() {
        return playerTile;
    }
}