package gamearea;
import java.util.*;

import gameplay.*;
import players.Enemy;
import players.Neutral;
import players.Player;
import players.Players;

public class Board {
    Random random = new Random();
    private Tiles[][] board;
    private int players;
    private int numberOfTiles;
    private int ownabletiles;
    private int row,col;
    public Players p1,p2,p3,p4, neutral;
    int randomRow,randomCol;
    
    
    public Board(int players){
        this.players = players;
        initializeBoard(players);
        initializeEnemies();
        setUpPlayersOnBoard();
    }
   
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getPlayers(){
        return players;
    }
    public Tiles[][] getBoard() {
        return board;
    }
    
    /**
     * This method generates a random number based on the column 
     * @return Random column position
     */
    public int randomCol(){
        return (int) Math.floor(Math.random()*this.col-0)+0;
    }
    /**
     * This method generates a random number based on the column 
     * @return Random row position
     */
    public int randomRow(){
        return (int) Math.floor(Math.random()*this.row-0)+0;
    }
    /**
     * This method prints the 2d Tiles array to the console<br/>
     * and colors the tiles based on the ownership of the tile
     */
    public void printBoard(){
        Console.clearScreen();
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
        System.out.println();
        System.out.println(Console.PURPLE_BACKGROUND + " " + Console.RESET + " = Rows");
        System.out.println(Console.CYAN_BACKGROUND + " " + Console.RESET + " = Columns");
        System.out.println(p1.getPlayerTile());
    }

    /**
     * initialize the board based on the user input<br/>
     * and then creates new object from the Tiles class, and initialize it with the at attributes
     * @param players the amount of players to initialize the board for
     */
    private void initializeBoard(int players){
        if(players == 2){
            numberOfTiles = 10;
            row = 4;
            col = 3;
        }
        if(players == 3){
            numberOfTiles = 27;
            row = 6;
            col = 6;
        }
        if(players == 4){
            numberOfTiles = 40;
            row = 7;
            col = 7;
        }
        ownabletiles=numberOfTiles/players;
        board = new Tiles[row][col];
    }
    /**
     * Initialize the enemies/creates new objects from Players class based on the user input, which is the number of players
     */
    private void initializeEnemies(){
        p1 = new Player("p1", numberOfTiles);
        if(players == 2){
            p2 = new Enemy("p2", numberOfTiles);
            neutral = new Neutral("neutral");
        }
        if(players == 3){
            p2 = new Enemy("p2", numberOfTiles);
            p3 = new Enemy("p3", numberOfTiles);
            neutral = new Neutral("neutral");
        }
        if(players == 4){
            p2 = new Enemy("p2", numberOfTiles);
            p3 = new Enemy("p3", numberOfTiles);
            p4 = new Enemy("p4", numberOfTiles);
            neutral = new Neutral("neutral");
        }
    }
    /**
     * Set up the board with players based on the user input, which is the number of players
     */
    private void setUpPlayersOnBoard(){
        if(players == 2) {
            setUp2PlayersOnBoard();
        }
        if(players == 3){
            setUp3PlayersOnBoard();
        }
        if(players == 4){
            setUp4PlayersOnBoard();
        }
    }
    /**
     * set up the board for 2 players
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
     * set up the board for 3 players
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
     * set up the board for 4 players
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
     * Fill the board with the player's tile
     * @param player which player's tile 
     * @param ownabletiles how many tiles you can fill the board with
     * @param randomRow which row to put it on(random becasue of different board generation)
     * @param randomCol which col to put it on(random becasue of different board generation)
     */
    private void fillBoardWithPlayersTiles(Players player, int ownabletiles, int randomRow, int randomCol){
        if(player.getPlayerTile() < ownabletiles && board[randomRow][randomCol] == null){
            board[randomRow][randomCol] = new Tiles(randomRow, randomCol, player);
            board[randomRow][randomCol].setDiceNumber(1);
            player.increasePlayerTile();
        }
    }
    /**
     * fills null places on the board, with neutral tiles to avoid NullPointerException
     */
    private void fillNullPlaces(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == null){
                    board[i][j] = new Tiles(i, j, neutral);
                }
            }
        }
    }
    /**
     * Generates the dices for the players
     * @param player which players dice to generate
     */
    private void generateDiceForPlayer(Players player){
        player.setPlayerDices();
        int range;
        int dices = 8;
        int iterate = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner().name == player.name){
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
