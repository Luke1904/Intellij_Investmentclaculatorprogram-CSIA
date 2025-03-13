import javax.accessibility.Accessible;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

// The Line interface extends multiple interfaces to provide additional functionality
public interface Line extends ImageObserver, MenuContainer, Serializable, Accessible {

    /**
     * This method is responsible for painting or drawing the component.
     * Classes implementing this interface must provide their own implementation
     * of this method to define how the component should be rendered.
     *
     * @param g The Graphics object used to draw the component.
     */
    void paintComponent(Graphics g);
}