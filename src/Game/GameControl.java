package Game;

import java.util.*;
import GameArea.*;

public class GameControl {
    public static final String RED = "\033[0;31m";     // RED
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    Random rand = new Random();
    public static int attack_value, defend_value; 

    public static void combat(Tiles attack, Tiles defend){
        rollDice(attack, defend);
        if(attack.getOwner() != defend.getOwner()){
            if (getAttack_value() > getDefend_value()){

            } else if (getAttack_value() == getDefend_value()){

            } else {

            }
        }
    }
    
    public static void rollDice(Tiles attack, Tiles defend){
            attack_value=attack.getDice_value() * attack.getDice_num();
            if (attack.getOwner().name == "p1") {
                System.out.print(RED + attack_value + RESET + " | ");
            }
            if(attack.getOwner().name == "p2") {
                System.out.print(GREEN + attack_value + RESET + " | ");
            }
            if(attack.getOwner().name == "p3") {
                System.out.print(BLUE + attack_value + RESET + " | ");
            }
            if(attack.getOwner().name == "p4") {
                System.out.print(YELLOW + attack_value + RESET + " | ");
            }
            defend_value=defend.getDice_num()*defend.getDice_value();
            if (defend.getOwner().name == "p1") {
                System.out.print(RED + attack_value + RESET + " | ");
            }
            if(defend.getOwner().name == "p2") {
                System.out.print(GREEN + defend_value + RESET + " | ");
            }
            if(defend.getOwner().name == "p3") {
                System.out.print(BLUE + defend_value + RESET + " | ");
            }
            if(defend.getOwner().name == "p4") {
                System.out.print(YELLOW + defend_value + RESET + " | ");
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
    public static boolean validatePosition(){
        return true;
    }
}
