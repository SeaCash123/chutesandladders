import java.awt.Color;
import java.awt.Graphics;

//Squares for the board grid
public class Square {
    int x, y, count;

    //constructor
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        
    }
    //sets square num
    public void setCount(int count) {
        this.count = count;  
    }
    //returns square num
    public int getCount() {
        return count;
    }
}