package Lab3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Lab3_1 {
    //Задание 1
    static class MatchApp extends JFrame {
        private int milanScore = 0;
        private int madridScore = 0;
        private String lastScorer = "N/A";

        JLabel resultLabel = new JLabel("Result: 0 X 0", SwingConstants.CENTER);
        JLabel lastScorerLabel = new JLabel("Last Scorer: N/A", SwingConstants.CENTER);
        JLabel winnerLabel = new JLabel("Winner: DRAW", SwingConstants.CENTER);
        JButton milanBtn = new JButton("AC Milan");
        JButton madridBtn = new JButton("Real Madrid");

        public MatchApp() {
            super("Match Scoreboard");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //закрывает окно а не программу
            setSize(300, 200);
            setLayout(new GridLayout(3, 2));

            Font font = new Font("Arial", Font.BOLD, 14);
            resultLabel.setFont(font);
            lastScorerLabel.setFont(font);
            winnerLabel.setFont(font);

            add(milanBtn);
            add(resultLabel);
            add(madridBtn);
            add(lastScorerLabel);
            add(new JLabel(""));
            add(winnerLabel);

            milanBtn.addActionListener(e -> { // это стрелочная функция (лямбда)
                milanScore++;
                lastScorer = "AC Milan";
                updateScore();
            });

            madridBtn.addActionListener(e -> {
                madridScore++;
                lastScorer = "Real Madrid";
                updateScore();
            });

            setLocationRelativeTo(null); //окно в центре экрана
        }

        private void updateScore() {
            resultLabel.setText("Result: " + milanScore + " X " + madridScore);
            lastScorerLabel.setText("Last Scorer: " + lastScorer);

            if (milanScore > madridScore) winnerLabel.setText("Winner: AC Milan");
            else if (madridScore > milanScore) winnerLabel.setText("Winner: Real Madrid");
            else winnerLabel.setText("Winner: DRAW");
        }

        public static void run() {
            new MatchApp().setVisible(true);
        }
    }

    //Задание 2
    static abstract class Shape {
        int x, y;
        Color color;
        public Shape(int x, int y, Color c) { this.x = x; this.y = y; this.color = c; }
        abstract void draw(Graphics g);
    }

    static class CircleShape extends Shape {
        int radius;
        public CircleShape(int x, int y, Color c, int r) { super(x, y, c); this.radius = r; }
        @Override void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }

    static class RectShape extends Shape {
        int width, height;
        public RectShape(int x, int y, Color c, int w, int h) { super(x, y, c); this.width = w; this.height = h; }
        @Override void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    static class RandomShapesPanel extends JPanel {
        public RandomShapesPanel() {
            setBackground(Color.WHITE);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Random rand = new Random();
            for (int i = 0; i < 20; i++) {
                int x = rand.nextInt(getWidth() - 50);
                int y = rand.nextInt(getHeight() - 50);
                Color c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                Shape s;
                if (rand.nextBoolean()) {
                    s = new CircleShape(x, y, c, 20 + rand.nextInt(20));
                } else {
                    s = new RectShape(x, y, c, 30 + rand.nextInt(30), 30 + rand.nextInt(30));
                }
                s.draw(g); //полиморфизм
            }
        }
    }

    static void task2() {
        JFrame frame = new JFrame("Random Shapes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new RandomShapesPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Задание 3
    static class ImagePanel extends JPanel {
        Image img;
        public ImagePanel(String path) {
            img = new ImageIcon(path).getImage();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null && img.getWidth(null) > 0) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            } else {
                g.drawString("Картинка не найдена по указанному пути", 50, 50);
            }
        }
    }

    static void task3(String imagePath) {
        JFrame frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new ImagePanel(imagePath));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Задание 4
    static class AnimationPanel extends JPanel implements ActionListener { //реализует
        int x = 0, y = 100;
        int xSpeed = 5, ySpeed = 3;
        Timer timer;

        public AnimationPanel() {
            setBackground(Color.BLACK);
            timer = new Timer(30, this);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(x, y, 50, 50);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            x += xSpeed;
            y += ySpeed;

            if (x <= 0 || x >= getWidth() - 50) xSpeed = -xSpeed;
            if (y <= 0 || y >= getHeight() - 50) ySpeed = -ySpeed;

            repaint();
        }
    }

    static void task4() {
        JFrame frame = new JFrame("Animation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new AnimationPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void run() {
        System.out.println("\nПРАКТИКА 3.1");
        System.out.println("Открываются окна приложения...");

        MatchApp.run();

        SwingUtilities.invokeLater(() -> task2());

        SwingUtilities.invokeLater(() -> task3("image.jpg"));

        SwingUtilities.invokeLater(() -> task4());
    }
}
