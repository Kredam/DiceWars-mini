package game;

import java.util.*;
import gamearea.*;

public class GameControl {
    public static final String RED = "\033[0;31m";     // RED
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static Random rand = new Random();
    public static int attack_value, defend_value; 
    public static int random_dice_number;

    public static void combat(Tiles attack, Tiles defend){
        rollDice(attack, defend);
        if(attack.getOwner() != defend.getOwner() && validatePosition(attack, defend)){
            if (getAttack_value() > getDefend_value()){

            } else if (getAttack_value() == getDefend_value()){

            } else {

            }
        }
    }
    
    public static void rollDice(Tiles attack, Tiles defend){
        int attack_dice_number = rand.nextInt(7-1)+1;
        int defend_dice_number = rand.nextInt(7-1)+1;
        attack_value = attack.getDice_num() * attack_dice_number;
        defend_value = defend.getDice_num() * defend_dice_number;
            switch(attack.getOwner().name){
                case "p1":
                    System.out.print(RED + attack_value + RESET + " | ");
                break;
                case "p2":
                    System.out.print(GREEN + attack_value + RESET + " | ");
                break;
                case "p3":
                    System.out.print(BLUE + attack_value + RESET + " | ");
                break;
                case "p4":
                    System.out.print(YELLOW + attack_value + RESET + " | ");
                break;
            }
            switch(attack.getOwner().name){
                case "p1":
                    System.out.print(RED + defend_value + RESET + " | ");
                break;
                case "p2":
                    System.out.print(GREEN + defend_value + RESET + " | ");
                break;
                case "p3":
                    System.out.print(BLUE + defend_value + RESET + " | ");
                break;
                case "p4":
                    System.out.print(YELLOW + defend_value + RESET + " | ");
                break;
            }

    }
    
    public static void changePosition(){

    }
    public static int getAttack_value() {
        return attack_value;
    }
    public static int getDefend_value() {
        return defend_value;
    }
    public static boolean validatePosition(Tiles attack, Tiles defend){
        return true;
    }
}
