package GameArea;

public class Board {
    public static final String RED = "\033[0;31m";     // RED
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String WHITE = "\u001B[37m";
    public int[][] board;
    public int player1Tiles, player2Tiles, player3Tiles, player4Tiles;
    public int players;
    public int numberOfTiles;
    public int randomRow, randomCol;
    public int row,col;
    
    public Board(int players){
        player1Tiles = 0;
        player2Tiles = 0;
        player3Tiles = 0;
        player4Tiles = 0;
        this.players = players;
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

    public int[][] getBoard() {
        return board;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    //create board based on player input
    public void createBoard(){
        board = new int[row][col];
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
                if(player1Tiles <5 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 1;
                    player1Tiles++;
                }
                if(player2Tiles <5 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 2;
                    player2Tiles++;
                }
                if(player1Tiles+player2Tiles == numberOfTiles){
                    break;
                }
                
        }
    }

    public void ini3PlayertTable(){
        while(true){
                randomRow = generateRandomInt(row);
                randomCol = generateRandomInt(col);
                if(player1Tiles <9 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 1;
                    player1Tiles++;
                }
                if(player2Tiles <9 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 2;
                    player2Tiles++;
                }
                if(player3Tiles <9 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 3;
                    player3Tiles++;
                }
                if(player1Tiles+player2Tiles+player3Tiles == numberOfTiles){
                    break;
                }
                
        }
    }

    public void ini4PlayertTable(){
        while(true){
                randomRow = generateRandomInt(row);
                randomCol = generateRandomInt(col);
                if(player1Tiles <10 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 1;
                    player1Tiles++;
                }
                if(player2Tiles <10 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 2;
                    player2Tiles++;
                }
                if(player3Tiles <10 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 3;
                    player3Tiles++;
                }
                if(player4Tiles <10 && board[randomRow][randomCol] == 0){
                    board[randomRow][randomCol] = 4;
                    player4Tiles++;
                }
                if(player1Tiles+player2Tiles+player3Tiles+player4Tiles == numberOfTiles){
                    break;
                }
                
        }
    }

    //generate a random number between 1 and the value that is giben by parameter
    public static int generateRandomInt(int upperRange){
        return (int) Math.floor(Math.random() * upperRange-1) + 1; 
    }

    /*public boolean validatePosition(int randomRow, int randomCol){
        //4 szomszéd
        if  (randomCol !=0 || randomCol !=col || randomRow != 0 || randomRow != row      || 
            (randomRow != 0 && randomRow != row) || (randomCol != 0 && randomCol != col) &&
            board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0     && 
            board[randomRow][randomCol+1] != 0 && board[randomRow][randomCol-1] != 0     ){
            return true;
        }
        //3 szomszéd
        else if( randomCol !=0 || randomCol != col || randomRow != 0 || randomRow != row || 
                 (randomRow != 0 && randomRow != row) || (randomCol != 0 && randomCol != col) &&
                 (board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0 && board[randomRow][randomCol-1] != 0 ) ||
                 (board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0 && board[randomRow][randomCol+1] != 0) ||
                 (board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0 && board[randomRow][randomCol+1] != 0) ||
                 (board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0 && board[randomRow][randomCol+1] != 0) ){
            return true;
        }
        //2 szomszéd
        else if( randomCol !=0 || randomCol !=col || randomRow != 0 || randomRow != row || 
                (randomRow != 0 && randomRow != row) || (randomCol != 0 && randomCol != col) &&
                (board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0)  ||
                 (board[randomRow][randomCol+1] != 0 && board[randomRow][randomCol-1] != 0) ||
                 (board[randomRow+1][randomCol] != 0 && board[randomRow-1][randomCol] != 0) ||
                 (board[randomRow][randomCol-1] != 0 && board[randomRow][randomCol+1] != 0) ){
            return true;
        }
        //1 szomszéd
        else if(randomCol !=0 || randomCol !=col || randomRow != 0 || randomRow != row || 
                (randomRow != 0 && randomRow != row) || (randomCol != 0 && randomCol != col) &&
                board[randomRow+1][randomCol] != 0 ||
                board[randomRow][randomCol+1] != 0 ||
                board[randomRow][randomCol-1] != 0 ||
                board[randomRow-1][randomCol] != 0 ){
            return true;
        } else {
            return false;
        }
    }*/

    //prints the board
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 1){
                    System.out.print(RED + board[i][j] + RESET + " | ");
                } 
                if(board[i][j] == 2) {
                    System.out.print(GREEN + board[i][j] + RESET + " | ");
                }
                if(board[i][j] == 3) {
                    System.out.print(BLUE + board[i][j] + RESET + " | ");
                }
                if(board[i][j] == 4) {
                    System.out.print(YELLOW + board[i][j] + RESET + " | ");
                }
                if(board[i][j] == 0){
                    System.out.print(WHITE + board[i][j] + RESET + " | ");
                }
            }
            System.out.println("");
        }
    }
    
}
