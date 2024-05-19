import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class Screen extends JPanel implements ActionListener {
	
    // Instance variables here
    Square[][] grid = new Square[10][10];
	private int turn, dice;
	private JButton roll, play;
	private Boolean start;
	private Font myFont = new Font("Serif", Font.BOLD, 45);


    // Constructor
	public Screen() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = new Square(50 * j + 100, 50 * i + 100);
			}
		}
		start = true;
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
		g.drawString("You rolled a " + dice, 940, 250);
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

		if (start){
			g.setColor(new Color(255,255,255));
			g.fillRect(0,0,1920,1080);
			g.setColor(new Color(0,0,0));
			g.setFont(myFont);
			g.drawString("Snakes and Ladders!", 400, 200);
		}



	}

	private int rollDice(){
		turn++;
		if (turn==4){
			turn = 0;
		}
		return (int)(Math.random()*6 + 1);
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
}

