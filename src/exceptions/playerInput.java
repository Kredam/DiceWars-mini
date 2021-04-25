package exceptions;

import java.util.Scanner;

public class playerInput{
    static Scanner sc = new Scanner(System.in);
    public static int players(){
        int amountofplayers;
        do{
            amountofplayers = sc.nextInt();
            if(amountofplayers>1 && amountofplayers < 5){
                return amountofplayers;
            }else{
                System.out.println("Please insert valid value!");
            }
        }while(true);
    }

    
}