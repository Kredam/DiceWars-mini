package players.combat.playercombat;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.playerInput;
import gamearea.*;
import others.Console;
import players.combat.Combat;

public class PlayerCombat extends Combat{
    private Scanner sc = new Scanner(System.in);
    private Tiles[][] tileBoard;
    private Board board;

    /**
     * PlayerCombat konstruktor(Combat gyerekosztálya)
     * @param board
     */
    public PlayerCombat(Board board) {
        super(board);
        this.board = getBoard();
        tileBoard=getTileBoard();
    }
    /**
     * Ez kezeli le a user támadását, itt haszánljuk a neigbour checkinges metódusokat
     */
    public void playerCombat(){
        int OwnPosX = playerInput.posX(board.getRow());
        int OwnPosY = playerInput.posY(board.getRow());
        if(tileBoard[OwnPosX][OwnPosY].isSelectable() && 
            tileBoard[OwnPosX][OwnPosY].getOwner().name.equals("p1") &&
                super.neighboursAround(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                    do{
                        printPossibleMoves(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner());
                        try{
                            int choice = sc.nextInt();
                            if(choice == 1 && upperNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                attack(OwnPosX, OwnPosY, OwnPosX-1, OwnPosY);
                                break;
                            }
                            else if(choice == 2 && bottomNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                attack(OwnPosX, OwnPosY, OwnPosX+1, OwnPosY);
                                break;
                            }
                            else if(choice == 3 && leftNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                attack(OwnPosX, OwnPosY, OwnPosX, OwnPosY-1);
                                break;
                            }
                            else if(choice == 4 && rightNeighbour(OwnPosX, OwnPosY, tileBoard[OwnPosX][OwnPosY].getOwner())){
                                attack(OwnPosX, OwnPosY, OwnPosX, OwnPosY+1);
                                break;
                            }else if(choice == 5){
                                break;
                            }else{
                                System.out.println(Console.WHITE_BOLD+"You've choosen an attack option that is invalid for this tile, please choose again!"+Console.RESET);
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Please enter a number!");
                            sc.nextLine();
                        }
                }while(true);
                
        }else{
            System.out.println("Not your tile or The tile cannot be selected or no nearby neighbours, please choose again!");
        }
}
    
}
