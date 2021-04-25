package exceptions;

import java.util.*;

public class playerInput{
    static Scanner sc = new Scanner(System.in);
    
    public static int checkPlayerInput(){
        int player;
        do{
            System.out.println("How many player between 2 and 4?");
            try {
                player = sc.nextInt();
                if(player == 2 || player == 3 || player == 4){
                    return player;
                }else{
                    System.out.println("Not a valid option, please try again!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Not a number please try again!");
                sc.nextLine();
            }
        }while(true);
    }
    public static int posX(int row){
        int x;
        do{
           try{
                x=sc.nextInt();
                if(x>=0 && x<row){
                    return x;
                }else{
                    System.out.println("Please enter x from the intervall!");
                }
           }catch(InputMismatchException e){
               System.out.println("Please enter a number!");
               sc.nextLine();
           }
        }while(true);
    }

    public static int posY(int col){
        int y;
        do{
           try{
                y=sc.nextInt();
                if(y>=0 && y<col){
                    return y;
                }else{
                    System.out.println("Please enter y from the intervall!");
                }
           }catch(InputMismatchException e){
               System.out.println("Please enter a number!");
               sc.nextLine();
           }
        }while(true);
    }

}