import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.lang.Math;

public class FlyweightTest extends JFrame {

    JButton startDrawing;
    int windowWidth = 1300;
    int windowHeight = 400;

    Color[] shapeColor = {
        Color.orange,
        Color.red,
        Color.yellow,
        Color.blue,
        Color.pink,
        Color.cyan,
        Color.magenta,
        Color.black,
        Color.gray
    };



    public static void main(String[] args) {
        new FlyweightTest();
    }

    public FlyweightTest() {
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Flyweight Test");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        final JPanel drawingPanel = new JPanel();
        startDrawing = new JButton("Draw Stuff");

        contentPane.add(drawingPanel, BorderLayout.CENTER);
        contentPane.add(startDrawing, BorderLayout.SOUTH);

        startDrawing.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    Graphics g = drawingPanel.getGraphics();
                    long startTime = System.currentTimeMillis();

                    for (int i=0; i < 100000; ++i) {
                        //g.setColor(getRandColor());
                        //g.fillRect(getRandX(), getRandY(), getRandX(), getRandY());
                        MyRect r = new MyRect(getRandColor(), getRandX(), getRandY(), getRandX(), getRandY());
                        r.draw(g);
                    }

                    long endTime = System.currentTimeMillis();
                    System.out.println("That took" + (endTime - startTime) + "ms");
                }
            });
        this.add(contentPane);
        this.setVisible(true);
    }

    private Color getRandColor() {
        Random gen = new Random();
        int i = gen.nextInt(9);
        return shapeColor[i];
    }

    private int getRandX() {
        return (int)(Math.random() * windowWidth);
    }
    private int getRandY() {
        return (int)(Math.random() * windowHeight);
    }

}