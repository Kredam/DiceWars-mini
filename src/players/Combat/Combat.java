package players.Combat;
import gamearea.*;

import players.Players;

public class Combat {
    private Tiles[][] tileBoard;
    private Board board;

    public Combat(Board board){
        this.board = board;
        tileBoard = board.getBoard();
    }
    public Board getBoard() {
        return board;
    }
    /**
     * Visszadja a pályát
     * @return pálya vagyis a 2d Tiles tömböt
     */
    public Tiles[][] getTileBoard() {
        return tileBoard;
    }
    /**
     * Lekezeli a támadást, vagyis összehasonlítja hogy ki mennyit dobott, 
     * és annak függvényében hívja meg a changePositionOnWin-t vagy a changePositionOnLoss-t
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player
     * @return igazat ha van alsó 1 darab dobókockájú csempe
     */   
    public void attack(int attackX, int attackY, int defendX, int defendY){
        int attackValue = rollDiceAttack(tileBoard[attackX][attackY].getDiceNumber());
        int defendValue = rollDiceAttack(tileBoard[defendX][defendY].getDiceNumber());
        if(attackValue > defendValue){
            changePositionOnWin(attackX, attackY, defendX, defendY);
        }
        if(attackValue < defendValue || attackValue == defendValue){
            changePositionOnLoss(attackX, attackY);
        }
        System.out.println("Attack roll: " + attackValue + ", Defend roll: " + defendValue);
        
    }

    /**
     * Lezeli azt az eseményt ha a támadó nagyobbat dobott vagyis nyert
     * @param attackX Támdaó sor koordináta, ahonnan kockát kell levonni
     * @param attackY Támadó osolop koordináta ahonnan kockát kell levonni
     * @param defendX Védekező sor koordináta, ahova kockát kell átrakni
     * @param defendY Védekező osolop koordináta ahova kockát kell átrakni
     */
    public void changePositionOnWin(int attackX, int attackY, int defendX, int defendY){
        tileBoard[attackX][attackY].getOwner().increasePlayerTile();
        tileBoard[defendX][defendY].getOwner().decreasePlayerTile();
        tileBoard[defendX][defendY].setOwner(tileBoard[attackX][attackY].getOwner());
        tileBoard[defendX][defendY].setDiceNumber(tileBoard[attackX][attackY].getDiceNumber()-1);
        tileBoard[attackX][attackY].setDiceNumber(1);
    }
    /**
     * Vesztés esetén történő kocka vesztést kezeli le
     * @param attackX Támdaó sor koordináta, ahonnan kockát kell levonni
     * @param attackY Támadó osolop koordináta ahonnan kockát kell levonni
     */
    public void changePositionOnLoss(int attackX, int attackY){
        tileBoard[attackX][attackY].setDiceNumber(1);
    }
    /**
     * Lekezeli a dobókocka harcot
     * @param dices hány dobókocka dobás történjen
     * @return dobott összeg
     */
    public int rollDiceAttack(int dices){
        int numberOfTimes = 0;
        int DiceValue = 0;
        int dice_number = 0;
        while(numberOfTimes < dices){
            dice_number = (int) (Math.random()*7-1)+1;
            DiceValue+=dice_number;
            numberOfTimes++;
        }
        return DiceValue;
    }


