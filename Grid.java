import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Grid extends JPanel {
    JButton button1 = new JButton("");
    JButton button2 = new JButton("");
    JButton button3 = new JButton("");
    JButton button4 = new JButton("");
    JButton button5 = new JButton("");
    JButton button6 = new JButton("");
    ImageIcon img = new ImageIcon("GOAT.png");
    ImageIcon img1 = new ImageIcon("1.png");
    ImageIcon img2 = new ImageIcon("2.png");
    ImageIcon img3 = new ImageIcon("3.png");
    ImageIcon img4 = new ImageIcon("4.png");
    ImageIcon img5 = new ImageIcon("5.png");
    ImageIcon img6 = new ImageIcon("6.png");

    private Puzzle app;

    public Grid(Puzzle puzzle) {
        app = puzzle;
        setLayout(null);
        setSize(1000, 700);
        setBounds(0, 0, 1000, 700);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
        button1.setBounds(150, 100, 250, 70);
        button1.setIcon(img1);
        button1.setBackground(Color.WHITE);
        add(button1);
        button2.setBounds(550, 100, 250, 70);
        button2.setIcon(img2);
        button2.setBackground(Color.WHITE);
        add(button2);
        button3.setBounds(150, 300, 250, 70);
        button3.setIcon(img3);
        button3.setBackground(Color.WHITE);
        add(button3);
        button4.setBounds(550, 300, 250, 70);
        button4.setIcon(img4);
        button4.setBackground(Color.WHITE);
        add(button4);
        button5.setBounds(150, 530, 250, 70);
        button5.setIcon(img5);
        button5.setBackground(Color.WHITE);
        add(button5);
        button6.setBounds(550, 530, 250, 70);
        button6.setIcon(img6);
        button6.setBackground(Color.WHITE);
        add(button6);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.pageClick(1);

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.pageClick(2);

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.pageClick(3);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.pageClick(4);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.pageClick(5);
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.pageClick(6);
            }
        });
    }
}
