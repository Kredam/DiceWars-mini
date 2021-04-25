package gamearea;
import java.util.*;

import gamecontrol.*;
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
    
    public Board(){
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
    
    public int randomCol(){
        return (int) Math.floor(Math.random()*this.col-0)+0;
    }
    public int randomRow(){
        return (int) Math.floor(Math.random()*this.row-0)+0;
    }
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
                    System.out.print(Console.BLACK + board[i][j].getDice_num() + Console.RESET + " | ");
                }
                if(board[i][j].getOwner() == p1){
                    System.out.print(Console.RED + board[i][j].getDice_num() + Console.RESET + " | ");
                } 
                if(board[i][j].getOwner() == p2) {
                    System.out.print(Console.GREEN + board[i][j].getDice_num() + Console.RESET + " | ");
                }
                if(board[i][j].getOwner() == p3) {
                    System.out.print(Console.BLUE + board[i][j].getDice_num() + Console.RESET + " | ");
                }
                if(board[i][j].getOwner() == p4) {
                    System.out.print(Console.YELLOW + board[i][j].getDice_num() + Console.RESET + " | ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(Console.PURPLE_BACKGROUND + " " + Console.RESET + " = Rows");
        System.out.println(Console.CYAN_BACKGROUND + " " + Console.RESET + " = Columns");
    }

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
    //create board based on player input
    private void setUpPlayersOnBoard(){
        if(players == 2) {
            ini2PlayertTable();
        }
        if(players == 3){
            ini3PlayertTable();
        }
        if(players == 4){
            ini4PlayertTable();
        }
    }
    private void ini2PlayertTable(){
        while(true){
            randomRow = randomRow();
            randomCol = randomCol();
            fillBoardWithPlayersTiles(p1, ownabletiles, randomRow, randomCol);
            fillBoardWithPlayersTiles(p2, ownabletiles, randomRow, randomCol);
            if(p1.getPlayerTile()+p2.getPlayerTile() == numberOfTiles){
                break;
            }
        }
        fillNullPlaces();
        generateDiceForPlayer(p1);
        generateDiceForPlayer(p2);
    }
    private void ini3PlayertTable(){
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
    private void ini4PlayertTable(){
        while(true){
            randomRow = randomRow();
            fillBoardWithPlayersTiles(p1, ownabletiles, randomRow, randomCol());
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
    private void fillBoardWithPlayersTiles(Players player, int ownabletiles, int randomRow, int randomCol){
        if(player.getPlayerTile() < ownabletiles && board[randomRow][randomCol] == null){
            board[randomRow][randomCol] = new Tiles(randomRow, randomCol, player);
            board[randomRow][randomCol].setDice_num(1);
            player.increasePlayerTile();
        }
    }
    private void fillNullPlaces(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == null){
                    board[i][j] = new Tiles(i, j, neutral);
                }
            }
        }
    }
    private void generateDiceForPlayer(Players player){
        player.setPlayerDices();
        System.out.println(player.getPlayerDices());
        int range = 0;
        int dices = 8;
        int iterate = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner().name == player.name){
                    if(dices > player.getPlayerDices()){
                        dices=player.getPlayerDices();
                    }
                    if(iterate < player.getPlayerTile()){
                        range = (int) (Math.random()*dices-1)+1;
                        board[i][j].setDice_num(range + board[i][j].getDice_num());
                        player.decreasePlayerDices(range);
                    }
                    if(iterate == player.getPlayerTile()){
                        board[i][j].setDice_num(player.getPlayerDices()+board[i][j].getDice_num());
                    }
                    iterate++;
                }
            }
        }
    }

}