   /**
    * Ellenőrzi hogy van-e legalább egy szomszédja
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player Melyik játékosnak ellenőrizzük le hogy van legalább egy szomszédja
     * @return igazat ad vissza ha van legalább 1, hamis ha nincs
     */
    protected boolean neighboursAround(int x, int y, Players player){
        if(upperNeighbour(x, y, player) || 
            bottomNeighbour(x, y, player) ||
                leftNeighbour(x, y, player) ||
                    rightNeighbour(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a felső csempe nem a sajátja-e, és hogy semleges-e, és hogy van-e felső szomszéd
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player Melyik játékosnak ellenőrizzük a felső szomszédját
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, és van felső szomszéd, ellenkező esetben hamis
     */
    protected boolean upperNeighbour(int x, int y, Players player) {
        if( x > 0 && upperNeighbourOwner(x, y, player)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a alsó csempe nem a sajátja-e, és hogy semleges-e, és hogy van-e alsó szomszéd
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player 
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, és van alsó szomszéd, ellenkező esetben hamis
     */
     protected boolean bottomNeighbour(int x, int y, Players player) {
        if(x<board.getRow()-1 && bottomNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a bal oldali csempe nem a sajátja-e, és hogy semleges-e, és hogy van-e bal oldali szomszéd  
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player Melyik játékosnak ellenőrizzük a bal oldali a szomszédját
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, és van bal oldali szomszéd, ellenkező esetben hamis
     */
    protected boolean leftNeighbour(int x, int y, Players player) {
        if(y>0 && leftNeighbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a jobb oldali csempe nem a sajátja-e, és hogy semleges-e, és hogy van-e jobb oldali szomszéd
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player Melyik játékosnak ellenőrizzük a jobb oldali szomszédját
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, és van jobb oldali szomszéd, ellenkező esetben hamis
     */
    protected boolean rightNeighbour(int x, int y, Players player) {
        if(y<board.getCol()-1 && rightNeigbourOwner(x, y, player)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a felső csempe nem a sajátja-e, és hogy semleges-e
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player ellenőrzi a felső szomszéd csempének a tulajdonosát
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, ellenkező esetben hamis
     */
    private boolean upperNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x-1][y].getOwner().name != player.name && notNeutralTile(x-1, y)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a alsó csempe nem a sajátja-e, és hogy semleges-e
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player ellenőrzi a alsó szomszéd csempének a tulajdonosát
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, ellenkező esetben hamis
     */
    private boolean bottomNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x+1][y].getOwner().name != player.name && notNeutralTile(x+1, y)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a bal oldali csempe nem a sajátja-e, és hogy semleges-e
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player ellenőrzi a bal oldali szomszéd csempének a tulajdonosát
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, ellenkező esetben hamis
     */
    private boolean leftNeighbourOwner(int x, int y, Players player){
        if(tileBoard[x][y-1].getOwner().name != player.name && notNeutralTile(x, y-1)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Ellenőrzi hogy a jobb oldali csempe nem a sajátja-e
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player ellenőrzi a jobb oldali szomszéd csempének a tulajdonosát
     * @return igazat ha nem ő a tulajdonosa és nem semleges a csempe, ellenkező esetben hamis
     */
    private boolean rightNeigbourOwner(int x, int y, Players player){
        if(tileBoard[x][y+1].getOwner().name != player.name && notNeutralTile(x, y+1)){
            return true;
        } else {
            return false;
        }
    }    
    /**
    * Ellenőrzi hogy semleges terület-e az adott pozíció
    * @param x sor koordináta ahonnan ellenőrizze
    * @param y oszlop koordináta ahonnan ellenőrizze
    * @return igazat ha nem semleges a csempe, ellenkező esetben hamis
    */
    public boolean notNeutralTile(int x, int y){
        if(tileBoard[x][y].getClass() != NeutralTile.class){
            return true;
        } else {
            return false;
        }
    }
    /**
     * Kiiratja a lehetséges lépéseket adott pozíción
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @param player Melyik játékosnak ellenőrizzük szomszédjait
     */
    public void printPossibleMoves(int x, int y, Players player){
        if(upperNeighbour(x, y, player)){
            System.out.println("Press 1 to attack upper neighbour");
        }
        if(bottomNeighbour(x, y, player)){
            System.out.println("Press 2 to attack bottom neighbour");
        }
        if(leftNeighbour(x, y, player)){
            System.out.println("Press 3 to attack left neighbour");
        }
        if(rightNeighbour(x, y, player)){
            System.out.println("Press 4 to attack right neighbour");
        }
        System.out.println("Press 5 to abort attack");
    }
    /**
     * Ellenőrzi jobb oldali szomszéd 1 db dobókockával rendelkezik
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @return igazat ha van jobb oldali 1 darab dobókockájú csempe, ellenkező setben hamis
     */
    public boolean rightNeigbourNumberIsOne(int x, int y){
        if(tileBoard[x][y+1].getDiceNumber()==1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Ellenőrzi bal oldali szomszéd 1 db dobókockával rendelkezik
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @return igazat ha van bal oldali 1 darab dobókockájú csempe, ellenkező esetben hamis
     */
    public boolean leftNeigbourNumberIsOne(int x, int y){
        if(tileBoard[x][y-1].getDiceNumber()==1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Ellenőrzi  felső szomszéd 1 db dobókockával rendelkezik
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @return igazat ha van felső 1 darab dobókockájú csempe, ellenkező esetben hamis
     */
    public boolean upperNeigbourNumberIsOne(int x, int y){
        if(tileBoard[x-1][y].getDiceNumber()==1){
            return true;
        }else{
            return false;
        }
    }
    /** 
     * Ellenőrzi alsó szomszéd 1 db dobókockával rendelkezik
     * @param x sor koordináta ahonnan ellenőrizze
     * @param y oszlop koordináta ahonnan ellenőrizze
     * @return igazat ha van alsó 1 darab dobókockájú csempe, ellenkező esetben hamis
     */
    public boolean bottomNeigbourNumberIsOne(int x, int y){
        if(tileBoard[x+1][y].getDiceNumber()==1){
            return true;
        }else{
            return false;
        }
    }
}
