
//Abstract superclass for the snakes and ladders
public abstract class GameObject {
    //instance variables
    private int topRow, topCol, bottomCol, bottomRow, pos;
    
    //constructor
    public GameObject(int bottomRow, int bottomCol, int topRow, int topCol, int pos) {
        this.bottomRow = bottomRow;
        this.bottomCol = bottomCol;
        this.topRow = topRow;
        this.topCol = topCol;
        this.pos = pos;
    }
    //check if at snake or ladder
    public abstract boolean check(int newRow, int newCol);
    
    //gives col of snake or ladder
    public abstract int giveX();

    //gives row of snake or ladder
    public abstract int giveY();

    //returns top row of snake or ladder
    public int getTopRow() {
        return topRow;
    }
    //returns top col of snake or ladder
    public int getTopCol() {
        return topCol;
    }
    //returns bottom col of snake or ladder
    public int getBottomCol() {
        return bottomCol;
    }

    //returns bottom row of snake or ladder
    public int getBottomRow() {
        return bottomRow;
    }
    //returns pos of top of ladder or bottom of snake
    public int getPos() {
        return pos;
    }


    }

