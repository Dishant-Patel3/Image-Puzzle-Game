import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game1 extends JPanel {
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("GOAT.png");

    private Puzzle app;  
    
    public Game1(Puzzle puzzle) {
        app = puzzle;
        setLayout(null);
        setSize(400, 500);
        setBounds(50, 85, 400, 500);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
        label.setIcon(img);
        label.setForeground(Color.WHITE);
        label.setBounds(0, 0, 400, 500);
        add(label);
    }
}
