import javax.swing.*;
import java.awt.*;

public class LinePanel extends JPanel implements Line {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        g2d.setColor(Color.BLACK);
        g2d.drawLine(30, height / 2 + 30, width - 30, height / 2 + 30);

    }
}
