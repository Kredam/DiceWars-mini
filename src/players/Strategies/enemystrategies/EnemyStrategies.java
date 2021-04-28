package players.Strategies.enemystrategies;

import players.Strategies.Strategies;

import players.Players;

import gamearea.*;

public class EnemyStrategies extends Strategies{
    private Tiles[][] tileBoard;
    private Board board;

    public EnemyStrategies(Board board) {
        super(board);
        this.board=getBoard();
        tileBoard=getTileBoard();
    }

    public void randomTileAttack(Players player){
        int choice;
        for (int i = 0; i < board.getRow() ;i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(neighboursAround(i, j, tileBoard[i][j].getOwner()) && 
                tileBoard[i][j].getOwner().name.equals(player.name) &&
                        tileBoard[i][j].isSelectable()){
                            while(true){
                                choice = (int) (Math.random()*5-1)+1;
                                if(choice == 1 && upperNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i-1, j);
                                    break;
                                }
                                if(choice == 2 && bottomNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i+1, j);
                                    break;
                                }
                                if(choice == 3 && leftNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i, j-1);
                                    break;
                                }
                                if(choice == 4 && rightNeighbour(i, j, tileBoard[i][j].getOwner())){
                                    attack(i, j, i, j+1);
                                    break;
                                }
                            }
                        }
            }
        }
    }    
    public void oneNumberedTileAttack(Players player){
        for (int i = 0; i < board.getRow() ;i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(neighboursAround(i, j, tileBoard[i][j].getOwner()) && 
                tileBoard[i][j].getOwner().name.equals(player.name) &&
                        tileBoard[i][j].isSelectable()){
                                attackOneNumberedTiles(i, j, tileBoard[i][j].getOwner());
                                basicAttack(i, j, tileBoard[i][j].getOwner());
                        }
            }
        }
    }    
    private void attackOneNumberedTiles(int x, int y, Players player){
        if(upperNeighbour(x, y, player) && upperNeigbourNumberIsOne(x, y, player)){
            attack(x, y, x-1, y);
        }
        if(bottomNeighbour(x, y, player) && bottomNeigbourNumberIsOne(x, y, player)){
            attack(x, y, x+1, y);
        }
        if(leftNeighbour(x, y, player) && leftNeigbourNumberIsOne(x, y, player)){
            attack(x, y, x, y-1);
        }
        if(rightNeighbour(x, y, player) && rightNeigbourNumberIsOne(x, y, player)){
            attack(x, y, x, y+1);
        }
    }
    
    public void biggerNumberedTileAttack(Players player){
        for (int i = 0; i < board.getRow() ;i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(neighboursAround(i, j, tileBoard[i][j].getOwner()) && 
                tileBoard[i][j].getOwner().name.equals(player.name) &&
                tileBoard[i][j].isSelectable()){
                        attackSmallerNumberedTile(i, j, tileBoard[i][j].getOwner());
                        //basicAttack(i, j, tileBoard[i][j].getOwner());
                }
            }
        }
    }    
    private void attackSmallerNumberedTile(int x, int y, Players player){
        if(upperNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x-1][y].getDiceNumber()){
            attack(x, y, x-1, y);
        }
        if(bottomNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x+1][y].getDiceNumber()){
            attack(x, y, x+1, y);
        }
        if(leftNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x][y-1].getDiceNumber()){
            attack(x, y, x, y-1);
        }
        if(rightNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x][y+1].getDiceNumber()){
            attack(x, y, x, y+1);
        }
    }
    
    private void basicAttack(int x, int y, Players player){
        if(upperNeighbour(x, y, player)){
            attack(x, y, x-1, y);
        }
        if(bottomNeighbour(x, y, player)){
            attack(x, y, x+1, y);
        }
        if(leftNeighbour(x, y, player)){
            attack(x, y, x, y-1);
        }
        if(rightNeighbour(x, y, player)){
            attack(x, y, x, y+1);
        }
    }
}
