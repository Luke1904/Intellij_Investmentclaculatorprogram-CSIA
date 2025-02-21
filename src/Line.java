import javax.accessibility.Accessible;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public interface Line extends ImageObserver, MenuContainer, Serializable, Accessible {
    void paintComponent(Graphics g);
}
