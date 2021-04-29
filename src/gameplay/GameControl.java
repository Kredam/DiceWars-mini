package gameplay;

import java.util.Random;

import exceptions.playerInput;
import gamearea.Board;
import gamearea.Tiles;
import others.Console;
import players.Players;
import players.combat.enemycombat.EnemyCombat;
import players.combat.playercombat.PlayerCombat;

public class GameControl{
    Random rand = new Random();
    private Tiles[][] tileBoard;
    private PlayerCombat playerStrategy;
    private EnemyCombat enemyStrategy;
    private Board board;
    private int enemyStrategyOption;

    public GameControl(int players){
        board = new Board(players);
        tileBoard = board.getBoard();
        playerStrategy = new PlayerCombat(board);
        enemyStrategy = new EnemyCombat(board);
        chooseYourEnemyType(playerInput.chooseEnemyStrategyOption());
        start();
    }
    
    /**
     * Beállítja az ellenfél stratégia változót a játékos inputjára
     * , ha az input 4 akkor véletleszerűen állítja be 1-3 között
     * @param enemyStrategyOption
     */
    private void chooseYourEnemyType(int enemyStrategyOption){
        if(enemyStrategyOption == 4){
            this.enemyStrategyOption = (int) (Math.random()*4-1)+1;
        }else{
            this.enemyStrategyOption=enemyStrategyOption;
        }
    }

    /**
     * Kiírja melyik ellenfél stratégia lett választva
     */
    private void printEnemyStrategy(){
        if(enemyStrategyOption == 1){
            System.out.println("Enemy attack random positions");
        }
        if (enemyStrategyOption == 2) {
            System.out.println("Enemy considers if the tile has bigger number");
        }
        if(enemyStrategyOption == 3){
            System.out.println("Enemy always attack 1 numbered tiles first");
        }
    }
    /**
     * A játék ciklus ami a játék menetét szabályozza, vagyis mikor jön az játékos
     * , mikor jön a ellenfél
     */
    public void start(){
        int endGameChoice, endTurnChoice;
        Console.clearScreen();
        System.out.println("----------------------\nYour enemy's strategy is");
        printEnemyStrategy();
        System.out.println("-----------------------");
        while(true){
            while(true){
                if(playerLostTheGame()){
                    System.out.println("You have lost the game");
                    break;
                }
                board.printBoard();
                playerStrategy.playerCombat();
                endTurnChoice=playerInput.endTurnOptions();
                if(endTurnChoice == 2){
                    giveDicesAtTheEndOfYourTurn(board.p1);
                    break;
                }
                if(endTurnChoice == 1){
                    Console.clearScreen();
                    continue;
                }
            }
            board.printBoard();
            enemyTurn();
            board.printBoard();
            endGameChoice=playerInput.endGameOptions();
            if(endGameChoice == 2){
                break;
            }
            if(endGameChoice == 1){
                Console.clearScreen();
                continue;
            }
        }
    }

    /**
     * Ez szabályozza az ellenfél körét
     */
    public void enemyTurn(){
        System.out.println("START OF ENEMY TURN\n----------------------------------------");
            if(board.getNumberOfPlayers() == 2){
                chooseEnemyStrategy(enemyStrategyOption, board.p2);
            }
            if(board.getNumberOfPlayers() == 3){
                chooseEnemyStrategy(enemyStrategyOption, board.p2);
                chooseEnemyStrategy(enemyStrategyOption, board.p3);
            }
            if(board.getNumberOfPlayers()== 4){
                chooseEnemyStrategy(enemyStrategyOption, board.p2);
                chooseEnemyStrategy(enemyStrategyOption, board.p3);
                chooseEnemyStrategy(enemyStrategyOption, board.p4);
            }
        System.out.println("----------------------\nEND OF ENEMYTURN");
    }
    /**
     * Kiválasztja az ellenfelek stratégiáját, az játékos input alapján
     * @param enemyStrategyOption Játékos által választott ellenfél stratégia
     * @param player Melyik játéksra alkalmazzuk a stratégiát
     */
    public void chooseEnemyStrategy(int enemyStrategyOption, Players player){
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
     * Kiosztja a kör végén a dobókockákat =  játékos által birtokolt terület/2
     * @param player Melyik játékosnak osszuk ki
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
                    if(iterate==player.getPlayerTile() && amountOfDicesToShare > 0 && tileBoard[i][j].getDiceNumber()+amountOfDicesToShare <=8){
                        tileBoard[i][j].setDiceNumber(tileBoard[i][j].getDiceNumber()+amountOfDicesToShare);
                    }
                    iterate++;
                }
            }
        }
    }
    /**
     * Ez a metódus ellenőrzi hogy a játékosnak nyert-e<br/>
     * , megnézi hogy a játékos által birtokolt mezők egyenlők-e az összes elfoglalhatóval
     * @param player user player
     * @return true if you have tiles, false you are out of tiles
     */
    public boolean playerLostTheGame(){
        if(board.p1.getPlayerTile() == board.getNumberOfTiles()){
            return true;
        }else{
            return false;
        }
    }
}
