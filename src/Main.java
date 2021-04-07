public class Main implements Runnable {
    GUInterface gui = new GUInterface();
    public static void main(String[] args) {
        Main game = new Main();
        Thread thread = new Thread(game);
        thread.start();
    }

    public void run(){
        while(true){
            gui.repaint();
        }
    }
}
