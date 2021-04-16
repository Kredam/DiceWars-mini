package GameArea;

import Game.*;

public class Board {
    public static final String RED = "\033[0;31m";     // RED
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String BLACK = "\u001B[30m";
    public Tiles[][] board;
    public int player1Tiles, player2Tiles, player3Tiles, player4Tiles;
    public int players;
    public int numberOfTiles;
    public int randomRow, randomCol;
    public int row,col;
    public Player p1,p2,p3,p4, neutral;
    
    public Board(int players){
        this.players = players;
        player1Tiles = 0;
        p1 = new Player("p1");
        player2Tiles = 0;
        p2 = new Player("p2");
        player3Tiles = 0;
        p3 = new Player("p3");
        player4Tiles = 0;
        p4 = new Player("p4");
        neutral = new Player("neutral");

        if(players == 2){
            numberOfTiles = 10;
            row = 4;
            col = 4;
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
        createBoard();
    }

    public Tiles[][] getBoard() {
        return board;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
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

    //sets up board and its field
    //randomly places the values if it isnt occupied and 
    public void ini2PlayertTable(){
        while(true){
                randomRow = generateRandomInt(row);
                randomCol = generateRandomInt(col);
                if(player1Tiles <5 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p1, player1Tiles);
                    player1Tiles++;
                }
                if(player2Tiles <5 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p2, player2Tiles);
                    player2Tiles++;
                }
                if(player1Tiles+player2Tiles == numberOfTiles){
                    break;
                }
        }
        fillNullPlaces();
    }

    public void ini3PlayertTable(){
        while(true){
                randomRow = generateRandomInt(row);
                randomCol = generateRandomInt(col);
                if(player1Tiles <9 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p1, player1Tiles);
                    player1Tiles++;
                }
                if(player2Tiles <9 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p2, player2Tiles);
                    player2Tiles++;
                }
                if(player3Tiles <9 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p3, player3Tiles);
                    player3Tiles++;
                }
                if(player1Tiles+player2Tiles+player3Tiles == numberOfTiles){
                    break;
                }
        }
        fillNullPlaces();
    }

    public void ini4PlayertTable(){
        while(true){
                randomRow = generateRandomInt(row);
                randomCol = generateRandomInt(col);
                if(player1Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p1, player1Tiles);
                    player1Tiles++;
                }
                if(player2Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p2, player2Tiles);
                    player2Tiles++;
                }
                if(player3Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p3, player3Tiles);
                    player3Tiles++;
                }
                if(player4Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p4, player4Tiles);
                    player4Tiles++;
                }
                if(player1Tiles+player2Tiles+player3Tiles+player4Tiles == numberOfTiles){
                    break;
                }
        }
        fillNullPlaces();
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

    //generate a random number between 1 and the value that is giben by parameter
    public static int generateRandomInt(int upperRange){
        return (int) Math.floor(Math.random() * upperRange-1) + 1; 
    }

    //prints the board
    public void printBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner() == neutral){
                    System.out.print(BLACK + board[i][j].getDice_num() + RESET + " | ");
                }
                if(board[i][j].getOwner() == p1){
                    System.out.print(RED + board[i][j].getDice_num() + RESET + " | ");
                } 
                if(board[i][j].getOwner() == p2) {
                    System.out.print(GREEN + board[i][j].getDice_num() + RESET + " | ");
                }
                if(board[i][j].getOwner() == p3) {
                    System.out.print(BLUE + board[i][j].getDice_num() + RESET + " | ");
                }
                if(board[i][j].getOwner() == p4) {
                    System.out.print(YELLOW + board[i][j].getDice_num() + RESET + " | ");
                }
            }
            System.out.println("");
        }
    }
    
}
