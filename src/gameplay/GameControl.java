package gameplay;

import java.util.Random;

import exceptions.playerInput;
import gamearea.Board;
import gamearea.Tiles;
import players.Players;
import players.Strategies.enemystrategies.EnemyStrategies;
import players.Strategies.playerstrategy.MainCombat;

public class GameControl{
    Random rand = new Random();
    private Tiles[][] tileBoard;
    private MainCombat playerStrategy;
    private EnemyStrategies enemyStrategy;
    private Board board;
    private int enemyStrategyOption;

    public GameControl(int players){
        board = new Board(players);
        tileBoard = board.getBoard();
        playerStrategy = new MainCombat(board);
        enemyStrategy = new EnemyStrategies(board);
        chooseYourEnemyType(playerInput.chooseEnemyBehaviourOptions());
        start();
    }
    
    public void chooseYourEnemyType(int enemyStrategyOption){
        if(enemyStrategyOption == 4){
            this.enemyStrategyOption = (int) (Math.random()*4-1)+1;
        }else{
            this.enemyStrategyOption=enemyStrategyOption;
        }
        System.out.println("You've choosen "+this.enemyStrategyOption+" strategy");
    }

    public void start(){
        int endGameChoice, endTurnChoice;
        Console.clearScreen();
        System.out.println("Your enemy's strategy is " + enemyStrategyOption);
        while(true){
            
            board.printBoard();
            while(playerHasTiles()){
                playerStrategy.playerCombat();
                endTurnChoice=playerInput.endTurnOptions();
                if(endTurnChoice == 2){
                    giveDicesAtTheEndOfYourTurn(board.p1);
                    break;
                }
                if(endTurnChoice == 1){
                    continue;
                }
            }
            initiateEnemyTurn();
            endGameChoice=playerInput.endGameOptions();
            if(endGameChoice == 2){
                break;
            }
            if(endGameChoice == 1){
                continue;
            }
        }
    }

    public void chooseEnemyBehaviour(){

    }

    public void initiateEnemyTurn(){
            if(board.getPlayers() == 2){
                initiateEnemyStrategy(enemyStrategyOption, board.p2);
            }
            if(board.getPlayers() == 3){
                initiateEnemyStrategy(enemyStrategyOption, board.p2);
                initiateEnemyStrategy(enemyStrategyOption, board.p3);
            }
            if(board.getPlayers()== 4){
                initiateEnemyStrategy(enemyStrategyOption, board.p2);
                initiateEnemyStrategy(enemyStrategyOption, board.p3);
                initiateEnemyStrategy(enemyStrategyOption, board.p4);
            }
    }
    public void initiateEnemyStrategy(int enemyStrategyOption, Players player){
        if(enemyStrategyOption==1){
            enemyStrategy.randomTileAttack(player);
            giveDicesAtTheEndOfYourTurn(player);
        }
        if(enemyStrategyOption==2){
            enemyStrategy.biggerNumberedTileAttack(player);
            giveDicesAtTheEndOfYourTurn(player);
        }
        if(enemyStrategyOption==3){
            enemyStrategy.oneNumberedTileAttack(player);
            giveDicesAtTheEndOfYourTurn(player);
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
                    if(iterate < player.getPlayerTile() && amountOfDicesToShare > 0 && tileBoard[i][j].getDiceNumber()+range <=8){
                        tileBoard[i][j].setDiceNumber(tileBoard[i][j].getDiceNumber()+range);
                        amountOfDicesToShare-=range;
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
