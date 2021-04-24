package gamearea;
import java.util.*;
import game.*;

public class Board {
    Random random = new Random();
    public Tiles[][] board;
    private int players;
    private int numberOfTiles;
    private int randomRow, randomCol;
    public int row,col;
    public Player p1,p2,p3,p4, neutral;
    
    public Board(int players){
        this.players = players;
        initializeBoard(players);
        initializePlayers();
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
    
    public int generateRandomColPosition(){
        return (int) Math.floor(Math.random()*this.col-0)+0;
    }
    public int generateRandomRowPosition(){
        return (int) Math.floor(Math.random()*this.row-0)+0;
    }
    public void printBoard(){
        clearScreen();
        System.out.print("   |");
        for (int i = 0; i < col; i++) {
            System.out.print(ConsoleColor.BLACK + ConsoleColor.CYAN_BACKGROUND+i+". "+ ConsoleColor.RESET +"|");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(ConsoleColor.BLACK + ConsoleColor.PURPLE_BACKGROUND+i+". " + ConsoleColor.RESET +"| ");
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner() == neutral){
                    System.out.print(ConsoleColor.BLACK + board[i][j].getDice_num() + ConsoleColor.RESET + " | ");
                }
                if(board[i][j].getOwner() == p1){
                    System.out.print(ConsoleColor.RED + board[i][j].getDice_num() + ConsoleColor.RESET + " | ");
                } 
                if(board[i][j].getOwner() == p2) {
                    System.out.print(ConsoleColor.GREEN + board[i][j].getDice_num() + ConsoleColor.RESET + " | ");
                }
                if(board[i][j].getOwner() == p3) {
                    System.out.print(ConsoleColor.BLUE + board[i][j].getDice_num() + ConsoleColor.RESET + " | ");
                }
                if(board[i][j].getOwner() == p4) {
                    System.out.print(ConsoleColor.YELLOW + board[i][j].getDice_num() + ConsoleColor.RESET + " | ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(ConsoleColor.PURPLE_BACKGROUND + " " + ConsoleColor.RESET + " = Rows");
        System.out.println(ConsoleColor.CYAN_BACKGROUND + " " + ConsoleColor.RESET + " = Columns");
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
        board = new Tiles[row][col];
    }
    private void initializePlayers(){
        if(players == 2){
            p1 = new Player("p1", numberOfTiles);
            p2 = new Player("p2", numberOfTiles);
            neutral = new Player("neutral");
        }
        if(players == 3){
            p1 = new Player("p1", numberOfTiles);
            p2 = new Player("p2", numberOfTiles);
            p3 = new Player("p3", numberOfTiles);
            neutral = new Player("neutral");
        }
        if(players == 4){
            p1 = new Player("p1", numberOfTiles);
            p2 = new Player("p2", numberOfTiles);
            p3 = new Player("p3", numberOfTiles);
            p4 = new Player("p4", numberOfTiles);
            neutral = new Player("neutral");
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
            randomRow = generateRandomRowPosition();
            randomCol = generateRandomColPosition();
            if(p1.getPlayerTile() < (numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p1);
            }
            if(p2.getPlayerTile() < (numberOfTiles/players)&& board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p2);
            }
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
            randomRow = generateRandomRowPosition();
            randomCol = generateRandomColPosition();
            if(p1.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p1);
            }
            if(p2.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p2);
            }
            if(p3.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p3);
            }
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
            randomRow = generateRandomRowPosition();
            randomCol = generateRandomColPosition();
            if(p1.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p1);
            }
            if(p2.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p2);
            }
            if(p3.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p3);
            }
            if(p4.getPlayerTile() <(numberOfTiles/players) && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p4);
            }
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
    private void fillBoardWithPlayersTiles(Player player){
        board[randomRow][randomCol] = new Tiles(randomRow, randomCol, player);
        board[randomRow][randomCol].setDice_num(1);
        player.increasePlayerTile();
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
    private void generateDiceForPlayer(Player player){
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
    private static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

}
