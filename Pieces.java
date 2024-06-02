import java.awt.Color;
import java.awt.Graphics;

//Player pieces
public class Pieces {

    //Instance variables
    private int x, y, position, playerIndex;
    private Color c;
    private Boolean change;

    public Pieces(int x, int y, Color c, int playerIndex){
        this.x = x;
        this.y = y;
        this.c = c;
        position = 0;
        change = true;
        this.playerIndex = playerIndex;
    }

    //Draws the player
    public void draw(Graphics g){
        g.setColor(c);
        g.fillOval(x + playerIndex * 2,y, 30, 30);
        g.setColor(Color.black);
        g.drawOval(x + playerIndex * 2,y, 30, 30);
    }

    //changes x
    public void changeX(int x) {
        this.x = x;
    }
    //returns x
    public int getX() {
        return x;
    }
    //changes y
    public void changeY(int y) {
        this.y = y;
    }
    //returns y
    public int getY() {
        return y;
    }
    //changes position the board
    public void changePos(int x) {
        position = x;
    }
    //returns position on board
    public int getPos() {
        return position;
    }

   
     
        
        
    

}