import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int spacing = 4;
    public void paintComponent(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(0, 0, GUInterface.getHEIGHT(), GUInterface.getWIDTH());
        g.setColor(Color.black);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.fillRect(spacing + i * 80, spacing+j*80, 80-2*spacing, 80-2*spacing);
            }
        }
    }
}