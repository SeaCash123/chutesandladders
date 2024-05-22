import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Game {

    Square[][] grid = new Square[10][10];
    private int turn, dice;
    private Font myFont = new Font("Serif", Font.BOLD, 45);
    Pieces[] playerList = new Pieces[4];
    private int newRow, newCol, pastRow, pastCol;
    private boolean isAnimating = false;
    private int targetX, targetY;
    private Pieces currentPiece;

    public Game() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Square(65 * j + 100, 65 * i + 100);
            }
        }
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


        playerList[3] = new Pieces(50, 580, Color.orange, 3);
        playerList[2] = new Pieces(50, 620, Color.red, 2);
        playerList[1] = new Pieces(50, 660, Color.blue,1 );
        playerList[0] = new Pieces(50, 700, Color.green, 0);


    }

    public int getTurn() {
        return turn;
    }

    public void drawBoard(Graphics g) {
        for (Square[] r : grid) {
            for (Square s : r) {
                s.draw(g);
            }
        }

        for (Pieces each : playerList) {
            each.draw(g);
        }

        g.setColor(Color.black);
        int count = 1;
        for (int i = grid.length - 1; i >= 0; i--) {
            if (i % 2 == 1) {
                for (int j = 0; j < grid[0].length; j++) {
                    g.drawString(count + "", 65 * j + 125, 65 * i + 135);
                    count++;
                }
            } else {
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    g.drawString(count + "", 65 * j + 125, 65 * i + 135);
                    count++;
                }
            }
        }
    
    }

    public void move(int dice) {
        if (isAnimating || playerList[turn].getPos() >= 100) {
            return; // Prevent new moves while animating
        }
        int pastPos = playerList[turn].getPos();
        int newPos = pastPos + dice;
        playerList[turn].changePos(newPos);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getCount() == pastPos) {
                    pastRow = i;
                    pastCol = j;
                    break;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getCount() == newPos) {
                    newRow = i;
                    newCol = j;
                    break;
                }
            }
        }
		if (newPos > 100) {
			playerList[turn].changePos(pastPos);
			targetX = 65 * pastCol + 115;
			targetY = 65 * newRow + 115;
			
		}
		else if (newPos < 100) {
        targetX = 65 * newCol + 115;
        targetY = 65 * newRow + 115;
		}
		else if (newPos == 100) {
		targetX = 115;
		targetY = 115;
		}

		
        currentPiece = playerList[turn];
        isAnimating = true;
    }


    public int rollDice() {
        int num = (int) (Math.random() * 6 + 1);
        move(num);
        return num;
    }

    public void animate() {
        if (isAnimating) {
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
                if (currentX == targetX && currentY == targetY) {
                    isAnimating = false;
                    turn = (turn + 1) % 4; // Move to the next player
                }
            }
    }



}