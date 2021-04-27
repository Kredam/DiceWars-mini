package gameplay;
import exceptions.*;

public class Game {
    GameControl gameControl;

    public Game(int players){
        gameControl = new GameControl(players);
        start();
    }
    public void start(){
        int endGameChoice, endTurnChoice;
        while(true){
            gameControl.getBoard().printBoard();
            while(gameControl.playerHasTiles()){
                endTurnChoice=playerInput.endTurnOptions();
                if(endTurnChoice == 2){
                    break;
                }
                gameControl.playerTurn();
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
            if(gameControl.getBoard().getPlayers() == 2){
                gameControl.enemyTurn(gameControl.getBoard().p2);
            }
            if(gameControl.getBoard().getPlayers() == 3){
                gameControl.enemyTurn(gameControl.getBoard().p2);
                gameControl.enemyTurn(gameControl.getBoard().p3);
            }
            if(gameControl.getBoard().getPlayers()== 4){
                gameControl.enemyTurn(gameControl.getBoard().p2);
                gameControl.enemyTurn(gameControl.getBoard().p3);
                gameControl.enemyTurn(gameControl.getBoard().p4);

            }
    }
}
