import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Puzzle extends JFrame {
    ImageIcon img = new ImageIcon("GOAT.png");

    private Game game;
    private Game1 game1;
    private Grid grid;
    private int num;

    public static void main(String[] args) {
        Puzzle win = new Puzzle();
    }

    public Puzzle() {
        setTitle("Dishant Patel - Puzzle");
        setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setIconImage(img.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        num = 0;
        grid = new Grid(this);
        add(grid);        
    }

    public void pageClick(int x) {
        num = x;
        game = new Game(this, x);
        game1 = new Game1(this);
        add(game);
        add(game1);
        grid.setVisible(false);
        game.setVisible(true);
        game1.setVisible(true);
    }
}