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
    private int turn, dice;
    private JButton roll, play;
    private Boolean start;
    private Font myFont = new Font("Serif", Font.BOLD, 45);
    Pieces[] playerList = new Pieces[4];
	private Game game;

    // Constructor
    public Screen() {

		game = new Game();

        start = true;
        roll = new JButton("ROLL DICE");
        play = new JButton("START GAME");
        setLayout(null);
        roll.setBounds(800, 100, 200, 100);
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
        // Sets the size of the panel
        return new Dimension(1920, 1080);
    }

    // Do not remove the super call. Add all drawing calls here
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dice > 0) {
            g.drawString("Player " + game.getTurn() + " rolled a " + dice, 800, 250);
        }
        g.drawString("Current Player Rolling: " + (game.getTurn() + 1), 800, 300);

        if (start) {
            roll.setVisible(false);
            play.setVisible(true);
        } else {
            roll.setVisible(true);
            play.setVisible(false);
        }

        // Call your drawing methods from here. 
        // You need to pass 'g'
		game.drawBoard(g);
       

        if (start) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, 1920, 1080);
            g.setColor(new Color(0, 0, 0));
            g.setFont(myFont);
            g.drawString("Snakes and Ladders!", 400, 200);
        }
    }

    

    

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == roll) {
            dice = game.rollDice();
        }

        if (event.getSource() == play) {
            start = false;
        }
        // You MUST have this line to repaint the screen
        repaint();
    }

    public void animate() {
        while (true) {
            // wait for .01 second
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            game.animate();

            repaint();
        }
    }

}
