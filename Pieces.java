import java.awt.Color;
import java.awt.Graphics;

public class Pieces {

    private int x, y, position;
    private Color c;
    private Boolean change;

    public Pieces(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.c = c;
        position = 0;
        change = true;
    }

    public void draw(Graphics g){
        g.setColor(c);
        g.fillOval(x,y, 30, 30);
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

    public void changePos(int x) {
        position = x;
    }

    public int getPos() {
        return position;
    }

   
     
        
        
    

}