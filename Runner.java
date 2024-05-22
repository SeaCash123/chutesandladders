import javax.swing.JFrame;

// Runner that can be used with JPanel Graphics
public class Runner {
	public static void main( String args[] ) {
        
        // Create the frame object. Give it a title appropriate to the application
        JFrame frame = new JFrame("");

        //Create the JPanel object and add it to the frame
        Screen canvas = new Screen();

        // You must have all of these calls
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        canvas.animate();

        // You probably will add more code (Scanner, etc.) depending on the assignment.

    }
}

