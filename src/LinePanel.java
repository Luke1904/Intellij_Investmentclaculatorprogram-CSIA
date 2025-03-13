import javax.swing.*;
import java.awt.*;

// LinePanel is a custom JPanel that implements the Line interface
public class LinePanel extends JPanel implements Line {

    /**
     * Overrides the paintComponent method to provide custom drawing behavior.
     * This method is automatically called whenever the panel needs to be repainted.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the parent class's paintComponent method to ensure proper rendering

        // Cast the Graphics object to Graphics2D for more advanced drawing operations
        Graphics2D g2d = (Graphics2D) g;

        // Get the width and height of the panel
        int width = getWidth();
        int height = getHeight();

        // Set the drawing color to black
        g2d.setColor(Color.BLACK);

        // Draw a horizontal line across the panel
        // The line starts at (30, height / 2 + 30) and ends at (width - 30, height / 2 + 30)
        g2d.drawLine(30, height / 2 + 30, width - 30, height / 2 + 30);
    }
}