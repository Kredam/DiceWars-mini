import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUInterface extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 8622928671902465370L;
    private static int HEIGHT = 1286;
    private static int WIDTH = 829;

    public GUInterface(){
        this.setTitle("Dice Wars");
        this.setSize(HEIGHT,WIDTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
    