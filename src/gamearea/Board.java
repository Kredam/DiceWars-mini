package gamearea;
import java.util.*;
import game.*;
public class Board {

    Random random = new Random();
    public Tiles[][] board;
    public int player1Tiles, player2Tiles, player3Tiles, player4Tiles;
    public int player1Dices, player2Dices, player3Dices, player4Dices;
    public int players;
    public int numberOfTiles;
    public int randomRow, randomCol;
    public int row,col;
    public int diceNumber;
    public int dice_given;
    public int dices;
    public int iterate;
    public int range;
    public Player p1,p2,p3,p4, neutral;
    
    public Board(int players){
        dice_given = 0;
        dices=8;
        iterate=0;
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
        //arrary list használat
        if(players == 2){
            numberOfTiles = 10;
            player1Dices = (numberOfTiles/players) * 2;
            player2Dices = (numberOfTiles/players) * 2;
            row = 4;
            col = 3;
        }
        if(players == 3){
            numberOfTiles = 27;
            player1Dices = (numberOfTiles/players) * 2;
            player2Dices = (numberOfTiles/players) * 2;
            player3Dices = (numberOfTiles/players) * 2;
            row = 6;
            col = 6;
        }
        if(players == 4){
            numberOfTiles = 40;
            player1Dices = (numberOfTiles/players) * 2;
            player2Dices = (numberOfTiles/players) * 2;
            player3Dices = (numberOfTiles/players) * 2;
            player4Dices = (numberOfTiles/players) * 2;
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
                randomRow = generateRandomPosition(row);
                randomCol = generateRandomPosition(col);
                if(player1Tiles < 5 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p1, player1Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player1Tiles++;
                }
                if(player2Tiles < 5 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p2, player2Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player2Tiles++;
                }
                if(player1Tiles+player2Tiles == numberOfTiles){
                    break;
                }
        }
        fillNullPlaces();
        generateDiceForPlayer1();
        generateDiceForPlayer2();
    }

    public void ini3PlayertTable(){
        while(true){
            randomRow = generateRandomPosition(row);
                randomCol = generateRandomPosition(col);
                if(player1Tiles <9 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p1, player1Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player1Tiles++;
                }
                if(player2Tiles <9 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p2, player2Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player2Tiles++;
                }
                if(player3Tiles <9 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p3, player3Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player3Tiles++;
                }
                if(player1Tiles+player2Tiles+player3Tiles == numberOfTiles){
                    break;
                }
        }
        fillNullPlaces();
        generateDiceForPlayer1();
        generateDiceForPlayer2();
        generateDiceForPlayer3();
        
    }

    public void ini4PlayertTable(){
        while(true){
                randomRow = generateRandomPosition(row);
                randomCol = generateRandomPosition(col);
                if(player1Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p1, player1Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player1Tiles++;
                }
                if(player2Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p2, player2Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player2Tiles++;
                }
                if(player3Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p3, player3Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player3Tiles++;
                }
                if(player4Tiles <10 && board[randomRow][randomCol] == null){
                    board[randomRow][randomCol] = new Tiles(randomRow, randomCol, p4, player4Tiles);
                    board[randomRow][randomCol].setDice_num(1);
                    player4Tiles++;
                }
                if(player1Tiles+player2Tiles+player3Tiles+player4Tiles == numberOfTiles){
                    break;
                }
        }
        fillNullPlaces();
        generateDiceForPlayer1();
        generateDiceForPlayer2();
        generateDiceForPlayer3();
        generateDiceForPlayer4();
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
    public int generateRandomPosition(int upperRange){
        return (int) Math.floor(Math.random() * upperRange-1) + 1; 
    }
    
    public void generateDiceForPlayer1(){
        int range;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner().name == "p1"){
                    if(dices>player1Dices){
                        dices=player1Dices;
                    }
                    if(player1Dices >= 0 && iterate < player1Tiles){
                        range = (int) (Math.random()*dices-1)+1;
                        board[i][j].setDice_num(range + board[i][j].getDice_num());
                        player1Dices-=range;
                    }
                    if(player1Dices < 0 && iterate == player1Tiles){
                        System.out.println(ConsoleColor.BLUE + player1Dices + ConsoleColor.RESET);
                        board[i][j].setDice_num(dices+board[i][j].getDice_num());
                    }
                    iterate++;
                }
            }
        }
        dices = 8;
        iterate = 0;
    }

    public void generateDiceForPlayer2(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner().name == "p2"){
                    if(dices>player2Dices){
                        dices=player2Dices;
                    }
                    if(player2Dices > 0 && iterate < player2Tiles){
                        range = (int) (Math.random()*dices-1)+1;
                        board[i][j].setDice_num(range+board[i][j].getDice_num());
                        player2Dices-=range;
                    }
                    if(iterate==player2Tiles){
                        board[i][j].setDice_num(dices+board[i][j].getDice_num());
                    }
                    iterate++;
                }
            }
        }
        dices = 8;
        iterate = 0;
    }

    public void generateDiceForPlayer3(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].getOwner().name == "p3"){
                    if(dices>player3Dices){
                        dices=player3Dices;
                    }
                    if(player3Dices > 0 && iterate < player3Tiles){
                        range = (int) (Math.random()*dices-3)+3;
                        board[i][j].setDice_num(range+board[i][j].getDice_num());
                        player3Dices-=range;
                    }
                    if(iterate==player3Tiles){
                        board[i][j].setDice_num(dices+board[i][j].getDice_num());
                    }
                    iterate++;
                }
            }
        }
        dices = 8;
        iterate = 0;
    }

    public void generateDiceForPlayer4(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j].getOwner().name == "p4") {
                    if(dices>player4Dices){
                        dices=player4Dices;
                    }
                    if(player4Dices > 0 && iterate < player4Tiles)
                    range = (int) (Math.random()*dices-1)+1;
                        board[i][j].setDice_num(range + board[i][j].getDice_num());
                        player4Dices-=range;
                    }
                    if(iterate == player4Tiles){
                        board[i][j].setDice_num(dices + board[i][j].getDice_num());
                    }
            }
        }
        dices = 8;
        iterate = 0;
    }

    //prints the board
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
