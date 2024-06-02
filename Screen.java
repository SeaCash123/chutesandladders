//Imports
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends JPanel implements ActionListener {

    // Instance variables here
    private int dice;
    private JButton roll, play, restart;
    private Boolean start;
    private Font myFont = new Font("Serif", Font.BOLD, 45);
    private Font smallFont = new Font("Serif", Font.BOLD, 18);
    Pieces[] playerList = new Pieces[4];
	private Game game;
	private int displayTurn;
    private Sound sound;
    private BufferedImage board;

    // Constructor
    public Screen() {

		game = new Game();
        sound = new Sound();

        //sets up buttons
        start = true;
        roll = new JButton("ROLL DICE");
        play = new JButton("START GAME");
        restart = new JButton("RESTART");
        setLayout(null);
        roll.setBounds(800, 100, 200, 100);
        play.setBounds(500, 400, 200, 100);
        restart.setBounds(500, 400, 200, 100);
        add(play);
        add(roll);
        add(restart);
        roll.setVisible(false);
        restart.setVisible(false);
        roll.addActionListener(this);
        play.addActionListener(this);
        restart.addActionListener(this);

        try {
            board = ImageIO.read(new File("board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

       
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

        //Gets turn of previous player

		if (game.getTurn() == 0) {
			displayTurn =4;
		}
		else {
			displayTurn = game.getTurn();
		}

        //displays information of the dice rolling
        if (dice > 0 && !game.isAnimating()) {
            g.drawString("Player " + displayTurn + " rolled a " + dice, 800, 250);
        }
        g.drawString("Current Player Rolling: " + (game.getTurn() + 1), 800, 300);

        //shows buttons based on what state the game is in
        if (start) {
            roll.setVisible(false);
            play.setVisible(true);
        } else if (game.getEnd()) { //when game ends
            roll.setVisible(false);
            restart.setVisible(true);
        } else {
            roll.setVisible(true);
            play.setVisible(false);
            restart.setVisible(false);
        }

        //draws the game board and players
        g.drawImage(board, 100, 100, null);
		game.drawPlayers(g);
       

        //start screen
        if (start) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, 1920, 1080);
            g.setColor(new Color(0, 0, 0));
            g.setFont(myFont);
            g.drawString("Snakes and Ladders!", 400, 200);
            g.setFont(smallFont);
            g.drawString("In this game there are four players. Roll the dice to move your piece.", 100, 550);
            g.drawString("There are snakes and ladders on the board.", 100, 600);
            g.drawString("Landing on the bottom of a ladder will move you up.", 100, 650);
            g.drawString("Landing on the head of a snake will move you to its tail.", 100, 700);
            g.drawString("The end goal is to reach the 100th square.", 100, 750);
        }

        //end screen
        if (game.getEnd()) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, 1920, 1080);
            g.setColor(new Color(0, 0, 0));
            g.setFont(myFont);
            g.drawString("Player " + (game.getTurn() + 1) + " wins!", 400, 200);
        }

        
    }


    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == roll) { 
        //dice button which rolls the dice            
        dice = game.rollDice();
        }

        if (event.getSource() == play) { //starts the game
            sound.playStartSound();
            start = false;
        }
        if (event.getSource() == restart) {
            sound.playStartSound();
            game.restart();
        // resets the game and restarts            
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
            //runs the animation code for the game
            game.animate();

            repaint();
        }
    }

}
