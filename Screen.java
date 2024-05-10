import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;


public class Screen extends JPanel {
	
    // Instance variables here
    Square[][] grid = new Square[10][10];
    // Constructor
	public Screen() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = new Square(50 * j + 50, 50 * i + 50);
			}
		}
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


		// Call your drawing methods from here. 
		// You need to pass 'g'
		for (Square[] row : grid) {
			for (Square each : row) {
				each.draw(g);
			}
		}

	}


	// You will have one method for each different element that you draw.
	private void drawSomething(Graphics g) {
		// Code that draws something
	}
}

