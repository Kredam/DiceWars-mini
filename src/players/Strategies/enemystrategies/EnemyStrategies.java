package players.Strategies.enemystrategies;

import players.Strategies.Strategies;

import players.Players;
import players.Strategies.Strategies;

import gamearea.*;

public class EnemyStrategies extends Strategies{
    private Tiles[][] tileBoard;
    private Board board;

    public EnemyStrategies(int players) {
        super(players);
        tileBoard=super.getTileBoard();
        board = super.getBoard();
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
}
