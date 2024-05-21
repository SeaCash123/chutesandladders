import java.awt.Color;
import java.awt.Graphics;

public class Pieces {

    private int x, y;
    private Color c;

    public Pieces(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public void draw(Graphics g){
        g.setColor(c);
        g.fillOval(x,y, 10, 10);
    }


    public void changeX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void changeY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

}