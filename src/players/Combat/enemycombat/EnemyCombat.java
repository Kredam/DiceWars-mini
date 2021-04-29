package players.Combat.enemycombat;

import players.Players;
import players.Combat.Combat;
import gamearea.*;

public class EnemyCombat extends Combat{
    private Tiles[][] tileBoard;
    private Board board;

    /**
     * EnemyCombat konstruktor(Combat gyerekosztálya)
     * @param board A tábla amin a stratégiákat alkamazza
     */
    public EnemyCombat(Board board) {
        super(board);
        this.board=getBoard();
        tileBoard=getTileBoard();
    }
    /**
     * Az ellenfél valamelyik random szomszédos ellenfél csempét támadja
     * @param player Melyik ellenfélre alkalmazzuk
     */
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
    /**
     * Az ellenfél figyelembe veszi a környező csempéket és hogy azon van-e 1 darab dobókocka
     * , ha nincs ilyen akkor random támadja valamelyik csempét
     * @param player Melyik ellenfélre alkalmazzuk
     */   
    public void oneNumberedTileAttack(Players player){
        for (int x = 0; x < board.getRow() ;x++) {
            for (int y = 0; y < board.getCol(); y++) {
                if(neighboursAround(x, y, tileBoard[x][y].getOwner()) && 
                tileBoard[x][y].getOwner().name.equals(player.name) &&
                tileBoard[x][y].isSelectable()){
                                if(upperNeighbour(x, y, player)){
                                    attack(x, y, x-1, y);
                                    break;
                                }
                                if(bottomNeighbour(x, y, player)){
                                    attack(x, y, x+1, y);
                                    break;
                                }
                                if(leftNeighbour(x, y, player)){
                                    attack(x, y, x, y-1);
                                    break;
                                }
                                if(rightNeighbour(x, y, player)){
                                    attack(x, y, x, y+1);
                                    break;
                                }
                                if(upperNeighbour(x, y, player) && upperNeigbourNumberIsOne(x, y)){
                                    attack(x, y, x-1, y);
                                    break;
                                }
                                if(bottomNeighbour(x, y, player) && bottomNeigbourNumberIsOne(x, y)){
                                    attack(x, y, x+1, y);
                                    break;
                                }
                                if(leftNeighbour(x, y, player) && leftNeigbourNumberIsOne(x, y)){
                                    attack(x, y, x, y-1);
                                    break;
                                }
                                if(rightNeighbour(x, y, player) && rightNeigbourNumberIsOne(x, y)){
                                    attack(x, y, x, y+1);
                                    break;
                                }
                            }
            }
        }
    }    
    /**
     * Az ellenfél figyelembe veszi a környező csempéket és hogy azon kevesebb dobókocka van-e
     * , ha nincs ilyen akkor random támadja valamelyik csempét
     * @param player Melyik ellenfélre alkalmazzuk
     */
    public void biggerNumberedTileAttack(Players player){
        for (int x = 0; x < board.getRow() ;x++) {
            for (int y = 0; y < board.getCol(); y++) {
                if(neighboursAround(x, y, tileBoard[x][y].getOwner()) && 
                tileBoard[x][y].getOwner().name.equals(player.name) &&
                tileBoard[x][y].isSelectable()){
                        if(upperNeighbour(x, y, player)){
                            attack(x, y, x-1, y);
                            break;
                        }
                        if(bottomNeighbour(x, y, player)){
                            attack(x, y, x+1, y);
                            break;
                        }
                        if(leftNeighbour(x, y, player)){
                            attack(x, y, x, y-1);
                            break;
                        }
                        if(rightNeighbour(x, y, player)){
                            attack(x, y, x, y+1);
                            break;
                        }
                        if(upperNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x-1][y].getDiceNumber()){
                            attack(x, y, x-1, y);
                            break;
                        }
                        if(bottomNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x+1][y].getDiceNumber()){
                            attack(x, y, x+1, y);
                            break;
                        }
                        if(leftNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x][y-1].getDiceNumber()){
                            attack(x, y, x, y-1);
                            break;
                        }
                        if(rightNeighbour(x, y, player) && tileBoard[x][y].getDiceNumber()>=tileBoard[x][y+1].getDiceNumber()){
                            attack(x, y, x, y+1);
                            break;
                        }
                }
            }
        }
    }    
}
 