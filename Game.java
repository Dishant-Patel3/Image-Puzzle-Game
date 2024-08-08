import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {

    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("GOAT.png");

    private Puzzle app;
    private BufferedImage image;
    private BufferedImage image1;    
    private Image image2;
    private MyButton lastButton;
    private int width, width1, height, rows, cols, numbuttons;  
    private List<MyButton> buttons;
    private List<Point> solution;

    public Game(Puzzle puzzle, int x) {
        app = puzzle;
        initUI(x);
    }

    private void initUI(int x) {
        rows = x;
        cols = x;
        numbuttons = x * x;
        width1 = 400;
        solution = new ArrayList<>();
        buttons = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                solution.add(new Point(i, j));
            }
        }
        setLayout(new GridLayout(x, x, 0, 0));
        setSize(400, 500);
        setBounds(525, 85, 400, 500);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.gray));
        label.setIcon(img);
        label.setForeground(Color.WHITE);
        label.setBounds(50, 85, 400, 500);
        add(label);

        try {
            image = loadImage();
            int h = getNewHeight(image.getWidth(), image.getHeight());
            image1 = resizeImage(image, width1, h, BufferedImage.TYPE_INT_ARGB);

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        width = image1.getWidth(null);
        height = image1.getHeight(null);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                image2 = createImage(new FilteredImageSource(image1.getSource(), new CropImageFilter(j * width / x, i * height / x, (width / x), height / x)));
                MyButton button = new MyButton(image2);
                button.putClientProperty("position", new Point(i, j));
                if (i == (x-1) && j == (x-1)) {
                    lastButton = new MyButton();
                    lastButton.setBorderPainted(false);
                    lastButton.setContentAreaFilled(false);
                    lastButton.setLastButton();
                    lastButton.putClientProperty("position", new Point(i, j));
                } else {
                    buttons.add(button);
                }
            }
        }

        Collections.shuffle(buttons);
        buttons.add(lastButton);

        for (int i = 0; i < numbuttons; i++) {
            MyButton btn = buttons.get(i);
            add(btn);
            btn.setBorder(BorderFactory.createLineBorder(Color.gray));
            btn.addActionListener(new ClickAction());
        }
    }

    public int getNewHeight(int w, int h) {
        double ratio = width1 / (double) w;
        int h1 = (int) (h * ratio);
        return h1;
    }

    public BufferedImage loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("GOAT.png"));
        return image;
    }

    public BufferedImage resizeImage(BufferedImage image, int width, int height, int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public class ClickAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkButton(e);
            checkSolution();
        }
        public void checkButton(ActionEvent e) {
            int x = 0;
            for (MyButton button : buttons) {
                if (button.isLastButton()) {
                    x = buttons.indexOf(button);
                }
            }
            JButton button = (JButton) e.getSource();
            int y = buttons.indexOf(button);
            if ((y - 1 == x) || (y + 1 == x) || (y - 3 == x) || (y + 3 == x)) {
                Collections.swap(buttons, y, x);
                updateButtons();
            }
        }
        public void updateButtons() {
            removeAll();
            for (JComponent btn : buttons) {
                add(btn);
            }
            validate();
        }
    }

    public void checkSolution() {
        List<Point> current = new ArrayList<>();
        for (JComponent btn : buttons) {
            current.add((Point) btn.getClientProperty("position"));
        } if (compareList(solution, current)) {
            label.setIcon(img);
            label.setForeground(Color.WHITE);
            label.setBounds(0, 0, 400, 500);
            add(label);
        }
    }

    public static boolean compareList(List list1, List list2) {
        return list1.toString().contentEquals(list2.toString());
    }
}

class MyButton extends JButton {

    private boolean isLastButton;

    public MyButton() {
        super();
        initUI();
    }

    public MyButton(Image image) {
        super(new ImageIcon(image));
        initUI();
    }

    private void initUI() {
        isLastButton = false;
        BorderFactory.createLineBorder(Color.gray);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.yellow));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.gray));
            }
        });
    }

    public void setLastButton() {
        isLastButton = true;
    }

    public boolean isLastButton() {
        return isLastButton;
    }
}
