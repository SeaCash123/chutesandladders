import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class Screen extends JPanel implements ActionListener {
	
    // Instance variables here
    Square[][] grid = new Square[10][10];
	private int turn, dice;
	private JButton roll, play;
	private Boolean start, pieceMoving;
	private Font myFont = new Font("Serif", Font.BOLD, 45);
	Pieces[] playerList = new Pieces[4];
	private int newRow, newCol, pastRow, pastCol;
	


    // Constructor
	public Screen() {
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = new Square(65 * j + 100, 65 * i + 100);
				
			}
		}
		int count = 1;
		for (int i = grid.length-1;  i >= 0; i--) {
			if (i%2 == 1) {
				for (int j = 0; j < grid.length; j++) {
					grid[i][j].setCount(count);
					count++;
				}
			}
			else {
				for (int j = grid.length-1; j >=0; j--) {
					grid[i][j].setCount(count);
					count++;
				}
			}
		}

		start = true;
		pieceMoving = false;
		roll = new JButton("ROLL DICE");
		play = new JButton("START GAME");
		setLayout(null);
		roll.setBounds(800,100, 200, 100);
		play.setBounds(500, 400, 200, 100);
		add(play);
		add(roll);
		roll.setVisible(false);
		roll.addActionListener(this);
		play.addActionListener(this);

		playerList[3] = new Pieces(50, 65 * 9+115, Color.orange);
		playerList[2] = new Pieces(50, 65*9+115, Color.red);
		playerList[1] = new Pieces(50, 65*9+115, Color.blue);
		playerList[0] = new Pieces(50, 65*9+115, Color.green);
		

		
    }

    // You do not have to change this.
	@Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1920,1080);
	}

    // Do not remove the super call. Add all drawing calls here
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color black = new Color(255,255,255);
		if (dice > 0) {
			g.drawString("You rolled a " + dice, 940, 250);
		}
		g.drawString("Current Player: " + (turn + 1), 800, 250);
		Color white = new Color(0,0,0);
		if (start) {
			roll.setVisible(false);
			play.setVisible(true);
		}
		else {
			roll.setVisible(true);
			play.setVisible(false);
		}
		


		// Call your drawing methods from here. 
		// You need to pass 'g'
		for (Square[] r : grid){
			for (Square s : r){
				s.draw(g);
			}
		}

		for (Pieces each : playerList) {
			each.draw(g);
		}

		g.setColor(Color.black);
		int count = 1;
		for (int i = grid.length-1; i>=0; i--) {
			if (i % 2 == 1) {
				for (int j = 0; j < grid[0].length; j++) {
					g.drawString(count + "",65 * j + 125, 65 * i + 135);
					count++;
				}
			}
			else {
				for (int j = grid[i].length-1; j>=0; j--) {
					g.drawString(count + "",65 * j + 125, 65 * i + 135);
					count++;
				}
			}
		}

		if (start){
			g.setColor(new Color(255,255,255));
			g.fillRect(0,0,1920,1080);
			g.setColor(new Color(0,0,0));
			g.setFont(myFont);
			g.drawString("Snakes and Ladders!", 400, 200);
		}



	}

	private void move(int dice) {
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
					playerList[turn].changeX(65 * j +115);
					playerList[turn].changeY(65 * i + 115);
					break;
				}
			}
		}



	}
	private int rollDice(){
		int num = (int)(Math.random()*6 + 1);
		move(num);
		turn++;
		if (turn==4){
			turn = 0;
		}
		return num;
	}


	// You will have one method for each different element that you draw.
	private void drawSomething(Graphics g) {
		// Code that draws something
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == roll) {
			dice = rollDice();
		}

		if (event.getSource() == play) {
			start = false;
		}
		// You MUST have this line to repaint the screen
		repaint();

	}

	public void animate() {
		while(true){
            //wait for .01 second
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            // You add code here. moveRectangle is called continously to simulate movement
            
            /* Repaint the graphics drawn. You MUST have this in your code
              because each time an object is moved, the panel needs to 
              be updated. */
            repaint();
        }
	}

}

