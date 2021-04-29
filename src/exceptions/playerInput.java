package exceptions;

import java.util.*;

import others.Console;

public class playerInput{
    static Scanner sc = new Scanner(System.in);
    /**
     * Lekezeli hogy a beírt játékos szám létezik-e
     * @return játékos szám, ha az input helyes
     */
    public static int checkPlayerInput(){
        int numberOfPlayers;
        do{
            System.out.println("How many players between 2 and 4?");
            try {
                numberOfPlayers = sc.nextInt();
                if(numberOfPlayers == 2 || numberOfPlayers == 3 || numberOfPlayers == 4){
                    return numberOfPlayers;
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
     * Lekezeli hogy a beírt sor pozíció intervallumon belül van-e
     * @return sor változó, ha az input helyes
     */
    public static int posX(int rowPosition){
        int row;
        do{
            System.out.println("which row?");
           try{
                row=sc.nextInt();
                if(row>=0 && row<rowPosition){
                    return row;
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
     * Lekezeli hogy a beírt oszlop pozíció intervallumon belül van-e
     * @return oszlop változó, ha az input helyes
     */
    public static int posY(int col){
        int column;
        do{
            System.out.println("which col?");
           try{
            column=sc.nextInt();
                if(column>=0 && column<col){
                    return column;
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
     * Lekezeli hogy a beírt játék végi opció létezik-e
     * @return játék végi opció változó, ha az input helyes
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
    /**
     * Lekezeli hogy a beírt kör végi opció létezik-e
     * @return kör végi opció változó, ha az input helyes
     */
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
    /**
     * Lekezeli hogya beí¶t ellenfél stratégia létezik-e
     * @return ellenfél stratégiáját, ha az input helyes
     */
    public static int chooseEnemyStrategyOption(){
        int enemyOption;
        do{
            System.out.println("Options:");
            System.out.println("1. Enemy attack random positions");
            System.out.println("2. Enemy considers if the tile has bigger number");
            System.out.println("3. Enemy always attack 1 numbered tiles first");
            System.out.println("4. Random option from the above");
            try{
                enemyOption=sc.nextInt();
                if(enemyOption==1 || enemyOption == 2 || enemyOption == 3 || enemyOption == 4){
                    return enemyOption;
                }else{
                    System.out.println("Choose from the options!");
                }

            }catch(InputMismatchException e){
                System.out.println(Console.WHITE_BOLD+"Please enter number!"+Console.RESET);
                sc.next();
            }

        }while(true);
    }

}