public class Ladder extends GameObject {
    Sound sound;
    public Ladder(int bottomRow, int bottomCol, int topRow, int topCol, int pos) {
        super(bottomRow, bottomCol, topRow, topCol, pos);
        sound = new Sound();
    }
    //checks if player is at bottom of ladder
    @Override
    public boolean check(int newRow, int newCol) {
        if (super.getBottomRow() == newRow && super.getBottomCol() == newCol) { 
            sound.playLadderSound();
            return true;
            
        }
        return false;
    }
    //gives col of top of ladder
    public int giveX() {
        return super.getTopCol();
    }
    //gives row of top of ladder
    public int giveY() {
        return super.getTopRow();
    }
}