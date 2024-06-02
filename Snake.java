


public class Snake extends GameObject {

    Sound sound;

    public Snake(int topRow, int topCol, int bottomRow, int bottomCol, int pos) {
        super(bottomRow, bottomCol, topRow, topCol, pos);
        sound = new Sound();
    }
    //checks if player is at top of snake
    public boolean check(int newRow, int newCol) {
        if (super.getTopRow() == newRow && super.getTopCol() == newCol) {
            sound.playSnakeSound();
            return true;
        }
        return false;
    }
    //gives col of bottom of snake
    public int giveX() {
        return super.getBottomCol();
    }
    //gives row of bottom of snake
    public int giveY() {
        return super.getBottomRow();
    }
}
