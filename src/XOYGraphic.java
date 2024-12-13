import javax.swing.*;
import java.awt.*;

public class XOYGraphic extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = 100;
        int height = 200;

        // Calculate the center of the panel (origin point)
        int originX = width / 2;
        int originY = height / 2;

        // Draw X and Y axes
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, originY, width, originY); // X-axis
        g2d.drawLine(originX, 0, originX, height); // Y-axis

        // Draw arrows for axes
        g2d.fillPolygon(new int[]{width - 10, width, width - 10}, new int[]{originY - 5, originY, originY + 5}, 3); // X-axis arrow
        g2d.fillPolygon(new int[]{originX - 5, originX, originX + 5}, new int[]{10, 0, 10}, 3); // Y-axis arrow

        // Add labels for axes
        g2d.drawString("X", width - 15, originY - 10);
        g2d.drawString("Y", originX + 10, 15);

        // Draw grid lines and labels
        g2d.setColor(Color.LIGHT_GRAY);
        for (int y = originY + 50; y < height; y += 50) {
            g2d.drawLine(0, y, width, y); // Horizontal grid line
            g2d.drawString(String.valueOf((originY - y) / 50), originX + 5, y); // Y-axis negative labels
        }
        for (int y = originY - 50; y > 0; y -= 50) {
            g2d.drawLine(0, y, width, y); // Horizontal grid line
            g2d.drawString(String.valueOf((originY - y) / 50), originX + 5, y); // Y-axis positive labels
        }

        // Plot a sample function (e.g., y = x^2 / 100)
        g2d.setColor(Color.RED);
        for (int x = -originX; x < originX; x++) {
            int y = (x * x) / 100; // Example: y = x^2 / 100
            int screenX = originX + x;
            int screenY = originY - y;
            if (screenX > 0 && screenY > 0 && screenY < height) {
                g2d.fillRect(screenX, screenY, 2, 2);
            }
        }
    }


    }



