package gamearea;
import java.util.*;

import gameplay.*;
import players.*;

public class Board {
    Random random = new Random();
    private Tiles[][] board;
    private int numberOfPlayers;
    private int numberOfTiles;
    private int ownabletiles;
    private int row,col;
    public Players p1,p2,p3,p4, neutral;
    int randomRow,randomCol;
    
    /**
     * Board osztály konstruktora
     * @param numberOfPlayers Hány játékos lesz a pályán
     */
    public Board(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        initializeBoard(numberOfPlayers);
        initializeEnemies();
        setUpPlayersOnBoard();
    }
   
    /**
     * Visszadja hogy hány sorból áll a tábla
     * @return
     */
    public int getRow() {
        return row;
    }
    /**
     * Visszaadja hogy hány oszlopból áll 
     * @return
     */
    public int getCol() {
        return col;
    }
    /**
     * Visszaadja hogy hány játkos van a pályán
     * @return
     */
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }
    /**
     * Visszaadja a 2d Tile array-t vagyis magát a pályát
     * @return Pályát(2d Tile array)
     */
    public Tiles[][] getBoard() {
        return board;
    }
    /**
     * Visszadja az elfoglalható összes csempék számát
     * @return
     */
    public int getNumberOfTiles(){
        return numberOfTiles;
    }
    
    /**
     * Legenerál egy oszlop számot az intervallumon belül
     * @return Random oszlop pozíció
     */
    public int randomCol(){
        return (int) Math.floor(Math.random()*this.col-0)+0;
    }
    /**
     * Legenrál egy sor számot az intervallumon belül 
     * @return Random sor pozícó
     */
    public int randomRow(){
        return (int) Math.floor(Math.random()*this.row-0)+0;
    }
    /**
     * Kiírja a konzolra a 2 dimenziós tömböt, vagyis a pályát<br/>
     * Kiszínezi a tömb elemeket a tulajdonosa alapján
     */
    public void printBoard(){
        System.out.print("   |");
        for (int i = 0; i < col; i++) {
            System.out.print(Console.BLACK + Console.CYAN_BACKGROUND+i+". "+ Console.RESET +"|");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(Console.BLACK + Console.PURPLE_BACKGROUND+i+". " + Console.RESET +"| ");
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner() == neutral){
                    System.out.print(Console.BLACK + board[i][j].getDiceNumber() + Console.RESET + " | ");
                }
                if(board[i][j].getOwner() == p1){
                    System.out.print(Console.RED + board[i][j].getDiceNumber() + Console.RESET + " | ");
                } 
                if(board[i][j].getOwner() == p2) {
                    System.out.print(Console.GREEN + board[i][j].getDiceNumber() + Console.RESET + " | ");
                }
                if(board[i][j].getOwner() == p3) {
                    System.out.print(Console.BLUE + board[i][j].getDiceNumber() + Console.RESET + " | ");
                }
                if(board[i][j].getOwner() == p4) {
                    System.out.print(Console.YELLOW + board[i][j].getDiceNumber() + Console.RESET + " | ");
                }
            }
            System.out.println();
        }
        printBoardPlayersColor();
        System.out.println(Console.PURPLE_BACKGROUND + " " + Console.RESET + " = Rows");
        System.out.println(Console.CYAN_BACKGROUND + " " + Console.RESET + " = Columns\n");
    }

    /**
     * Kiírja a játkosok milyen szinű
     */
    private void printBoardPlayersColor(){
        System.out.println(Console.RED+"Vour Tile's color"+Console.RESET);
        System.out.println(Console.GREEN+"2nd enemy color"+Console.RESET);
        if(p3 != null){
            System.out.println(Console.BLUE+"3rd enemy color"+Console.RESET);
        }
        if(p4 != null){
            System.out.println(Console.YELLOW+"4th enemy color"+Console.RESET);
        }
    }

    /**
     * Létrehozza a Tile 2 dimenziós tömböt attól föggően hogy hány játékos lesz a pályán, és<br/>
     * meghatározza a játékos szám alapján hogy hány soros és oszlopos lesz a pálya és hogy hány elfoglalható terület lesz<br/>
     * ,és kiszámolja hogy 1 játékos hány mezőt birtokolhat a játék elején
     * @param numberOfPlayers Hány játékos játszik
     */
    private void initializeBoard(int numberOfPlayers){
        if(numberOfPlayers == 2){
            numberOfTiles = 10;
            row = 4;
            col = 3;
        }
        if(numberOfPlayers == 3){
            numberOfTiles = 27;
            row = 6;
            col = 6;
        }
        if(numberOfPlayers == 4){
            numberOfTiles = 40;
            row = 7;
            col = 7;
        }
        ownabletiles=numberOfTiles/numberOfPlayers;
        board = new Tiles[row][col];
    }
    /**
     * Példányosítja a az enemy osztályt,létrehozza az ellenfeleket attól függően hogy hány játákos lesz a pályán
     */
    private void initializeEnemies(){
        p1 = new Player("p1", numberOfTiles);
        if(numberOfPlayers == 2){
            p2 = new Enemy("p2", numberOfTiles);
            neutral = new Neutral("neutral");
        }
        if(numberOfPlayers == 3){
            p2 = new Enemy("p2", numberOfTiles);
            p3 = new Enemy("p3", numberOfTiles);
            neutral = new Neutral("neutral");
        }
        if(numberOfPlayers == 4){
            p2 = new Enemy("p2", numberOfTiles);
            p3 = new Enemy("p3", numberOfTiles);
            p4 = new Enemy("p4", numberOfTiles);
            neutral = new Neutral("neutral");
        }
    }
    /**
     * Meghívja valamelyik setUpPlayerOnBoard metódust attól függően hogy hány játékos lesz a pályán
     */
    private void setUpPlayersOnBoard(){
        if(numberOfPlayers == 2) {
            setUp2PlayersOnBoard();
        }
        if(numberOfPlayers == 3){
            setUp3PlayersOnBoard();
        }
        if(numberOfPlayers == 4){
            setUp4PlayersOnBoard();
        }
    }
    /**
     * Feltölti a táblát a játékosokkal és annak dobókockáival, és feltöti a kimaradt semleges mezőket 3 játkos számára
     */
    private void setUp2PlayersOnBoard(){
        while(true){
            fillBoardWithPlayersTiles(p1, ownabletiles, randomRow(), randomCol());
            fillBoardWithPlayersTiles(p2, ownabletiles, randomRow(), randomCol());
            if(p1.getPlayerTile()+p2.getPlayerTile() == numberOfTiles){
                break;
            }
        }
        fillNullPlaces();
        generateDiceForPlayer(p1);
        generateDiceForPlayer(p2);
    }
    /**
     * Feltölti a táblát a játékosokkal és annak dobókockáival, és feltöti a kimaradt semleges mezőket 3 játkos számára
     */
    private void setUp3PlayersOnBoard(){
        while(true){
            fillBoardWithPlayersTiles(p1, ownabletiles, randomRow(), randomCol());
            fillBoardWithPlayersTiles(p2, ownabletiles, randomRow(), randomCol());
            fillBoardWithPlayersTiles(p3, ownabletiles, randomRow(), randomCol());
            if(p1.getPlayerTile()+p2.getPlayerTile()+p3.getPlayerTile() == numberOfTiles){
                break;
            }
        }
        fillNullPlaces();
        generateDiceForPlayer(p1);
        generateDiceForPlayer(p2);
        generateDiceForPlayer(p3);
        
    }
    /**
     * Feltölti a táblát a játékosokkal és annak dobókockáival, és feltöti a kimaradt semleges mezőket 4 játkos számára
     */
    private void setUp4PlayersOnBoard(){
        while(true){
            fillBoardWithPlayersTiles(p1, ownabletiles, randomRow(), randomCol());
            fillBoardWithPlayersTiles(p2, ownabletiles, randomRow(), randomCol());
            fillBoardWithPlayersTiles(p3, ownabletiles, randomRow(), randomCol());
            fillBoardWithPlayersTiles(p4, ownabletiles, randomRow(), randomCol());
            if(p1.getPlayerTile()+p2.getPlayerTile()+p3.getPlayerTile()+p4.getPlayerTile() == numberOfTiles){
                break;
            }
        }
        fillNullPlaces();
        generateDiceForPlayer(p1);
        generateDiceForPlayer(p2);
        generateDiceForPlayer(p3);
        generateDiceForPlayer(p4);
    }
    /**
     * Feltölti a táblát a megadott játékos Tile-al, random poziciókon
     * @param player Melyik játékos 
     * @param ownabletiles Hány feltölthető hely van
     * @param randomRow Random sor pozíció
     * @param randomCol Random oszlop pozíció
     */
    private void fillBoardWithPlayersTiles(Players player, int ownabletiles, int randomRow, int randomCol){
        if(board[randomRow][randomCol] == null && player.getPlayerTile() < ownabletiles){
            board[randomRow][randomCol] = new Tiles(player);
            board[randomRow][randomCol].setDiceNumber(1);
            player.increasePlayerTile();
        }
    }
    /**
     * Feltölti a kimaradt Tile-okat semleges Tile-al, elkerüli a NullPointerException-t
     */
    private void fillNullPlaces(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == null){
                    board[i][j] = new NeutralTile(neutral);
                }
            }
        }
    }
    /**
     * Legenerálja a játékosoknak a megfelelő mennyiségű dobókockákat
     * @param player Melyik játékosnak generáljuk le dobókockákat
     */
    private void generateDiceForPlayer(Players player){
        player.setPlayerDices();
        int range;
        int dices = 8;
        int iterate = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getClass() != NeutralTile.class && board[i][j].getOwner().name == player.name){
                    if(dices > player.getPlayerDices()){
                        dices=player.getPlayerDices();
                    }
                    if(iterate < player.getPlayerTile()){
                        range = (int) (Math.random()*dices-1)+1;
                        board[i][j].setDiceNumber(range + board[i][j].getDiceNumber());
                        player.decreasePlayerDices(range);
                    }
                    if(iterate == player.getPlayerTile()){
                        board[i][j].setDiceNumber(player.getPlayerDices()+board[i][j].getDiceNumber());
                    }
                    iterate++;
                }
            }
        }
    }
}
