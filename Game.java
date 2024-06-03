import java.awt.Color;
import java.awt.Graphics;

public class Game {


    //Instance variables
    private Square[][] grid;
    private int turn, dice;
    private Pieces[] playerList;
    private int newRow, newCol;
    private boolean isAnimating;
    private int targetX, targetY;
    private Pieces currentPiece;
    private GameObject[] snakesandladders;
    private boolean touching, end;

    public Game() {

        grid = new Square[10][10];

        //Creates board grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Square(65 * j + 100, 65 * i + 100);
            }
        }

        //Sets each grid object to a number
        int count = 1;
        for (int i = grid.length - 1; i >= 0; i--) {
            if (i % 2 == 1) {
                for (int j = 0; j < grid.length; j++) {
                    grid[i][j].setCount(count);
                    count++;
                }
            } else {
                for (int j = grid.length - 1; j >= 0; j--) {
                    grid[i][j].setCount(count);
                    count++;
                }
            }
        }
        

        

        //defining some instance variables
        touching = false;
        end = false;
        playerList = new Pieces[4];
        isAnimating = false;

        //creates all the player objects
        playerList[3] = new Pieces(50, 580, Color.orange, 3);
        playerList[2] = new Pieces(50, 620, Color.red, 2);
        playerList[1] = new Pieces(50, 660, Color.blue,1 );
        playerList[0] = new Pieces(50, 700, Color.green, 0);

        //creates all the snake and ladder objects
        snakesandladders = new GameObject[16];
        snakesandladders[0] = new Ladder(9,0,6,2,38);
        snakesandladders[1] = new Ladder(9,3,8,6,14);
        snakesandladders[2] = new Ladder(9,8,6,9,31);
        snakesandladders[3] = new Ladder(7,0,5,1,42);
        snakesandladders[4] = new Ladder(7,7,1,3,84);
        snakesandladders[5] = new Ladder(4,9,3,6,67);
        snakesandladders[6] = new Ladder(2,8,0,9,91);
        snakesandladders[7] = new Ladder(2,0,0,1,99);
        snakesandladders[8] = new Snake(8,3,9,6,7);
        snakesandladders[9] = new Snake(3,1,8,1,19);
        snakesandladders[10] = new Snake(3,3,4,0,60);
        snakesandladders[11] = new Snake(4,6,6,6,34);
        snakesandladders[12] = new Snake(1,6,6,4,36);
        snakesandladders[13] = new Snake(0,7,2,7,73);
        snakesandladders[14] = new Snake(0,5,2,5,75);
        snakesandladders[15] = new Snake(0,2,2,1,79);
        


    }
    //restarts game when restart button is clicked
    public void restart() {
        for (Pieces each : playerList) {
            each.changePos(0);
        }

        playerList[0].changeX(50);
        playerList[1].changeX(50);
        playerList[2].changeX(50);
        playerList[3].changeX(50);

        playerList[0].changeY(700);
        playerList[1].changeY(660);
        playerList[2].changeY(620);
        playerList[3].changeY(580);
        
        isAnimating = false;
        end = false;
        turn = 0;
    }
    //returns the current turn
    public int getTurn() {
        return turn;
    }
    //returns if the game is animating
    public boolean isAnimating() {
        return isAnimating;
    }
    //returns if the game is over
    public boolean getEnd() {
        return end;
    }
    
    //draws players
    public void drawPlayers(Graphics g) {

        for (Pieces each : playerList) {
            each.draw(g);
        }
    
    }
    //changes the targetX and targetY of the player after a player rolls the dice
    private void move(int dice) {
        if (isAnimating) {
            return; // Prevent new moves while animating
        }
        //changes the player position to the dice
        int pastPos = playerList[turn].getPos();
        int newPos = pastPos + dice;
        //checks if player rolled over a 100, and sets column and row to square 100
        if (newPos >= 100) {
            playerList[turn].changePos(100);
            newRow = 0;
            newCol = 0;
        }
        //if not over 100, sets the column and row to the square with the number the same as newPos
        else {
            playerList[turn].changePos(newPos);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].getCount() == newPos) {
                        newRow = i;
                        newCol = j;
                        break;
                    }
                }
            }
        }

        
        //sets the target for the animation to the x and y of the middle of the grid
        targetX = 65 * newCol + 115;
        targetY = 65 * newRow + 115;



		//starts the animation
        currentPiece = playerList[turn];
        isAnimating = true;
    }

    //returns a random number and runs the move function
    public int rollDice() {
        int num = (int) (Math.random() * 6 + 1);
        move(num);
        return num;
    }
    //animates the player movement
    public void animate() {
        if (isAnimating) {
            //changes the x and y to move torwards the targetX and targetY
                int currentX = currentPiece.getX();
                int currentY = currentPiece.getY();
                if (currentX < targetX) {
                    currentX +=5 ;
                } else if (currentX > targetX ) {
                    currentX -=5;
                }
                if (currentY < targetY) {
                    currentY +=5;
                } else if (currentY > targetY) {
                    currentY -=5;
                }
                currentPiece.changeX(currentX);
                currentPiece.changeY(currentY);
                //checks if it arrives at the targetX and targetY
                if (currentX == targetX && currentY == targetY) {
                    //sees if it has reached the end and sets the game to over
                    if (playerList[turn].getPos() == 100) {
                        end = true;
                    }
                    //if not at end, do this
                    else {   
                        //checks if its at a snake or ladder 
                        for (GameObject each : snakesandladders) {
                            if (each.check(newRow, newCol)) {
                                //sets targetX and targetY to either top of a ladder or bottom of a snake
                                touching = true;
                                targetX = 65 * each.giveX() +115;
                                targetY = 65 * each.giveY() + 115;
                                newRow = each.giveY();
                                newCol = each.giveX();
                                playerList[turn].changePos(each.getPos());
                                break;
                            }
                            else {
                                touching = false;
                            }
                        }
                        //if not at a ladder, stops animation and moves to the next player
                        if (!touching) {
                            isAnimating = false;
                            turn = (turn + 1) % 4;
                        }
                    }
                }
            }
    }



}