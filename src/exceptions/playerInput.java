package exceptions;

import java.util.*;

import gameplay.Console;

public class playerInput{
    static Scanner sc = new Scanner(System.in);
    
    /**
     * Ask the player for input as longs as it is not valid, when choosing player number
     * @return player number, if it's correct
     */
    public static int checkPlayerInput(){
        int player;
        do{
            System.out.println("How many player between 2 and 4?");
            try {
                player = sc.nextInt();
                if(player == 2 || player == 3 || player == 4){
                    return player;
                }else{
                    System.out.println(Console.WHITE_BOLD+"Not a valid option, please try again!"+Console.RESET);
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println(Console.WHITE_BOLD+"Not a number, please try again!"+Console.RESET);
                sc.nextLine();
            }
        }while(true);
    }
    /**
     * Ask the player for input as longs as it is not valid, when choosing row
     * @return row position if it's correct
     */
    public static int posX(int row){
        int x;
        do{
            System.out.println("which row?");
           try{
                x=sc.nextInt();
                if(x>=0 && x<row){
                    return x;
                }else{
                    System.out.println(Console.WHITE_BOLD+"Please enter x from the intervall!"+Console.RESET);
                }
           }catch(InputMismatchException e){
            System.out.println(Console.WHITE_BOLD+"Not a number, please try again!"+Console.RESET);
            sc.next();
           }
        }while(true);
    }
    /**
     * Ask the player for input as longs as it is not valid, when choosing col
     * @return col position if it's correct
     */
    public static int posY(int col){
        int y;
        do{
            System.out.println("which col?");
           try{
                y=sc.nextInt();
                if(y>=0 && y<col){
                    return y;
                }else{
                    System.out.println(Console.WHITE_BOLD+"Please enter y from the intervall!"+Console.RESET);
                }
           }catch(InputMismatchException e){
            System.out.println(Console.WHITE_BOLD+"Not a number, please try again!"+Console.RESET);
            sc.next();
           }
        }while(true);
    }
    /**
     * Ask the player for input as longs as it is not valid, when choosing end game option
     * @return end game option if it's correct
     */
    public static int endGameOptions(){
        int endturnoptions;
        do{
            System.out.println(Console.GREEN_BOLD+"Press 1 to cotinue game"+ Console.RED_BOLD +"    Press 2 to quit game"+Console.RESET);
            try{
                endturnoptions = sc.nextInt();
                if(endturnoptions == 2 || endturnoptions == 1){
                    return endturnoptions;
                }else{
                    System.out.println(Console.WHITE_BOLD+"Not and option!"+Console.RESET);
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter a number!");
                sc.next();
            }
        }while(true);
    }
    public static int endTurnOptions(){
        int endturnoptions;
        do{
            System.out.println(Console.GREEN_BOLD+"Press 1 for next attack"+ Console.RED_BOLD+ "     Press 2 to end turn!"+Console.RESET);
            try{
                endturnoptions = sc.nextInt();
                if(endturnoptions == 2 || endturnoptions == 1){
                    return endturnoptions;
                }else{
                    System.out.println(Console.WHITE_BOLD+"Not and option!"+Console.RESET);
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter a number!");
                sc.next();
            }
        }while(true);
    }
    public static int chooseEnemyBehaviourOptions(){
        int enemyOption;
        do{
            System.out.println("Options:");
            System.out.println("1. Enemy attack random positions");
            System.out.println("2. Enemy considers if the tile has bigger number");
            System.out.println("3. Enemy always attack 1 numbered tiles first");
            enemyOption=sc.nextInt();
            try{
                if(enemyOption==1 || enemyOption == 2 || enemyOption == 3){
                    return enemyOption;
                }else{
                    System.out.println("Choose from the options!");
                }

            }catch(InputMismatchException e){
                System.out.println("Please enter number!");
                sc.next();
            }

        }while(true);
    }

}