package gameplay;

import exceptions.playerInput;
import gamearea.Board;
import gamearea.Tiles;
import players.Players;
import players.Strategies.Strategies;
import players.Strategies.enemystrategies.EnemyStrategies;
import players.Strategies.playerstrategy.MainCombat;

public class GameControl{
    private Strategies strategies;
    private Tiles[][] tileBoard;
    private MainCombat playerStrategy;
    private EnemyStrategies enemyStrategy;
    private Board board;

    public GameControl(int players){
        strategies = new Strategies(players);
        board = strategies.getBoard();
        tileBoard = strategies.getTileBoard();
        playerStrategy = new MainCombat(players);
        enemyStrategy = new EnemyStrategies(players);
        start();
    }
    
    
    public void start(){
        int endGameChoice, endTurnChoice;
        while(true){
            board.printBoard();
            while(playerHasTiles()){
                playerStrategy.playerCombat();
                endTurnChoice=playerInput.endTurnOptions();
                if(endTurnChoice == 2){
                    break;
                }
                
                if(endTurnChoice == 1){
                    continue;
                }
            }
            //initiateEnemyTurn();
            endGameChoice=playerInput.endGameOptions();
            if(endGameChoice == 2){
                break;
            }
            if(endGameChoice == 1){
                continue;
            }
        }
    }

    public void initiateEnemyTurn(){
            if(board.getPlayers() == 2){
                enemyStrategy.randomTileAttack(board.p2);
            }
            if(board.getPlayers() == 3){
                enemyStrategy.randomTileAttack(board.p2);
                enemyStrategy.randomTileAttack(board.p3);
            }
            if(board.getPlayers()== 4){
                enemyStrategy.randomTileAttack(board.p2);
                enemyStrategy.randomTileAttack(board.p3);
                enemyStrategy.randomTileAttack(board.p4);
            }
    }


    /**
     * this method is responsible for the players turn
     */


    /**
     * Shares the dice based on the formula which is =  palyerCurrentTiles/2
     * @param player which player dice to share at the end of the turn
     */
    public void giveDicesAtTheEndOfYourTurn(Players player){
        int amountOfDicesToShare=player.getPlayerTile()/2;
        int iterate = 1;
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if(tileBoard[i][j].getOwner().name.equals(player.name)){
                    int range = (int) (Math.random()*amountOfDicesToShare-1)+1;
                    System.out.println(range);
                    if(iterate < player.getPlayerTile() && amountOfDicesToShare > 0 && tileBoard[i][j].getDiceNumber()+range <=8){
                        tileBoard[i][j].setDiceNumber(tileBoard[i][j].getDiceNumber()+range);
                        amountOfDicesToShare-=range;
                        System.out.println(amountOfDicesToShare + "= amount dice to share");
                    }
                    if(iterate==player.getPlayerTile() && amountOfDicesToShare > 0){
                        tileBoard[i][j].setDiceNumber(tileBoard[i][j].getDiceNumber()+amountOfDicesToShare);
                    }
                    iterate++;
                }
            }
        }
        board.printBoard();
    }

    /**
     * This method checks player tiles<br/>
     * if you are out of tiles it ends the game
     * @param player user player
     * @return true if you have tiles, false you are out of tiles
     */
    public boolean playerHasTiles(){
        if(board.p1.getPlayerTile() != 0){
            return true;
        }else{
            System.out.println("You have been defeated");
            return false;
        }
    }
}
