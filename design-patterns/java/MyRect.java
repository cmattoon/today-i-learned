import java.awt.*;

public class MyRect {
    private Color color;
    private int x,y,x2,y2;

    public MyRect(Color color) {
        this.color = color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public void draw(Graphics g, int upperX, int upperY, int lowerX, int lowerY) {
        this.x = upperX;
        this.y = upperY;
        this.x2 = lowerX;
        this.y2 = lowerY;
        g.setColor(color);
        g.fillRect(x,y,x2,y2);
    }
}