package gamearea;
import java.util.*;
import game.*;

public class Board {
    Random random = new Random();
    public Tiles[][] board;
    public int players;
    public int numberOfTiles;
    public int randomRow, randomCol;
    public int row,col;
    public Player p1,p2,p3,p4, neutral;
    

    public Board(int players){
        this.players = players;
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
        initializePlayers();
        createBoard();
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

    public Tiles[][] getBoard() {
        return board;
    }

    //create board based on player input
    public void createBoard(){
        board = new Tiles[row][col];
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

    public void ini2PlayertTable(){
        while(true){
            randomRow = generateRandomPosition(row);
            randomCol = generateRandomPosition(col);
            if(p1.getPlayerTile() < 5 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p1);
            }
            if(p2.getPlayerTile() <5 && board[randomRow][randomCol] == null){
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
    public void ini3PlayertTable(){
        while(true){
            randomRow = generateRandomPosition(row);
            randomCol = generateRandomPosition(col);
            if(p1.getPlayerTile() <9 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p1);
            }
            if(p2.getPlayerTile() <9 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p2);
            }
            if(p3.getPlayerTile() <9 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p3);
            }
            if(p1.getPlayerTile()+p2.getPlayerTile()+p3.getPlayerTile() == numberOfTiles){
                break;
            }
        }
        fillNullPlaces();

        
    }
    public void ini4PlayertTable(){
        while(true){
            randomRow = generateRandomPosition(row);
            randomCol = generateRandomPosition(col);
            if(p1.getPlayerTile() <10 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p1);
            }
            if(p2.getPlayerTile() <10 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p2);
            }
            if(p3.getPlayerTile() <10 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p3);
            }
            if(p4.getPlayerTile() <10 && board[randomRow][randomCol] == null){
                fillBoardWithPlayersTiles(p4);
            }
            if(p1.getPlayerTile()+p2.getPlayerTile()+p3.getPlayerTile()+p4.getPlayerTile() == numberOfTiles){
                break;
            }
        }
        fillNullPlaces();

    }
    public void fillBoardWithPlayersTiles(Player player){
        board[randomRow][randomCol] = new Tiles(randomRow, randomCol, player);
        board[randomRow][randomCol].setDice_num(1);
        player.increasePlayerTile();
    }
    public void fillNullPlaces(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == null){
                    board[i][j] = new Tiles(i, j, neutral);
                }
            }
        }
    }
    public int generateRandomPosition(int upperRange){
        return (int) Math.floor(Math.random() * upperRange-1) + 1; 
    }
    
    public void generateDiceForPlayer(Player player){
        player.setPlayerDices();
        int range = 0;
        int dices = 8;
        int iterate = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner().name == player.name){
                    if(dices>player.getPlayerDices()){
                        dices=player.getPlayerDices();
                    }
                    if(player.getPlayerDices() >= 0 && iterate < player.getPlayerTile()){
                        range = (int) (Math.random()*dices-1)+1;
                        board[i][j].setDice_num(range + board[i][j].getDice_num());
                        player.decreasePlayerDices(range);
                    }
                    if(iterate == player.getPlayerTile()){
                        if(player.getPlayerDices() < 0){
                            dices = range - Math.abs(player.getPlayerDices());
                        }
                        board[i][j].setDice_num(dices+board[i][j].getDice_num());
                    }
                    iterate++;
                }
            }
        }
    }
    public void printBoard() {
        for (int i = 0; i < row; i++) {
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
            System.out.println("");
        }
    }
}
