package gamearea;

import players.Players;

public class NeutralTile extends Tiles{

    /**
     * 
     * @param x Pálya melyik sora
     * @param y Pálya melyik oszlopa
     */
    public NeutralTile(int x, int y, Players owner) {
        super(x, y, owner);
    }
    
}
