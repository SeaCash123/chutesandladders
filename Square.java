import java.awt.Color;
import java.awt.Graphics;

public class Square {
    private String type;
    int x, y, count;

    public Square(int x, int y) {
        type = "";
        this.x = x;
        this.y = y;
        
    }
    public void setCount(int count) {
        this.count = count;  
    }
    public int getCount() {
        return count;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.drawRect(x,y,65,65);
    }
}